package com.financial.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemRequestPK id = new ItemRequestPK();
	
	private String discount; // Desconto
	private String amount; // Quantidade
	private String price; // Preço
	
	
	public ItemRequest() {
	}

	public ItemRequest(Request request, Product product, String discount, String amount, String price) {
		this.id.setRequest(request); // ItemRequestPK
		this.id.setProduct(product); // ItemRequestPK
		this.discount = discount;
		this.amount = amount;
		this.price = price;
	}
	
	public BigDecimal getSubTotal() {
		
		BigDecimal total = new BigDecimal(getPrice())
													.subtract(new BigDecimal(getDiscount()))
													.multiply(new BigDecimal(getAmount()));
		
		return total;
	}
	
	@JsonIgnore
	public Request getRequest() { // obs
		return id.getRequest();
	}
	
	public void setRequest(Request request) { // obs
		id.setRequest(request);
	}

	public Product getProduct() { // obs
		return id.getProduct();
	}
	
	public void setProduct(Product product) { // obs
		id.setProduct(product);
	}

	public ItemRequestPK getId() {
		return id;
	}

	public void setId(ItemRequestPK id) {
		this.id = id;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemRequest other = (ItemRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
