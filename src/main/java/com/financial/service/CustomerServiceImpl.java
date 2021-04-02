package com.financial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.entity.Customer;
import com.financial.repository.CustomerRepository;
import com.financial.repository.reposervice.CustomerService;
import com.financial.service.exception.NotFoundException;

public class CustomerServiceImpl implements CustomerService{
	
	@Autowired private CustomerRepository customerRepository;

	@Override
	public Customer getById(Long id) {
		Optional<Customer> result = customerRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Customer.class.getName()));
		return result.get();
	}

}
