package com.financial.dto.requestdto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.financial.entity.Customer;

public class CustomerRequestDto implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	
	public CustomerRequestDto() {
	}

	public CustomerRequestDto(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Customer transformToCustomer() {
		Customer customer = new Customer(null, this.name, this.email, null, null);
		
		return customer;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
