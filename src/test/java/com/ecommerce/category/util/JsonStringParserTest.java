package com.ecommerce.category.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import com.ecommerce.category.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.ecommerce.category.util.JsonStringParser.asJsonString;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class JsonStringParserTest {

	private Category category = new Category(1, "Laptops");

	@Test
	public void constructorPrivate_throwsException()
			throws IllegalStateException,
			NoSuchMethodException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException {
		Constructor<JsonStringParser> constructor = JsonStringParser.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void asJsonString_willReturnStringJSON() throws Exception {
		String result = asJsonString(category);
		assertThat(result).isEqualTo("{\"id\":1,\"name\":\"Laptops\"}");
	}
}
