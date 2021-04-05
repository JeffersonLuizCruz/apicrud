package com.financial.dto.requestdto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.financial.entity.Category;

public class CategoryRequestDto implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String name;

	
	public CategoryRequestDto() {
	}

	public CategoryRequestDto(String name) {
		this.name = name;
	}

	public Category transformToCategory(){
		Category category = new Category(null, this.name);
		
		return category;
	}

	public String getName() {
		return name;
	}
	
	
	
}
