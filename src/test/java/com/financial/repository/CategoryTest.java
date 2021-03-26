package com.financial.repository;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Category;
import com.financial.entity.Product;

@SpringBootTest
public class CategoryTest {
	
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private ProductRepository productRepository;

	
	@Test
	public void saveCategoryProductTest() {
		Category cat1 = new Category(null, "Papelaria");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Acessório de TI");
		
		Product p1 = new Product(null, "Computador", BigDecimal.valueOf(2000));
		Product p2 = new Product(null, "Notebook Aple", BigDecimal.valueOf(7000));
		Product p3 = new Product(null, "Folha A4", BigDecimal.valueOf(25));
		Product p4 = new Product(null, "Lápis", BigDecimal.valueOf(1));
		Product p5 = new Product(null, "Caneta", BigDecimal.valueOf(1.5));
		Product p6 = new Product(null, "Mouse", BigDecimal.valueOf(05));
		
		cat1.getProducts().addAll(Arrays.asList(p3, p4, p5));
		cat2.getProducts().addAll(Arrays.asList(p1, p2));
		cat3.getProducts().addAll(Arrays.asList(p6));
		
//		p1.getCategories().addAll(Arrays.asList(cat2));
//		p2.getCategories().addAll(Arrays.asList(cat2));
//		p3.getCategories().addAll(Arrays.asList(cat1));
//		p4.getCategories().addAll(Arrays.asList(cat1));
//		p5.getCategories().addAll(Arrays.asList(cat1));
//		p6.getCategories().addAll(Arrays.asList(cat3));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
	}

}
