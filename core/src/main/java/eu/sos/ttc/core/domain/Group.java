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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Group domain object
 * @author BauerMitFackel
 */
@Entity
@Table(name = "article_group")
public class Group {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "icon", unique = false, nullable = true)
	private String icon;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Group parent;

	@OneToMany(mappedBy="parent")
	private List<Group> subGroups = new ArrayList<>();


	@OneToMany(mappedBy="group")
	private List<Article> articles = new ArrayList<>();

	/**
	 * Creates a new category instance using the specified name.
	 * @param name The name of the category
	 */
	public static Group create (String name) {
		return Group.create(name, null);
	}


	/**
	 * Creates a new category instance using the specified name and icon.
	 * @param name The name of the category
	 * @param icon The icon path
	 */
	public static Group create (String name, String icon) {

		Group category = new Group();
		category.setName(name);
		category.setIcon(icon);

		return category;
	}


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Group () {
	}


	public int getId () {
		return id;
	}


	public String getName () {
		return name;
	}


	public void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	public String getIcon () {
		return icon;
	}


	public void setIcon (String icon) {
		this.icon = icon;
	}


	private Group getParent () {
		return parent;
	}


	private void setParent (Group parent) {
		this.parent = parent;
	}


	private List<Group> getSubGroups () {
		return subGroups;
	}


	private void setSubGroups (List<Group> subGroups) {
		this.subGroups = subGroups;
	}


	public List<Article> getArticles () {
		return articles;
	}


	public List<Article> getArticles (boolean includeSubGroups) {

		List<Article> articles = new ArrayList<>();
		articles.addAll(getArticles());

		if (includeSubGroups) {
			for (Group subGroup : getSubGroups()) {
				articles.addAll(subGroup.getArticles(true));
			}
		}

		return articles;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Group)) {
			return false;
		}


		Group group = (Group) o;
		return getId() == group.getId();
	}


	@Override
	public int hashCode () {
		return (int) (getId() ^ (getId() >>> 32));
	}
}
