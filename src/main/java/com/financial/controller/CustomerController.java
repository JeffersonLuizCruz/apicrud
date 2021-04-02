package com.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.entity.Customer;
import com.financial.service.CustomerServiceImpl;

@RestController
@RequestMapping("customers")
public class CustomerController {
	
	@Autowired private CustomerServiceImpl customerService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id){
		Customer result = customerService.getById(id);
		return ResponseEntity.ok(result);
	}

}
