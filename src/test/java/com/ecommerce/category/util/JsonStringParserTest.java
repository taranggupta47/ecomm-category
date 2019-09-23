package com.ecommerce.category.util;

import com.ecommerce.category.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.ecommerce.category.util.JsonStringParser.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class to test Json String parser
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonStringParserTest {

	Category category = new Category(1, "Laptops");

	/**
	 * Pass Category type should return Json with field id and name
	 * @throws Exception
	 */
	@Test
	public void asJsonString_willReturnStringJSON() throws Exception {
		String result = asJsonString(category);
		assertThat(result).isEqualTo("{\"id\":1,\"name\":\"Laptops\"}");
	}
}
