package com.leo.hogwarts.annotation;

import com.leo.hogwarts.constant.HogwartsConstant;

import java.lang.annotation.*;

/**
 * @ClassName RateLimiter
 * @Description 限流注解
 * @Author Leo
 * @Date 2020/4/28 10:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD , ElementType.TYPE})
public @interface RateLimit {
    int permits() default 1;
}
