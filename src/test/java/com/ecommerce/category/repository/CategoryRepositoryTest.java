package com.ecommerce.category.repository;

import java.util.Optional;

import com.ecommerce.category.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Before
	public void setUp() {
		categoryRepository.save(new Category("Laptops"));
	}

	@After
	public void tearDown() {
		categoryRepository.deleteAll();
	}

	@Test
	public void getAllCategories_returnsCategories() throws Exception {
		Iterable<Category> categories = categoryRepository.findAll();
		assertThat(categories).isNotEmpty();
		assertThat(categories).hasSize(1);
	}

	@Test
	public void findById_willReturnCategory() throws Exception {
		Optional<Category> category = categoryRepository.findCategoriesByName("Laptops");
		Optional<Category> actual = categoryRepository.findById(category.get().getId());
		assertThat(actual).isNotEmpty();
	}

	@Test
	public void findByCategoryName_willReturnCategory() throws Exception {
		Optional<Category> category = categoryRepository.findCategoriesByName("Laptops");
		assertThat(category.isPresent()).isTrue();
		assertThat(category.get()).isNotNull();
	}

	@Test
	public void addCategory_willReturnCategory() throws Exception {
		categoryRepository.save(new Category("Phones"));
		assertThat(categoryRepository.findAll()).isNotEmpty();
		assertThat(categoryRepository.findAll()).hasSize(2);
	}
}