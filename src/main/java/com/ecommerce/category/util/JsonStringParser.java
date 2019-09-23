/**
 * Created by IntelliJ IDEA.
 * User: akshay
 * Date: 22/09/19
 * Time: 11:54 AM
 */
package com.ecommerce.category.util;

import com.ecommerce.category.model.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json String Parser
 */
public class JsonStringParser {
	/**
	 * A static class which will take in an object and return json format of it
	 * @param obj of any typelike {@link Category}
	 * @return a string of JSON type
	 * @throws JsonProcessingException
	 */
	public static String asJsonString(final Object obj) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonContent = mapper.writeValueAsString(obj);
		return jsonContent;
	}
}
