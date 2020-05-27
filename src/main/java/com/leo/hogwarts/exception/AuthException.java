package com.leo.hogwarts.exception;

/**
 * @ClassName AuthException
 * @Description
 * @Author Leo
 * @Date 2020/3/31 15:31
 */

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
