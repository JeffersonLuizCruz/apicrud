package com.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.entity.Category;
import com.financial.service.CategoryServiceImpl;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired private CategoryServiceImpl categoryService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {
		Category result = categoryService.getById(id);
		return ResponseEntity.ok(result);
	}

}
