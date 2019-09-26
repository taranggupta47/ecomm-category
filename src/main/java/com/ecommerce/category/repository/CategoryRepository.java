/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 23/09/19
 * Time: 11:13 PM
 */
package com.ecommerce.category.repository;

import java.util.Optional;

import com.ecommerce.category.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Category Repository to interact with database of type {@link JpaRepository}
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	/**
	 * find category by name
	 * @param name	of type {@link String}
	 * @return an {@link Optional} of {@link Category}
	 */
	Optional<Category> findCategoriesByName(String name);
}
