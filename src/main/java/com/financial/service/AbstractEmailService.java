package com.financial.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.financial.entity.Request;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Request request) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(request);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Request request) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(request.getCustomer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + request.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(request.toString());
		return sm;
	}

}
