package com.leo.hogwarts.controller;

import com.leo.hogwarts.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/7
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private EventPublisher eventPublisher;

    @RequestMapping("/test")
    public void publishEvent(){
        eventPublisher.publishEvent("sssss");

    }
}

