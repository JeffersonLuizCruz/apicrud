package com.financial.dto.responsedto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.financial.entity.Product;

public class ProductResponseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private BigDecimal price;
	
	
	
	
	
	public ProductResponseDto() {
	}
	
	private ProductResponseDto(Long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public ProductResponseDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		
	}
	
	public static ProductResponseDto transformToProduct(Product product) {
		
		return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

	
}
