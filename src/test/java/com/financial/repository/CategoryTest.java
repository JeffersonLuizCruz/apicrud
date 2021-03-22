package com.financial.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.Category;

@SpringBootTest
public class CategoryTest {
	
	@Autowired private CategoryRepository repository;
	
	@Test
	public void saveTest() {
		
		Category category = new Category(null, "Alimentos");
		
		Category createCategory = repository.save(category);
		
		assertThat(createCategory.getId()).isEqualTo(1L);
		
	}

}
