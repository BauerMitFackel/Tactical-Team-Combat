package eu.sos.ttc.core.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CoreConfiguration.class,
		PersistenceConfiguration.class
})
public class CategoryServiceTest {


	@Autowired
	private CategoryService categoryService;


	@Test
	public void testGetById () {

		int categoryId = 1;

		Category category = categoryService.getById(categoryId);
		assertNotNull(category);
		assertEquals(categoryId, category.getId());
	}


	@Test
	public void testGetRootCategories () {

		List<Category> categories = categoryService.getRootCategories();
		assertNotNull(categories);
		assertTrue(categories.size() > 0);

		for (Category category : categories) {
			assertNull(category.getSupercategory());
		}
	}


	@Test
	public void testGetAll () {

		List<Category> categories = categoryService.getAll();
		assertNotNull(categories);
		assertTrue(categories.size() > 0);
	}
}
