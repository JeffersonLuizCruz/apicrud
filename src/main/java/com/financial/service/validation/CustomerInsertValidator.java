package com.financial.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import com.financial.controller.exception.FieldMessage;
import com.financial.dto.request.CustomerRequestDto;
import com.financial.entity.enums.TypeCustomer;
import com.financial.service.validation.util.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerRequestDto>{

	
	@Override
	public void initialize(CustomerInsert customerInsert) {
		// TODO Auto-generated method stub
		//ConstraintValidator.super.initialize(customerInsert);
	}
	
	@Override
	public boolean isValid(CustomerRequestDto customerRequestDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(customerRequestDto.getType().equals(TypeCustomer.NATURAL_PERSON.getCod()) && !BR.isValidCPF(customerRequestDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF Inválido."));
		}
		
		if(customerRequestDto.getType().equals(TypeCustomer.LEGAL_ENTITY.getCod()) && !BR.isValidCNPJ(customerRequestDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CNPJ Inválido."));
		}
		
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
