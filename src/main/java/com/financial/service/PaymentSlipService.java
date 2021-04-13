package com.financial.service;

import java.util.Calendar;

import com.financial.entity.PaymentSlip;

public class PaymentSlipService {

	public void preencherPagamentoComBoleto(PaymentSlip pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
