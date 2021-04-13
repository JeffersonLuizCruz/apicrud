package com.financial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.entity.PaymentCard;
import com.financial.entity.PaymentSlip;

/**
 * Classe de registro das SUBCLASSES PaymentCard e PaymentSlip.
 * OBS: Código padrão da exigência do Jakson para subclasses de uma entidade abstrata.
 * */

@Configuration
public class JacksonConfig {
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentCard.class);
				objectMapper.registerSubtypes(PaymentSlip.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}