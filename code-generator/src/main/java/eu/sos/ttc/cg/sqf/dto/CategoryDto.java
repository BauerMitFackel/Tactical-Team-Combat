package eu.sos.ttc.cg.sqf.dto;


import java.util.List;

import eu.sos.ttc.core.domain.arma.Category;


/**
 * @author BauerMitFackel
 */
public class CategoryDto {


	private int id;
	private String name;
	private String icon;
	private List<ArticleDto> articles;


	public CategoryDto (Category category, List<ArticleDto> articles) {

		this.id = category.getId();
		this.name = category.getName();
		this.icon = category.getArmaIcon();
		this.articles = articles;
	}


	public int getId () {
		return id;
	}


	public String getName () {
		return name;
	}


	public String getIcon () {
		return icon;
	}


	public List<ArticleDto> getArticles () {
		return articles;
	}
}
