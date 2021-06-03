package com.financial.service.impl;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.financial.entity.PaymentSlip;
// OBS: Enquanto estiviver com essa notação porque não foi testando essa classe.
// Classe de abstração para teste do vencimento de um pagamento
@Service
public class PaymentSlipService {
															// instanteDoPedido
	public void requestPaymentSlip(PaymentSlip paymentSlip, OffsetDateTime requestTime) {
		
		paymentSlip.setDueDate(requestTime.plusDays(7));
	}
}
