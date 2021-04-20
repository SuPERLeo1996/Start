package com.leo.hogwarts.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonShutdownException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLockAspect.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/20
 */
@Aspect
@Component
public class RedisLockAspect {
    private static final Logger log = LoggerFactory.getLogger(RedisLockAspect.class);

    @Autowired
    private RedissonClient redissonClient;

    private static int getLeaseTime(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        RedisLock annotation = method.getAnnotation(RedisLock.class);
        return annotation.leaseTime();
    }

    private static int getTimeout(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        RedisLock annotation = method.getAnnotation(RedisLock.class);
        return annotation.timeout();
    }


    @Around(value = "@annotation(RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String lockKey = String.format("%s:%s",
                joinPoint.getSignature().getName(),
                Arrays.deepToString(joinPoint.getArgs())
        );
        RLock lock = redissonClient.getLock(lockKey);
        String threadName = Thread.currentThread().getName(), lockName = lock.getName();
        boolean success = false;
        try {
            log.debug("Thread {} try lock {}", threadName, lockName);
            long timeout = getTimeout(joinPoint);
            if (timeout == 0) {
                lock.lock(getLeaseTime(joinPoint), TimeUnit.MILLISECONDS);
                success = true;
            } else {
                success = lock.tryLock(timeout, getLeaseTime(joinPoint), TimeUnit.MILLISECONDS);
                if (!success) {
                    log.info("Thread {} try lock {} failed", threadName, lockName);
                    throw new Exception("尝试锁获取失败");
                }
            }
            log.debug("Thread {} get lock {}", threadName, lockName);
            return joinPoint.proceed();
        } finally {
            boolean unlockSuccess = true;
            if (success)
                try {
                    lock.unlock();
                } catch (IllegalMonitorStateException | RedissonShutdownException ignored) {
                    log.warn("Thread {} unlock failed {}", threadName, lockName);
                    unlockSuccess = false;
                }
            if (unlockSuccess)
                log.debug("Thread {} unlock {}", threadName, lockName);
        }
    }
}


