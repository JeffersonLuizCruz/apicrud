package com.financial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.financial.entity.Category;
import com.financial.repository.CategoryRepository;
import com.financial.repository.reposervice.CategoryService;
import com.financial.service.exception.IntegrityViolationException;
import com.financial.service.exception.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired private CategoryRepository categoryRepository;

	@Override
	public Category getById(Long id) {
		Optional<Category> result = categoryRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe categoria com id " + id + ", Tipo: " + Category.class.getName()));
		
		return result.get();
	}

	@Override
	public Category save(Category category) {
		Category createCategory = categoryRepository.save(category);
		
		return createCategory;
	}

	@Override
	public Category update(Long id, Category category) {
		Category editCategory = getById(id);
		
		BeanUtils.copyProperties(category, editCategory, "id");
		
		return categoryRepository.save(editCategory);
	}

	@Override
	public List<Category> listAll() {
		List<Category> listAllCategory = categoryRepository.findAll();
		
		return listAllCategory;
	}

	@Override
	public void delete(Long id) {
		getById(id);
		
		try {
			categoryRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}
	

}
