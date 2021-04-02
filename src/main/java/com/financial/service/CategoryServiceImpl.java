package com.financial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.entity.Category;
import com.financial.repository.CategoryRepository;
import com.financial.repository.reposervice.CategoryService;
import com.financial.service.exception.NotFoundException;

public class CategoryServiceImpl implements CategoryService{
	
	@Autowired private CategoryRepository categoryRepository;

	@Override
	public Category getById(Long id) {
		Optional<Category> result = categoryRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Category.class.getName()));
		return result.get();
	}
	

}
