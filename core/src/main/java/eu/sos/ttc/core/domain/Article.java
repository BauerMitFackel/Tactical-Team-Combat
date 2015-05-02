package eu.sos.ttc.core.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Article domain object
 * @author BauerMitFackel
 */
@Entity
@Table(name = "article")
public class Article {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "price", unique = false, nullable = false)
	private int price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "article_side",
			joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "side_id", referencedColumnName = "id")})
	private List<Side> sides = new ArrayList<>();


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Article () {
	}


	/**
	 * Returns the id of this article.
	 */
	public int getId () {
		return id;
	}


	/**
	 * Returns the name of this article.
	 */
	public String getName () {
		return name;
	}


	/**
	 * Returns the price of this article.
	 */
	public int getPrice () {
		return price;
	}


	/**
	 * Returns the category of this article.
	 */
	public Category getCategory () {
		return category;
	}


	/**
	 * Returns the sides of this article.
	 */
	public List<Side> getSides () {
		return sides;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Article)) {
			return false;
		}


		Article article = (Article) o;
		return getId() == article.getId();
	}


	@Override
	public int hashCode () {
		return getId();
	}


	@Override
	public String toString () {
		return String.format("%s{id=%d, name=%s}", Article.class.getSimpleName(), getId(), getName());
	}
}
