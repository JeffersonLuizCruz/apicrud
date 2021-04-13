package com.financial.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.financial.entity.enums.StagePayment;

@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installment;
	
	
	
	
	
	public PaymentCard() {
	}

	public PaymentCard(Long id, StagePayment stage, Request request, Integer installment) {
		super(id, stage, request);
		this.installment = installment;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	
}
