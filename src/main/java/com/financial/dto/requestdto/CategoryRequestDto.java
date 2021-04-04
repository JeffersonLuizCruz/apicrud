package com.financial.dto.requestdto;

import java.io.Serializable;

import com.financial.entity.Category;

public class CategoryRequestDto implements Serializable{
	private static final long serialVersionUID = 1L;
	

	
	private String name;


	public Category transformToCategory(){
		Category category = new Category(null, this.name);
		
		return category;
	}
	
	private CategoryRequestDto() {
	}

	private CategoryRequestDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	
}
