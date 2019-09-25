package com.ecommerce.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * The main category application bootstrap class
 */

@EnableEurekaClient
@SpringBootApplication
public class CategoryApplication {

	/**
	 * Main function
	 * @param args environment variables
	 */
	public static void main(String[] args) {
		SpringApplication.run(CategoryApplication.class, args);
	}

}
