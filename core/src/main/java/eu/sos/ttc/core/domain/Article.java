package eu.sos.ttc.core.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@Column(name = "arma_class_name", unique = true, nullable = false)
	private String armaClassName;

	@Column(name = "price", unique = false, nullable = false)
	private int price;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="group_id")
	private Group group;

	@Column(name = "factory_function", unique = true, nullable = false)
	private String factoryFunction;


	/**
	 * Creates a new article instance using the specified name.
	 * @param name The name of the category
	 */
	public static Article create (String name, String armaClassName) {
		return Article.create(name, armaClassName, -1);
	}


	/**
	 * Creates a new article instance using the specified name and icon.
	 * @param name The name of the category
	 * @param armaClassName The arma class name of the article
	 * @param price The price of the article
	 */
	public static Article create (String name, String armaClassName, int price) {

		Article article = new Article();
		article.setName(name);
		article.setArmaClassName(armaClassName);
		article.setPrice(price);

		return article;
	}


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Article () {
	}


	public int getId () {
		return id;
	}


	public String getName () {
		return name;
	}


	private void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	public String getArmaClassName () {
		return armaClassName;
	}


	private void setArmaClassName (String armaClassName) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("armaClassName must be not null and not empty");
		}

		this.armaClassName = armaClassName;
	}


	public int getPrice () {
		return price;
	}


	private void setPrice (int price) {
		this.price = price;
	}


	public int getMaxAmount () {
		return -1;
	}


	/**
	 * Returns the group of this article.
	 */
	public Group getGroup () {
		return group;
	}


	public String getFactoryFunction () {
		return factoryFunction;
	}
}
