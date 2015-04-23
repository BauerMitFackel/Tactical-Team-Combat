package eu.sos.ttc.sqf.serializer;


import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.transaction.Transactional;

import eu.sos.ttc.core.comparator.ArticleIdComparator;
import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.Article;
import eu.sos.ttc.core.domain.Group;
import eu.sos.ttc.core.service.GroupService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				CoreConfiguration.class,
				PersistenceConfiguration.class
		}
)
@Transactional
public class Tryout {

	@Autowired
	private GroupService groupService;


	@Test
	public void test () {

		List<Group> categories = groupService.getCategories();
		List<Article> articles = new ArrayList<>();
		for (Group category : categories) {
			articles.addAll(category.getArticles(true));
		}

		Collections.sort(articles, new ArticleIdComparator());
		ArticleSerializer serializer = new ArticleSerializer();
		String result = serializer.serialize(articles);

		File file = new File("E:\\articles.sqf");
		try {
			FileUtils.writeStringToFile(file, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n" + result);
	}

}
