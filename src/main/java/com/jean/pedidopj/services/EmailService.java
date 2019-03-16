package com.jean.pedidopj.services;

import org.springframework.mail.SimpleMailMessage;

import com.jean.pedidopj.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

 	void sendEmail(SimpleMailMessage msg);
}
