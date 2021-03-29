package com.financial.entity;

import java.time.OffsetDateTime;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.financial.entity.enums.StagePayment;

@Entity
public class PaymentSlip extends Payment{
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private OffsetDateTime dueDate;

	@JsonFormat(pattern="dd/MM/yyyy")
	private OffsetDateTime payDay;

	public PaymentSlip() {
	}

	public PaymentSlip(Long id, StagePayment stage, Request request, OffsetDateTime dueDate, OffsetDateTime payDay) {
		super(id, stage, request);
		this.dueDate = dueDate;
		this.payDay = payDay;
	}

	public OffsetDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(OffsetDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public OffsetDateTime getPayDay() {
		return payDay;
	}

	public void setPayDay(OffsetDateTime payDay) {
		this.payDay = payDay;
	}
	
	
	
	
}
