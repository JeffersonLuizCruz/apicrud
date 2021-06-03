package com.financial.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.response.ProductResponseDto;
import com.financial.entity.Product;
import com.financial.service.impl.ProductServiceImpl;
import com.financial.util.URL;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired private ProductServiceImpl productService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getById(Long id) {
		Product result = productService.getById(id);
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProductResponseDto>> findPage(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="") String categories,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
			){
		
		String nameDecoded = URL.decodingParam(name);
		List<Long> ids = URL.decodingUrl(categories);
		
		Page<Product> list = productService.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductResponseDto> listDto = list.map(result -> ProductResponseDto.transformToProduct(result));  
		return ResponseEntity.ok().body(listDto);
	}

}
