package eu.sos.ttc.cg.sqf.dto;


import java.util.Comparator;

import eu.sos.ttc.core.domain.arma.Article;


/**
 * @author BauerMitFackel
 */
public class ArticleDto {


	private int id;
	private String className;
	private int price;
	private String factoryFunction;


	public ArticleDto (Article article) {

		this.id = article.getItem().getId() * -1;
		this.className = article.getItem().getArmaClass();
		this.price = article.getPrice();
		this.factoryFunction = article.getItem().getFactoryFunction();
	}


	public int getId () {
		return id;
	}


	public String getClassName () {
		return className;
	}


	public int getPrice () {
		return price;
	}


	public String getFactoryFunction () {
		return factoryFunction;
	}


	public static class IdComparator implements Comparator<ArticleDto> {

		@Override
		public int compare (ArticleDto left, ArticleDto right) {
			return right.getId() - left.getId();
		}
	}
}
