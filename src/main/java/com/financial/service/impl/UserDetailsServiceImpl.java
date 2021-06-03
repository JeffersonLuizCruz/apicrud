package com.financial.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.financial.entity.Customer;
import com.financial.repository.CustomerRepository;
import com.financial.security.UserSecurityDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Customer> customer = customerRepository.findByEmail(email);
		
		if(customer.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSecurityDetails(customer.get().getId(),
									   customer.get().getEmail(),
									   customer.get().getPassword(),
									   customer.get().getPerfis());
	}

}
