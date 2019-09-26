/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 21/09/19
 * Time: 11:23 AM
 */
package com.ecommerce.category.controller;

import com.ecommerce.category.model.Category;
import com.ecommerce.category.model.CategoryDTO;
import com.ecommerce.category.service.CategoryService;
import com.ecommerce.category.util.CategoryAlreadyExistsException;
import com.ecommerce.category.util.CategoryNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Category Controller - Gives all the APIs to perform CRUD operations on Category database
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;
	private ModelMapper modelMapper;

	/**
	 * Constructor dependency injection of Category Service
	 * @param categoryService of {@link CategoryService}
	 */
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
		this.modelMapper = new ModelMapper();
	}

	/**
	 * Get all categories in the table
	 * @return A list of categories in format of {@link Iterable<Category>}
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<Category>> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}

	/**
	 * Get a category information by its Id
	 * @param id existing id of a category
	 * @return the result of type {@link Category}
	 * @throws CategoryNotFoundException when category not found
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
		return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
	}

	/**
	 * Get a category by its name
	 * @param name existing name of a category
	 * @return the result of type {@link Category}
	 * @throws CategoryNotFoundException when category not found
	 */
	@GetMapping()
	public ResponseEntity<Category> getCategoryByName(@RequestParam("name") String name) {
		return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
	}

	/**
	 * Add a new category
	 * @param category of type {@link Category}
	 * @return the newly added category of type {@link Category}
	 * @throws CategoryAlreadyExistsException when there is already a category of that particular name
	 */
	@PostMapping()
	public ResponseEntity<Category> addCategory(@RequestBody CategoryDTO category) {
		return new ResponseEntity<>(categoryService.addCategory(modelMapper.map(category, Category.class)), HttpStatus.CREATED);
	}

	/**
	 * Exception handler when user is not found
	 * @param e of type {@link CategoryAlreadyExistsException}
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void categoryNotFoundExceptionHandler(CategoryNotFoundException e){
		// implementation yet to be completed
	}

	/**
	 * Exception handler when user information already exists
	 * @param e of type {@link CategoryAlreadyExistsException}
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public void categoryAlreadyExistsExceptionHandler(CategoryAlreadyExistsException e){
		// implementation yet to be completed
	}
}
