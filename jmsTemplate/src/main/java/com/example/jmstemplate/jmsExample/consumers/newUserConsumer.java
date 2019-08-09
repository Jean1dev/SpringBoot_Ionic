package com.example.jmstemplate.jmsExample.consumers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class newUserConsumer {

    @JmsListener(destination = "login", containerFactory = "jmsFactory")
    public void onMessage(String message) {
        System.out.println(message);
    }
}
