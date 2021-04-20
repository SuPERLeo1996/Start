package com.leo.hogwarts.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RedisLock.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    int leaseTime() default 15000;

    int timeout() default 60000;
}
