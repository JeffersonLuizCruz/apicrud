package com.financial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.financial.entity.Category;
import com.financial.entity.Product;
import com.financial.repository.CategoryRepository;
import com.financial.repository.ProductRepository;
import com.financial.repository.reposervice.ProductService;
import com.financial.service.exception.BadRequestException;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired private ProductRepository productRepository;
	@Autowired private CategoryRepository categoryRepository;

	@Override
	public Product getById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		result.orElseThrow(() -> new BadRequestException("Não existe Produto com id :" + id + ", Tipo: " + Product.class.getName()));
		
		return result.get();
	}


	public Page<Product> search(String name, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageProduct = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return productRepository.search(name, categories, pageProduct);
	}

}
