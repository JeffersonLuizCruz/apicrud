package com.financial.repository.reposervice;

import java.util.List;

import com.financial.entity.Customer;

public interface CustomerService {
	
	public Customer getById(Long id);
	public Customer save(Customer customer);
	public Customer update(Customer customer);
	public List<Customer> listAll();
	public void delete(Long id);

}
