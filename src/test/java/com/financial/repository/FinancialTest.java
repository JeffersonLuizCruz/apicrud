package com.financial.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Address;
import com.financial.entity.Category;
import com.financial.entity.City;
import com.financial.entity.Customer;
import com.financial.entity.ItemRequest;
import com.financial.entity.Payment;
import com.financial.entity.PaymentSlip;
import com.financial.entity.Product;
import com.financial.entity.Request;
import com.financial.entity.State;
import com.financial.entity.enums.StagePayment;
import com.financial.entity.enums.TypeCustomer;

@SpringBootTest
public class FinancialTest {
	
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private CustomerRepository customerRepository;
	@Autowired private AddressRepository addressRepository;
	@Autowired private CityRepository cityRepository;
	@Autowired private StateRepository stateRepository;
	@Autowired private RequestRepository requestRepository;
	@Autowired private ItemRequestRepository itemRepository;

	
	@Test
	public void saveCategoryProductTest() {
		
		Category cat1 = new Category(null, "Papelaria");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Acessório de TI");
		
		Product p1 = new Product(null, "Computador", new BigDecimal("2500"));
		Product p2 = new Product(null, "Notebook Aple", new BigDecimal("7000"));
		Product p3 = new Product(null, "Folha A4", new BigDecimal("25"));
		Product p4 = new Product(null, "Lápis", new BigDecimal("1.5"));
		Product p5 = new Product(null, "Caneta", new BigDecimal("2"));
		Product p6 = new Product(null, "Mouse", new BigDecimal("7.5"));
		
		
		
		cat1.getProducts().addAll(Arrays.asList(p3, p4, p5));
		cat2.getProducts().addAll(Arrays.asList(p1, p2));
		cat3.getProducts().addAll(Arrays.asList(p6));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		p1.getCategories().addAll(List.of(cat2));
		p2.getCategories().addAll(List.of(cat2));
		p3.getCategories().addAll(List.of(cat1));
		p4.getCategories().addAll(List.of(cat1));
		p5.getCategories().addAll(List.of(cat1));
		p6.getCategories().addAll(List.of(cat3));
		
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
	}
	
	@Test
	public void saveCustomerTest() {

		State state = new State(null, "Pernambuco");
		City city = new City(null, "Ipojuca", state);
		Customer customer = new Customer(null, "Jefferson Luiz", "jefferson@gmail.com", "08575874490", TypeCustomer.NATURAL_PERSON);
		Address address = new Address(null, "Cavalo Marinho", "15", "Didinho", "Ipojuca", "5592000", customer, city);
		
		

		customer.getPhones().addAll(Set.of("88053521", "88053522"));
		customer.getAddress().addAll(List.of(address));
		
		stateRepository.save(state);
		cityRepository.save(city);
		customerRepository.save(customer);
	}
	
	@Test
	public void saveRequestTest() {
		Optional<Customer> customer = customerRepository.findById(1L);
		Optional<Address> address = addressRepository.findById(1L);
		
		Request request = new Request(null, OffsetDateTime.now(), address.get(), customer.get());
			
		requestRepository.save(request);
		
	}
	
	@Test
	public void saveItemRequestTest() {
		Optional<Request> request = requestRepository.findById(1L);
		Optional<Product> product = productRepository.findById(1L);
		ItemRequest item = new ItemRequest(request.get(), product.get(), "150", "2", "2000");
		
		
		itemRepository.save(item);
	}
	

	
	@Test
	public void savePaymentTest() {
		Optional<Request> request = requestRepository.findById(1L);
		
		Payment paySlip = new PaymentSlip(null, StagePayment.SETTLED, request.get(), OffsetDateTime.now(), null);
		request.get().setPayment(paySlip);
		
		requestRepository.save(request.get());
	}


}
