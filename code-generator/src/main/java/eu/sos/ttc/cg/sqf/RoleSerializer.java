package eu.sos.ttc.cg.sqf;


import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import eu.sos.ttc.cg.sqf.dto.ArticleDto;
import eu.sos.ttc.cg.sqf.dto.CategoryDto;
import eu.sos.ttc.cg.utils.VelocityHelper;
import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Category;
import eu.sos.ttc.core.domain.arma.Item;
import eu.sos.ttc.core.domain.arma.Role;


/**
 * @author BauerMitFackel
 */
public class RoleSerializer {


	private static final Logger LOG = LoggerFactory.getLogger(RoleSerializer.class);


	public String serialize (Role role, List<Category> categories) {

		VelocityContext context = new VelocityContext();
		context.put("header", createHeader());

		List<ArticleDto> articleDtos = new ArrayList<>();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for (Category category : categories) {
			CategoryDto dto = createCategoryDto(category, role);
			if (!dto.getArticles().isEmpty()) {
				categoryDtos.add(dto);
				articleDtos.addAll(dto.getArticles());
			}
		}

		Collections.sort(articleDtos, new ArticleDto.IdComparator());

		context.put ("articles", articleDtos);
		context.put ("categories", categoryDtos);

		Template template = createTemplate();
		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();
	}


	private String createHeader () {

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			InputStream is = classLoader.getResourceAsStream("sqf/role_header.sqf");
			return IOUtils.toString(is);
		} catch (IOException e) {
			LOG.debug(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}


	private CategoryDto createCategoryDto (Category category, Role role) {

		List<Article> articles = new ArrayList<>();
		for (Item item : category.getItems()) {
			Article article = role.getArticleByItemId(item.getId());
			if (article != null) {
				articles.add(article);
			}
		}


		return new CategoryDto (
				category, articles.stream()
								  .map(ArticleDto::new)
								  .collect(Collectors.toList())
		);
	}


	private Template createTemplate () {

		VelocityEngine velocityEngine = VelocityHelper.createVelocityEngine();
		return velocityEngine.getTemplate("templates/role.vm");
	}


	private class ArticleComparator implements Comparator<Article> {

		@Override
		public int compare (Article left, Article right) {
			return left.getPrice() - right.getPrice();
		}
	}
}
