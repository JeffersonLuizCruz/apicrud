package com.financial.repository.reposervice;

import java.util.List;

import com.financial.entity.Category;

public interface CategoryService {
	
	public Category getById(Long id);
	public Category save(Category category);
	public Category update(Category category);
	public List<Category> listAll();
	public void delete(Long id);

}
