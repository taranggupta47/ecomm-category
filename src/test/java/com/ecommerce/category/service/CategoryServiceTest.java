package com.ecommerce.category.service;

import java.util.Collections;
import com.ecommerce.category.model.Category;
import com.ecommerce.category.repository.CategoryRepository;
import com.ecommerce.category.util.CategoryAlreadyExistsException;
import com.ecommerce.category.util.CategoryNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	private  CategoryService categoryService;

	@Before
	public void setUp() {
		categoryService = new CategoryService(categoryRepository);
	}

	@Test
	public void getAllCategories_returnsAllCategories() throws Exception {
		given(categoryRepository.findAll()).willReturn(Collections.emptyList());
		assertThat(categoryService.getAllCategories()).isEmpty();
		verify(categoryRepository).findAll();
	}

	@Test
	public void getCategoryById_returnsCategory() throws Exception {
		given(categoryRepository.findById(1))
				.willReturn(of(new Category(1, "Laptops")));
		Category actual = categoryService.getCategoryById(1);
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isEqualTo(1);
		assertThat(actual.getName()).isEqualTo("Laptops");
		verify(categoryRepository).findById(1);
	}

	@Test(expected = CategoryNotFoundException.class)
	public void getCategoryById_willThrowNotFound() throws Exception {
		given(categoryRepository.findById(any())).willReturn(empty());
		Category actual = categoryService.getCategoryById(1);
		verify(categoryRepository).findById(1);
	}

	@Test
	public void getCategoryByName_returnsCategory() throws Exception {
		given(categoryRepository.findCategoriesByName("Laptops"))
				.willReturn(of(new Category(1, "Laptops")));
		Category actual = categoryService.getCategoryByName("Laptops");
		assertThat(actual.getId()).isEqualTo(1);
		assertThat(actual.getName()).isEqualTo("Laptops");
		verify(categoryRepository).findCategoriesByName("Laptops");
	}

	@Test(expected = CategoryNotFoundException.class)
	public void getCategoryByName_throwsNotFound() throws Exception {
		given(categoryRepository.findCategoriesByName("Laptops")).willReturn(empty());
		Category actual = categoryService.getCategoryByName("Laptops");
		verify(categoryRepository).findCategoriesByName("Laptops");
	}

	@Test
	public void addCategory_ReturnsAddedCategory() throws Exception {
		Category category = new Category("Laptops");
		given(categoryRepository.save(category))
				.willReturn(new Category(1,"Laptops"));
		Category actual = categoryService.addCategory(category);
		assertThat(actual.getName()).isEqualTo("Laptops");
		verify(categoryRepository).save(category);
	}

	@Test(expected = CategoryAlreadyExistsException.class)
	public void addCategory_willThrowAlreadyExists() throws Exception {
		given(categoryRepository.findCategoriesByName("Laptops"))
				.willReturn(of(new Category(1, "Laptops")));
		Category actual = categoryService.addCategory(new Category(1, "Laptops"));
		verify(categoryRepository).findCategoriesByName("Laptops");
	}
}