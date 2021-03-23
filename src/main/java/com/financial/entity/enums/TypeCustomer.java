package com.financial.entity.enums;

public enum TypeCustomer {
	
	NATURAL_PERSON(1, "Pessoa Física"),
	LEGAL_ENTITY(2, "Pessoa Jurídica");
	
	private int cod;
	private String description;
	
	
	private TypeCustomer(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static TypeCustomer toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TypeCustomer x : TypeCustomer.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
