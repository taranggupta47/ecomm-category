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

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findCategoriesByName(String name);
}
