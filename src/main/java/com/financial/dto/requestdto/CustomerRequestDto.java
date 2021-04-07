package com.financial.dto.requestdto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.financial.entity.Address;
import com.financial.entity.City;
import com.financial.entity.Customer;
import com.financial.entity.State;
import com.financial.entity.enums.TypeCustomer;
import com.financial.service.validation.CustomerInsert;

// Anotação personalizada de validação de cpj e cnpj
@CustomerInsert
public class CustomerRequestDto implements Serializable{
	private static final long serialVersionUID = 1L;
	

	
	@NotEmpty(message="Preenchimento obrigatório - Nome")
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;

	@NotEmpty(message="Preenchimento obrigatório - Email")
	@Email(message="Email inválido")
	private String email;

	@NotEmpty(message="Preenchimento obrigatório - CPF")
	private String cpf;

	private Integer type;
	
	
	
	//@NotEmpty(message="Preenchimento obrigatório - Password")
	private String password;
	
	
	
	@NotEmpty(message="Preenchimento obrigatório - Street")
	private String street;

	@NotEmpty(message="Preenchimento obrigatório - Numéro")
	private String number;

	private String complement;

	private String district;

	@NotEmpty(message="Preenchimento obrigatório - CEP")
	private String zipCode;
	
	
	
	@NotEmpty(message="Preenchimento obrigatório - Telefone")
	private String telephone1;
	private String telephone2;
	private String telephone3;

	private Long cityId;
	private Long stateId;
	
	
	public CustomerRequestDto() {
	}
	

	public CustomerRequestDto(
			@NotEmpty(message = "Preenchimento obrigatório") @Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres") String name,
			@NotEmpty(message = "Preenchimento obrigatório") @Email(message = "Email inválido") String email,
			@NotEmpty(message = "Preenchimento obrigatório") String cpf, Integer type,
			@NotEmpty(message = "Preenchimento obrigatório") String password,
			@NotEmpty(message = "Preenchimento obrigatório") String street,
			@NotEmpty(message = "Preenchimento obrigatório") String number, String complement, String district,
			@NotEmpty(message = "Preenchimento obrigatório") String zipCode,
			@NotEmpty(message = "Preenchimento obrigatório") String telephone1, String telephone2, String telephone3,
			Long cityId, Long stateId) {
		
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.type = type;
		this.password = password;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.zipCode = zipCode;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
		this.telephone3 = telephone3;
		this.cityId = cityId;
		this.stateId = stateId;
	}


	// [PUT]
	public Customer transformToCustomer() {
		Customer customer = new Customer(null, this.name, this.email, null, null);
		
		return customer;
	}
	// [POST]
	public Customer transformToNewCustomer() {
		State state = new State(this.stateId, null);
		City city = new City(this.cityId, null, state);
		Address address = new Address(null, this.street, this.number, this.complement, this.district, this.zipCode, city);
		Customer customer = new Customer(null, this.name, this.email, this.cpf, TypeCustomer.toEnum(getType()));
		
		customer.getAddress().add(address);
		customer.getPhones().add(this.telephone1);
		
		if (this.telephone2 != null) {
			customer.getPhones().add(telephone2);
		}
		if (this.telephone3 != null) {
			customer.getPhones().add(telephone3);
		}
		
		return customer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getComplement() {
		return complement;
	}


	public void setComplement(String complement) {
		this.complement = complement;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getTelephone1() {
		return telephone1;
	}


	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}


	public String getTelephone2() {
		return telephone2;
	}


	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}


	public String getTelephone3() {
		return telephone3;
	}


	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}


	public Long getCityId() {
		return cityId;
	}


	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}


	public Long getStateId() {
		return stateId;
	}


	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	
	
		
	
}
