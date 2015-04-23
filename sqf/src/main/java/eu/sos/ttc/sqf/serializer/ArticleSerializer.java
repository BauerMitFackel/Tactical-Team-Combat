package eu.sos.ttc.sqf.serializer;


import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.sos.ttc.core.domain.Article;
import eu.sos.ttc.sqf.utils.VelocityHelper;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
public class ArticleSerializer implements Serializer<List<Article>> {


	public String serialize (List<Article> articles) {

		VelocityContext context = new VelocityContext();
		context.put("functions", getFunctions());
		context.put("articles", getArticleMapList(articles));

		Template template = getTemplate();
		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();
	}


	private String getFunctions () {

		String result = "";

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream("ttc_shop_articles_functions.sqf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}


	private List<Map<String, String>> getArticleMapList (List<Article> articles) {

		ArrayList<Map<String, String>> list = new ArrayList<>();
		for (Article article : articles) {
			Map<String, String> dto = createArticleDto(article);
			list.add(dto);
		}

		return list;
	}


	private Map<String, String> createArticleDto (Article article) {

		Map<String, String> map = new HashMap<>();
		map.put("id", Integer.toString(article.getId() * -1));
		map.put("armaClassName", article.getArmaClassName());
		map.put("price", Integer.toString(article.getPrice()));
		map.put("maxAmount", Integer.toString(article.getMaxAmount()));
		map.put("factoryFunction", article.getFactoryFunction());

		return map;
	}


	private Template getTemplate () {

		VelocityEngine velocityEngine = VelocityHelper.createVelocityEngine();
		return velocityEngine.getTemplate("ttc_shop_articles.vm");
	}
}
