package com.financial.repository;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Customer;
import com.financial.entity.enums.TypeCustomer;

@SpringBootTest
public class CustomerTest {
	
	@Autowired private CustomerRepository customerRepository;
	
	@Test
	public void saveCustomerTest() {
		
		Customer cust1 = new Customer(null, "Hugo Luiz", "hugo@gmail.com", "08575874400", TypeCustomer.NATURAL_PERSON);

	}

}
