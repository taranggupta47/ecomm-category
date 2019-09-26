/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 21/09/19
 * Time: 11:20 AM
 */
package com.ecommerce.category.controller;

import java.util.Collections;

import com.ecommerce.category.model.Category;
import com.ecommerce.category.service.CategoryService;
import com.ecommerce.category.util.CategoryAlreadyExistsException;
import com.ecommerce.category.util.CategoryNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.ecommerce.category.util.JsonStringParser.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	@Test
	public void getAllCategories_willReturnCategories() throws Exception {
		given(categoryService.getAllCategories()).willReturn(Collections.emptyList());

		mockMvc.perform(get("/categories/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());
	}

	@Test
	public void getCategoryById_willReturnCategory() throws Exception {
		given(categoryService.getCategoryById(1))
				.willReturn(new Category("Laptops"));

		mockMvc.perform(get("/categories/{id}", 1l))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty());
	}

	@Test
	public void getCategoryById_willThrowNotFound() throws Exception {
		given(categoryService.getCategoryById(1l))
				.willThrow(CategoryNotFoundException.class);

		mockMvc.perform(get("/categories/{id}", 1l))
				.andExpect(status().isNotFound());
	}

	@Test
	public void getCategoryByName_willReturnCategory() throws Exception {
		given(categoryService.getCategoryByName("Laptops"))
				.willReturn(new Category("Laptops"));

		mockMvc.perform(get("/categories?name={name}", "Laptops"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty());
	}

	@Test
	public void getCategoryByName_willThrowNotFound() throws Exception {
		given(categoryService.getCategoryByName("Laptops"))
				.willThrow(CategoryNotFoundException.class);

		mockMvc.perform(get("/categories?name={name}", "Laptops"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void addCategory_willReturnNewlyAddedCategory() throws Exception {
		given(categoryService.addCategory(new Category("Laptops")))
				.willReturn(new Category("Laptops"));

		mockMvc.perform(post("/categories")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(new Category("Laptops"))))
				.andExpect(status().isCreated());
	}

	@Test
	public void addCategory_willThrowAlreadyExists() throws Exception {
		given(categoryService.addCategory(any()))
				.willThrow(CategoryAlreadyExistsException.class);

		mockMvc.perform(post("/categories")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(new Category("Laptops"))))
				.andExpect(status().isConflict());
	}

}
