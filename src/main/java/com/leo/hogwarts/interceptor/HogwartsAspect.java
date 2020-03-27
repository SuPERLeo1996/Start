package com.leo.hogwarts.interceptor;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

//    @Before("executeService()")
//    public void doBeforeAdvice(JoinPoint joinPoint){
//        Object[] objects = joinPoint.getArgs();
//        logger.info("args:{}",objects);
//        MemberSignature memberSignature = (MemberSignature) joinPoint.getSignature();
//        logger.info("declaringType:{}",memberSignature.getDeclaringType());
//        logger.info("declaringTypeName:{}",memberSignature.getDeclaringTypeName());
//        logger.info("name:{}",memberSignature.getName());
//    }

    @Around("executeService()")
    public void doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.info("requestURL:{}",request.getRequestURL());
        logger.info("remoteAddr:{}",request.getRemoteAddr());
        logger.info("requestURI:{}",request.getRequestURI());
        logger.info("User-agent:{}",request.getHeader("user_agent"));
        String requestMethod = request.getMethod();
        logger.info("requestMethod:{}",requestMethod);
        MemberSignature memberSignature = (MemberSignature) proceedingJoinPoint.getSignature();
        logger.info("declaringTypeName:{},name:{}",memberSignature.getDeclaringTypeName(),memberSignature.getName());
        Object[] objects = proceedingJoinPoint.getArgs();
        logger.info("args:{}", JSON.toJSONString(objects));

    }
}
