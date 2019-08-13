package com.jean.pedidopj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class PedidoPjApplication implements CommandLineRunner {

	@Autowired private JmsTemplate jmsTemplate;
	@Autowired private JmsTemplate jmsTemplateTopic;

	public static void main(String[] args) {
		SpringApplication.run(PedidoPjApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.convertAndSend("queue.sample", "MENSAGEM PARA FILA 1");
		jmsTemplate.convertAndSend("queue.sample", "MENSAGEM PARA FILA 2");
		jmsTemplate.convertAndSend("queue.sample", "MENSAGEM PARA FILA 3");
		jmsTemplate.convertAndSend("queue.sample", "MENSAGEM PARA FILA 4");
		jmsTemplateTopic.convertAndSend("topic.sample", "MENSAGEM PARA O TOPICO");
	}
}
