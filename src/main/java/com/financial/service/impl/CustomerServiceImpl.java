package com.financial.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financial.entity.Customer;
import com.financial.entity.enums.Perfil;
import com.financial.repository.AddressRepository;
import com.financial.repository.CustomerRepository;
import com.financial.security.UserSecurityDetails;
import com.financial.service.CustomerService;
import com.financial.service.UserService;
import com.financial.service.exception.AuthorizationException;
import com.financial.service.exception.BadRequestException;
import com.financial.service.exception.IntegrityViolationException;
import com.financial.service.exception.NotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired private CustomerRepository customerRepository;
	@Autowired private AddressRepository addressRepository;
	@Autowired private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Customer getById(Long id) {
		verifyAuthenticated(id);
		Optional<Customer> result = customerRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Customer.class.getName()));
		return result.get();
	}

	@Transactional
	@Override
	public Customer save(Customer customer) {
		customer.setId(null);
		try {
			// O Email é unique na base de dados
			customerRepository.findByEmail(customer.getEmail());
		} catch (Exception e) {
			throw new BadRequestException("Email já existente");
		}
				
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		Customer createCustomer = customerRepository.save(customer);
		addressRepository.saveAll(createCustomer.getAddress());
		
		return createCustomer;
	}

	@Transactional
	@Override
	public Customer update(Customer customer) {
		Customer editCustomer = verifyIfExist(customer.getId());
		String editPassword = passwordEncoder.encode(editCustomer.getPassword());
		editCustomer.setPassword(editPassword);
		
		editCustomer.setName(customer.getName());
		editCustomer.setEmail(customer.getEmail());
		
		return customerRepository.save(editCustomer);
	}

	@Override
	public List<Customer> listAll() {
		List<Customer> listAllCustomer = customerRepository.findAll();
		
		return listAllCustomer;
	}

	@Override
	public void delete(Long id) {
		verifyIfExist(id);
		
		try {
			customerRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Não é possível excluir porque há pedidos relacionados com o cliente");
		}
		
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return customerRepository.findAll(pageRequest);
	}
	
	private Customer verifyIfExist(Long id) {
		Optional<Customer> result = customerRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Customer.class.getName()));
		
		return result.get();
	}
	
	private void verifyAuthenticated(Long id) {
		UserSecurityDetails user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
	}
	

}
