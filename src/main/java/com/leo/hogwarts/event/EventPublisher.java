package com.leo.hogwarts.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @ClassName EventPublisher.java
 * @Author LEO
 * @Description
 * @Date 2021/4/7
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publishEvent(String obj){
        applicationContext.publishEvent(new HelloApplicationEvent(this,obj));
    }
}
