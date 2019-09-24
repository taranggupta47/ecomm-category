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

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category getCategoryById(int id) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepository.findById(id);
		if(!category.isPresent()) throw new CategoryNotFoundException();
		return category.get();
	}

	public Category getCategoryByName(String name) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepository.findCategoriesByName(name);
		if(!category.isPresent()) throw new CategoryNotFoundException();
		return category.get();
	}

	public Category addCategory(Category cat) throws CategoryAlreadyExistsException {
		Optional<Category> category = categoryRepository.findCategoriesByName(cat.getName());
		if(category.isPresent()) throw new CategoryAlreadyExistsException();
		return categoryRepository.save(cat);
	}
}
