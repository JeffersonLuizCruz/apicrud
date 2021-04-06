package com.financial.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.requestdto.CategoryRequestDto;
import com.financial.dto.responsedto.CategoryResponseDto;
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
	public ResponseEntity<CategoryResponseDto> saveCategory(@Valid @RequestBody CategoryRequestDto categoryDto, HttpServletResponse response){
		Category category = categoryDto.transformToCategory();
		Category createCategory = categoryService.save(category);
		publisher.publishEvent(new Event(this, response, createCategory.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDto.transformToCategory(createCategory));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto categoryDto){
		Category category = categoryDto.transformToCategory();
		category.setId(id);
		Category editCategory = categoryService.update(category);
		
		return ResponseEntity.ok(CategoryResponseDto.transformToCategory(editCategory));
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> listAllCategory(){
		List<Category> list = categoryService.listAll();
		List<CategoryResponseDto> listDto = list.stream().map(result -> new CategoryResponseDto(result)).collect(Collectors.toList());  
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoryResponseDto>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
			){
		Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryResponseDto> listDto = list.map(result -> new CategoryResponseDto(result));  
		return ResponseEntity.ok().body(listDto);
	}

}
