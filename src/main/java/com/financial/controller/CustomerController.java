package com.financial.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.request.CustomerRequestDto;
import com.financial.dto.response.CustomerResponseDto;
import com.financial.entity.Customer;
import com.financial.event.Event;
import com.financial.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("customers")
public class CustomerController {
	
	@Autowired private CustomerServiceImpl customerService;
	@Autowired private ApplicationEventPublisher publisher;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id){
		Customer result = customerService.getById(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponseDto> saveCustomer(@Valid @RequestBody CustomerRequestDto customerDto, HttpServletResponse response){
		Customer customer = customerDto.transformToNewCustomer();
		Customer createCustomer = customerService.save(customer);
		publisher.publishEvent(new Event(this, response, createCustomer.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponseDto.transformToCustomer(customer));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CustomerResponseDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerDto){
		Customer customer = customerDto.transformToCustomer();
		customer.setId(id);
		Customer editCustomer = customerService.update(customer);
		
		return ResponseEntity.ok(CustomerResponseDto.transformToCustomer(editCustomer));
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> listAllCategory(){
		List<Customer> list = customerService.listAll();
		List<CustomerResponseDto> listDto = list.stream().map(result -> new CustomerResponseDto(result)).collect(Collectors.toList());  
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
		customerService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CustomerResponseDto>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
			){
		Page<Customer> list = customerService.findPage(page, linesPerPage, orderBy, direction);
		Page<CustomerResponseDto> listDto = list.map(result -> new CustomerResponseDto(result));  
		return ResponseEntity.ok().body(listDto);
	}

}
