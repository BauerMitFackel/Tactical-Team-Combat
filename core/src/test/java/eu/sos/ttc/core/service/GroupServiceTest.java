package eu.sos.ttc.core.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.Article;
import eu.sos.ttc.core.domain.Group;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				CoreConfiguration.class,
				PersistenceConfiguration.class
		}
)
@Transactional
public class GroupServiceTest {


	@Autowired
	private GroupService groupService;


	@Test
	public void testGet () {
		Group group = groupService.get(1);
		System.out.println("Group " + group.getName());
	}


	@Test
	public void testGetAll () {

		String prefix = "TTC_SHOP_";

		StringBuilder builder = new StringBuilder();
		List<Group> groups = groupService.getAll();
		Group group = groups.get(0);

		builder.append(prefix).append(group.getName().toLowerCase()).append(" = [\n");

		for (Article article : group.getArticles(true)) {
			builder.append(prefix).append("article_").append(article.getArmaClassName()).append(" = [")
				   .append(article.getId() * -1).append(", ")
				   .append("\"").append(article.getArmaClassName()).append("\"").append(", ")
					.append(article.getPrice())
				   .append("];\n");
		}
		builder.append("];");

		System.out.println(builder.toString());
	}
}
