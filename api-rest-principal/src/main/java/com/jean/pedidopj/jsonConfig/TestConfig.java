package com.jean.pedidopj.jsonConfig;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jean.pedidopj.services.DBService;
import com.jean.pedidopj.services.EmailService;
import com.jean.pedidopj.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;

 	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
 	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}

