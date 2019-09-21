/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 21/09/19
 * Time: 11:23 AM
 */
package com.ecommerce.category.controller;

import java.util.List;

import com.ecommerce.category.model.Category;
import com.ecommerce.category.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Category Controller - Gives all the APIs to perform CRUD operations on Category database
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;

	/**
	 * Constructor dependency injection of Category Service
	 * @param categoryService of {@link CategoryService}
	 */
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * Get all categories in the table
	 * @return A list of categories in format of {@link Iterable<Category>}
	 */
	@GetMapping()
	public ResponseEntity<Iterable<Category>> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}
}
