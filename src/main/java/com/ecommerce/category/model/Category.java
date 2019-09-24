/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 21/09/19
 * Time: 12:21 PM
 */
package com.ecommerce.category.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Category entity mapper POJO to map category table from database to this application
 */
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;

	/**
	 * Empty constructor for hibernate
	 */
	public Category() {
	}

	/**
	 * Contructor to build object of {@link Category}
	 * @param name of type {@link String}
	 */
	public Category(String name) {
		this.name = name;
	}

	/**
	 * Constructor for testing purpose
	 * @param id of type {@link Integer}
	 * @param name of type {@link String}
	 */
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
