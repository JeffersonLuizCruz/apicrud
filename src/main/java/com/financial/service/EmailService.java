package com.financial.service;

import org.springframework.mail.SimpleMailMessage;

import com.financial.entity.Request;


public interface EmailService {
	
	void sendOrderConfirmationEmail(Request request);
	void sendEmail(SimpleMailMessage msg);

}
