package com.financial.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Address;
import com.financial.entity.City;
import com.financial.entity.Customer;
import com.financial.entity.Request;
import com.financial.entity.State;

@SpringBootTest
public class RequestTest {
	@Autowired private CustomerRepository customerRepository;
	@Autowired private AddressRepository addressRepository;
	@Autowired private CityRepository cityRepository;
	@Autowired private StateRepository stateRepository;
	
	@Test
	public void requestTest() {
		OffsetDateTime odt = OffsetDateTime.now();
		Customer customer = new Customer();
		State state = new State(null, "Pernambuco");
		City city = new City(null, "Ipojuca", state);

		Address address = new Address(null, "Cavalo Marinho", "15", "Didinho", "Ipojuca", "5592000", city);
		Request request = new Request(null, odt, address);
		
		customer.getRequests().addAll(List.of(request));
		
		stateRepository.save(state);
		cityRepository.save(city);
		addressRepository.save(address);
		customerRepository.save(customer);
	}

}
