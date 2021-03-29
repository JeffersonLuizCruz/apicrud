package com.financial.repository;

import java.util.List;
import java.util.Set;

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
	@Autowired private StateRepository stateRepository;
	@Autowired private CityRepository cityRepository;
	
	@Test
	public void saveCustomerTest() {
		State state = new State(null, "Pernambuco");
		City city = new City(null, "Lagoa dos Gatos", state);
		
		
		Customer customer = new Customer(null, "Jefferson Luiz", "jefferson@gmail.com", "08575874490", TypeCustomer.NATURAL_PERSON);

		customer.getPhones().addAll(Set.of("88053521", "88053522"));	
		Address address = new Address(
				null, "Alcides Alburquerques",
				"15", "Ao lado da casa Elisabete",
				"Distrito Centro", "55592000", city);
		
		customer.getAddress().addAll(List.of(address));
		
		stateRepository.save(state);
		cityRepository.save(city);
		customerRepository.save(customer);
		addressRepository.save(address);
	}

}
