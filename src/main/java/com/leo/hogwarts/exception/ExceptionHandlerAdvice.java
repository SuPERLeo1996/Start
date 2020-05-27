package com.leo.hogwarts.exception;

import com.leo.hogwarts.entity.base.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ExceptionHandler
 * @Description
 * @Author Leo
 * @Date 2020/5/27 14:44
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultDTO exceptionResponse(HttpServletRequest request, Exception e) throws Exception {
        return new ResultDTO(500,e.getMessage());
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultDTO authExceptionResponse(HttpServletRequest request, Exception e) throws Exception {
        return new ResultDTO(400,e.getMessage());
    }

}
