package eu.sos.ttc.core.domain.arma;


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
import javax.persistence.OrderBy;
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

	@Column(name = "arma_icon", unique = false, nullable = true)
	private String armaIcon;

	@Column(name = "rank", unique = false, nullable = false)
	private int rank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supercategory_id")
	private Category supercategory;

	@OneToMany(mappedBy = "supercategory", fetch = FetchType.EAGER)
	@OrderBy("rank ASC")
	private List<Category> subcategories = new ArrayList<>();

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	@OrderBy("price ASC, name ASC")
	private List<Item> items = new ArrayList<>();


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
	 * Sets the name of this category.
	 * @param name The name to set
	 * @throws java.lang.IllegalArgumentException If {@code name} is null or empty
	 */
	public void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	/**
	 * Returns the Arma 3 icon file path.
	 */
	public String getArmaIcon () {
		return armaIcon;
	}


	/**
	 * Sets the Arma 3 icon file path.
	 * @param armaIcon The Arma 3 icon file path to set
	 */
	public void setArmaIcon (String armaIcon) {
		this.armaIcon = armaIcon;
	}


	/**
	 * Returns the rank of this category.
	 */
	public int getRank () {
		return rank;
	}


	/**
	 * Sets the rank of this category.
	 * @param rank The rank to set
	 */
	public void setRank (int rank) {
		this.rank = rank;
	}


	/**
	 * Returns the supercategory of this category.
	 */
	public Category getSupercategory () {
		return supercategory;
	}


	/**
	 * Returns all subcategories of this category.
	 */
	public List<Category> getSubcategories () {
		return subcategories;
	}


	/**
	 * Returns all items from this category and all subcategories.
	 */
	public List<Item> getItems () {

		List<Item> items = new ArrayList<>();
		items.addAll(this.items);

		for (Category subcategory : getSubcategories()) {
			items.addAll(subcategory.getItems());
		}

		return items;
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
		return String.format("%s{id=%d, name=%s}", Category.class.getSimpleName(), getId(), getName());
	}
}

