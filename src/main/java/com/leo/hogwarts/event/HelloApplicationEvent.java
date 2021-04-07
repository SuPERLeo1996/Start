package com.leo.hogwarts.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName HelloApplicationEvent.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/7
 */
public class HelloApplicationEvent extends ApplicationEvent {

    private String obj;

    public HelloApplicationEvent(Object source,String obj) {
        super(source);
        this.obj = obj;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}
