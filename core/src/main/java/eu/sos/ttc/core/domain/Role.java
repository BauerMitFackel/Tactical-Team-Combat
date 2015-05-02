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
 * Role domain object
 * @author BauerMitFackel
 */
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "description", unique = false, nullable = true)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "side_id")
	private Side side;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "role_article",
			joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")})
	private List<Article> articles = new ArrayList<>();


	/**
	 * Returns the id of this role.
	 */
	public int getId () {
		return id;
	}


	/**
	 * Returns the name of this role.
	 */
	public String getName () {
		return name;
	}


	/**
	 * Sets the name of this role.
	 */
	public void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	/**
	 * Returns the description of this role.
	 */
	public String getDescription () {
		return description;
	}


	/**
	 * Sets the description of this role.
	 */
	public void setDescription (String description) {
		this.description = description;
	}


	/**
	 * Returns the side of this role.
	 */
	public Side getSide () {
		return side;
	}


	/**
	 * Returns the articles assigned to this role.
	 */
	public List<Article> getArticles () {
		return articles;
	}


	/**
	 * Sets the articles for this role.
	 */
	public void setArticles (List<Article> articles) {
		this.articles = articles;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Role)) {
			return false;
		}


		Role role = (Role) o;
		return getId() == role.getId();
	}


	@Override
	public int hashCode () {
		return getId();
	}


	@Override
	public String toString () {
		return Role.class.getSimpleName() + " {id:" + getId() + ", name:" + getName() + "}";
	}
}
