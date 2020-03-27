package com.leo.hogwarts.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName HogwartsAspect
 * @Description
 * @Author Leo
 * @Date 2020/3/27 15:18
 */
@Aspect
@Component
public class HogwartsAspect {

    private static final Logger logger = LoggerFactory.getLogger(HogwartsAspect.class);

    @Pointcut("execution(* com.leo.hogwarts.controller.*.* (..))")
    public void executeService(){

    }

    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        logger.info("args:{}",objects);
        MemberSignature memberSignature = (MemberSignature) joinPoint.getSignature();
        logger.info("declaringType:{}",memberSignature.getDeclaringType());
        logger.info("declaringTypeName:{}",memberSignature.getDeclaringTypeName());
        logger.info("name:{}",memberSignature.getName());
    }

}
