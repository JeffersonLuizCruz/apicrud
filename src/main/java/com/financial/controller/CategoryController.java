package com.financial.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.entity.Category;
import com.financial.event.Event;
import com.financial.service.CategoryServiceImpl;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired private CategoryServiceImpl categoryService;
	@Autowired private ApplicationEventPublisher publisher;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {
		Category result = categoryService.getById(id);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category, HttpServletResponse response){
		Category createCategory = categoryService.save(category);
		publisher.publishEvent(new Event(this, response, createCategory.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category){
		Category editCategory = categoryService.update(id, category);
		
		return ResponseEntity.ok(editCategory);
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> listAllCategory(){
		List<Category> list = categoryService.listAll();
		
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
