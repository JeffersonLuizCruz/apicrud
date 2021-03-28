package com.financial.repository;

import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Address;
import com.financial.entity.City;
import com.financial.entity.Customer;
import com.financial.entity.State;
import com.financial.entity.enums.TypeCustomer;

@SpringBootTest
public class CustomerTest {
	
	@Autowired private CustomerRepository customerRepository;
	@Autowired private AddressRepository addressRepository;
	
	@Test
	public void saveCustomerTest() {
		State state = new State(null, "Pernambuco");
		City city = new City(null, "Lagoa dos Gatos", state);
		
		
		
		Customer cust1 = new Customer(null, "Hugo Luiz", "hugo@gmail.com", "88053521", TypeCustomer.NATURAL_PERSON);

		cust1.getPhones().addAll(Set.of("88053522", "88053523"));	
		Address address = new Address(
				null, "Alcides Alburquerques",
				"", "Ao lado da casa Elisabete",
				"Distrito Centro", "55592000", cust1, city);
		
		cust1.getAddress().add((Address) Arrays.asList(address));
		
		customerRepository.save(cust1);
		addressRepository.save(address);
	}

}
