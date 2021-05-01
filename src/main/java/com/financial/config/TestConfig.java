package com.financial.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.financial.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired private DBService dbService;
	
	/**
	 * Método responsável por instanciar no bando de dados no Profile de teste.
	 * */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

}
