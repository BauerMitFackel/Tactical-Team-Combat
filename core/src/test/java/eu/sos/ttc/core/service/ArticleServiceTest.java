package eu.sos.ttc.core.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.Article;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CoreConfiguration.class,
		PersistenceConfiguration.class
})
@Transactional
public class ArticleServiceTest {


	@Autowired
	private ArticleService articleService;


	@Test
	public void testGetById () {

		int articleId = 1;

		Article article = articleService.getById(articleId);
		assertNotNull(article);
		assertEquals(articleId, article.getId());
	}


	@Test
	public void testGetAll () {

		List<Article> articles = articleService.getAll();
		assertNotNull(articles);
		assertTrue(articles.size() > 0);
	}
}
