package eu.sos.ttc.core.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Category domain object
 * @author BauerMitFackel
 */
@Entity
@Table(name = "category")
public class Category {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "icon", unique = false, nullable = true)
	private String icon;

	@Column(name = "rank", unique = false, nullable = false)
	private int rank = -1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_category_id")
	private Category parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private List<Category> subcategories = new ArrayList<>();

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private List<Article> articles = new ArrayList<>();


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Category () {
	}


	/**
	 * Returns the id of this category.
	 */
	public int getId () {
		return id;
	}


	/**
	 * Returns the name of this category.
	 */
	public String getName () {
		return name;
	}


	/**
	 * Returns the icon of this category.
	 */
	public String getIcon () {
		return icon;
	}


	/**
	 * Returns the rank of this category.
	 */
	public int getRank () {
		return rank;
	}


	/**
	 * Returns the supercategory of this category.
	 */
	public Category getSupercategory () {
		return parent;
	}


	/**
	 * Returns all subcategories of this category.
	 */
	public List<Category> getSubcategories () {
		return subcategories;
	}


	/**
	 * Returns all articles from this category and all subcategories.
	 */
	public List<Article> getArticles () {
		return getArticles(true);
	}


	private List<Article> getArticles (boolean includeSubcategories) {

		List<Article> articles = new ArrayList<>();
		articles.addAll(this.articles);

		if (includeSubcategories) {
			for (Category subcategory : getSubcategories()) {
				articles.addAll(subcategory.getArticles(true));
			}
		}

		return articles;
	}


	/**
	 * Returns true if the given article is a member of this category
	 * or its subcategories.
	 */
	public boolean containsArticle (Article article) {

		if (getArticles(false).contains(article)) {
			return true;
		} else {

			for (Category subcategory : getSubcategories()) {
				if (subcategory.containsArticle(article)) {
					return true;
				}
			}
		}

		return false;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Category)) {
			return false;
		}


		Category category = (Category) o;
		return getId() == category.getId();
	}


	@Override
	public int hashCode () {
		return getId();
	}


	@Override
	public String toString () {
		return Category.class.getSimpleName() + " {id:" + getId() + ", name:" + getName() + "}";
	}
}
