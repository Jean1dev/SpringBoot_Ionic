package com.jean.pedidopj.jsonConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.pedidopj.domain.PagamentoComBoleto;
import com.jean.pedidopj.domain.PagamentoComCartao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
 public class JacksonConfig {
 	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
 	@Bean
 	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
 		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
 			public void configure(ObjectMapper objectMapper) {
 				objectMapper.registerSubtypes(PagamentoComCartao.class);
 				objectMapper.registerSubtypes(PagamentoComBoleto.class);
 				super.configure(objectMapper);
 			}
 		};
 		return builder;
 	}
 }