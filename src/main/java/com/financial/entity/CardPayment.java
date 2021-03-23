package com.financial.entity;

import javax.persistence.Entity;

import com.financial.entity.enums.StagePayment;

@Entity
public class CardPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installment;

	public CardPayment() {
	}

	public CardPayment(Long id, StagePayment stage, Request request, Integer installment) {
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
