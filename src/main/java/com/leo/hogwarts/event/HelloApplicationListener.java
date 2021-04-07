package com.leo.hogwarts.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloApplicationListener.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/7
 */
@Component
public class HelloApplicationListener {

    @EventListener
    public void listenerHelloApplicationEvent(HelloApplicationEvent event) {
        String param = event.getObj();
        System.out.println("-------111-------" + param);
    }
}
