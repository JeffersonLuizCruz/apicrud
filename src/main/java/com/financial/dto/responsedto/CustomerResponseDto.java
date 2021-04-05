package com.financial.dto.responsedto;

import java.io.Serializable;

import com.financial.entity.Customer;

public class CustomerResponseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	
	
	private CustomerResponseDto(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public CustomerResponseDto(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}
	
	public static CustomerResponseDto transformToCustomer(Customer customer) {
		
		return new CustomerResponseDto(customer.getId(), customer.getName(), customer.getEmail());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	

}
