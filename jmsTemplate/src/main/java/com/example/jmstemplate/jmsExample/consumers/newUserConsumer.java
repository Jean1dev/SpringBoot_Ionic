package com.example.jmstemplate.jmsExample.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class newUserConsumer {

    @Autowired private JmsTemplate jmsTemplate;

    // Ouvindo um topico
    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(String str) {
        System.out.println(str);
        jmsTemplate.convertAndSend("queue.sample", "ENVIANDO VIA CLIENT");
    }

    //Ouvindo uma fila
    @JmsListener(destination = "queue.sample")
    public void onReceiverQueue(String str) {
        System.out.println( str );
        jmsTemplate.convertAndSend("topic.sample", "ENVIANDO VIA CLIENT");
    }
}
