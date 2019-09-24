/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 21/09/19
 * Time: 12:24 PM
 */
package com.ecommerce.category.service;
import java.util.Optional;

import com.ecommerce.category.model.Category;
import com.ecommerce.category.repository.CategoryRepository;
import com.ecommerce.category.util.CategoryAlreadyExistsException;
import com.ecommerce.category.util.CategoryNotFoundException;

import org.springframework.stereotype.Service;

/**
 * Category service to provide services to rest controller
 */
@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	/**
	 * Constructor Dependency injection of {@link CategoryRepository}
	 * @param categoryRepository
	 */
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Get all categories
	 * @return of type {@link Iterable<Category>}
	 */
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * Get category by id
	 * @param id of type {@link Integer}
	 * @return of type {@link Category}
	 * @throws CategoryNotFoundException
	 */
	public Category getCategoryById(int id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(!category.isPresent()) throw new CategoryNotFoundException();
		return category.get();
	}

	/**
	 * Get category by name
	 * @param name of type {@link String}
	 * @return of type {@link Category}
	 * @throws CategoryNotFoundException
	 */
	public Category getCategoryByName(String name) {
		Optional<Category> category = categoryRepository.findCategoriesByName(name);
		if(!category.isPresent()) throw new CategoryNotFoundException();
		return category.get();
	}

	/**
	 * Add a new category to the database
	 * @param cat of type {@link Category}
	 * @return newly added category of type {@link Category}
	 * @throws CategoryAlreadyExistsException
	 */
	public Category addCategory(Category cat) {
		Optional<Category> category = categoryRepository.findCategoriesByName(cat.getName());
		if(category.isPresent()) throw new CategoryAlreadyExistsException();
		return categoryRepository.save(cat);
	}
}
