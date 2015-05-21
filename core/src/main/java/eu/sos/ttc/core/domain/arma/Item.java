package eu.sos.ttc.core.domain.arma;


import java.util.Set;
import java.util.TreeSet;

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
 * Item domain object.
 * @author BauerMitFackel
 */
@Entity
@Table(name = "item")
public class Item {


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
			name = "item_faction_association",
			joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "faction_id", referencedColumnName = "id")})
	private Set<Faction> factions = new TreeSet<>();

	@Column(name = "arma_class", unique = true, nullable = false)
	private String armaClass;

	@Column(name = "factory_function", unique = false, nullable = false)
	private String factoryFunction;

	@Column(name = "metadata", unique = false, nullable = true)
	private String metadata;


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Item () {
	}


	/**
	 * Returns the id of this article.
	 */
	public int getId () {
		return id;
	}


	/**
	 * Returns the name of this item.
	 */
	public String getName () {
		return name;
	}


	/**
	 * Sets the name of this item.
	 * @param name The name to set
	 * @throws java.lang.IllegalArgumentException If name is {@code null}
	 */
	public void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	/**
	 * Returns the price of this item.
	 */
	public int getPrice () {
		return price;
	}


	/**
	 * Sets the price of this item.
	 * @param price The price to set
	 */
	public void setPrice (int price) {
		this.price = price;
	}


	/**
	 * Returns the category of this item.
	 */
	public Category getCategory () {
		return category;
	}


	/**
	 * Sets the category of this item.
	 * @param category The category to set
	 * @throws java.lang.IllegalArgumentException If category is {@code null}
	 */
	public void setCategory (Category category) {

		if (category == null) {
			throw new IllegalArgumentException("category must be not null");
		}

		this.category = category;
	}


	/**
	 * Returns the sides to which this item belongs.
	 */
	public Set<Faction> getFactions () {
		return factions;
	}


	/**
	 * Returns the sides to which this item belongs.
	 * @param factions The sides to set
	 * @throws java.lang.IllegalArgumentException If {@code sides} is null or empty
	 */
	public void setFactions (Set<Faction> factions) {

		if (factions == null || factions.isEmpty()) {
			throw new IllegalArgumentException("sides must be not null and not empty");
		}

		this.factions = factions;
	}


	/**
	 * Returns the Arma 3 class name of this item.
	 */
	public String getArmaClass () {
		return armaClass;
	}


	/**
	 * Sets the Arma 3 class name of this item.
	 * @param armaClass The Arma 3 class name to set
	 * @throws java.lang.IllegalArgumentException If {@code armaClass} is null or empty
	 */
	public void setArmaClass (String armaClass) {

		if (armaClass == null || armaClass.isEmpty()) {
			throw new IllegalArgumentException("armaClass must be not null and not empty");
		}

		this.armaClass = armaClass;
	}


	/**
	 * Returns the ttc shop factory function name.
	 */
	public String getFactoryFunction () {
		return factoryFunction;
	}


	public void setFactoryFunction (String factoryFunction) {
		this.factoryFunction = factoryFunction;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Item)) {
			return false;
		}


		Item item = (Item) o;
		return getId() == item.getId();
	}


	@Override
	public int hashCode () {
		return getId();
	}


	@Override
	public String toString () {
		return String.format("%s{id=%d, name=%s}", Item.class.getSimpleName(), getId(), getName());
	}
}
