package com.financial.dto.response;

import java.io.Serializable;

import com.financial.entity.Category;

public class CategoryResponseDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	
	
	
	
	private CategoryResponseDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryResponseDto(Category category) {
		id = category.getId();
		name = category.getName();
	}

	public static CategoryResponseDto transformToCategory(Category category) {
		
		return new CategoryResponseDto(category.getId(), category.getName());
	}

	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
	

}
