package com.financial.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Address;
import com.financial.entity.Category;
import com.financial.entity.City;
import com.financial.entity.Customer;
import com.financial.entity.Payment;
import com.financial.entity.PaymentSlip;
import com.financial.entity.Product;
import com.financial.entity.Request;
import com.financial.entity.State;
import com.financial.entity.enums.StagePayment;
import com.financial.entity.enums.TypeCustomer;

@SpringBootTest
public class CategoryTest {
	
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private CustomerRepository customerRepository;
	@Autowired private AddressRepository addressRepository;
	@Autowired private CityRepository cityRepository;
	@Autowired private StateRepository stateRepository;

	
	@Test
	public void saveCategoryProductTest() {
		
		Category cat1 = new Category(null, "Papelaria");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Acessório de TI");
		
		Product p1 = new Product(null, "Computador", BigDecimal.valueOf(2000));
		Product p2 = new Product(null, "Notebook Aple", BigDecimal.valueOf(7000));
		Product p3 = new Product(null, "Folha A4", BigDecimal.valueOf(25));
		Product p4 = new Product(null, "Lápis", BigDecimal.valueOf(1));
		Product p5 = new Product(null, "Caneta", BigDecimal.valueOf(1.5));
		Product p6 = new Product(null, "Mouse", BigDecimal.valueOf(05));
		
		cat1.getProducts().addAll(Arrays.asList(p3, p4, p5));
		cat2.getProducts().addAll(Arrays.asList(p1, p2));
		cat3.getProducts().addAll(Arrays.asList(p6));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		State state = new State(null, "Pernambuco");
		City city = new City(null, "Ipojuca", state);
		Address address = new Address(null, "Cavalo Marinho", "15", "Didinho", "Ipojuca", "5592000", city);
		Customer customer = new Customer(null, "Jefferson Luiz", "jefferson@gmail.com", "08575874490", TypeCustomer.NATURAL_PERSON);
		
		customer.getPhones().addAll(Set.of("88053521", "88053522"));
		
		Request request = new Request(null, OffsetDateTime.now(), address);
		
		customer.getRequests().addAll(List.of(request));
		
		Payment payment = new PaymentSlip(null, StagePayment.SETTLED, request, OffsetDateTime.now(), null);
		request.setPayment(payment);
		
		stateRepository.save(state);
		cityRepository.save(city);
		addressRepository.save(address);
		customerRepository.save(customer);
		

		
	}

}
