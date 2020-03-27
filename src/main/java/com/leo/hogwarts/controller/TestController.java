package com.leo.hogwarts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Author Leo
 * @Date 2020/3/27 15:39
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/aop",method = RequestMethod.GET)
    public Object aopTest(String param,Integer size){
        return "success";
    }

}
