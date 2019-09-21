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
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test cases for Category controller
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	/**
	 * If the {@link CategoryService} return an empty list on getAllCategories()
	 * Then check if the status is OK(200)
	 * @throws Exception
	 */
	@Test
	public void getAllCategories_shouldReturnCategories() throws Exception {
		given(categoryService.getAllCategories()).willReturn(Collections.emptyList());

		mockMvc.perform(get("/categories"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());
	}

}
