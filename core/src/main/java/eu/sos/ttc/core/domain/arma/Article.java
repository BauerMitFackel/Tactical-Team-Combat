package eu.sos.ttc.core.domain.arma;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author BauerMitFackel
 */
@Entity
@Table(name = "article")
@IdClass(Article.Id.class)
public class Article {


	@javax.persistence.Id
	@Column(name = "role_id")
	private int roleId;

	@javax.persistence.Id
	@Column(name = "item_id")
	private int itemId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "item_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Item item;

	@Column(name = "discount", unique = false, nullable = false)
	private int discount = 0;

	@Column(name = "available_at_sectors", unique = false, nullable = false)
	private boolean availableAtSectors = false;


	/**
	 * Creates a new article instance using the given role and item.
	 * The price change will be set to 0.
	 * The article won't be available at the sector shops.
	 * @param role The role assigned to the article
	 * @param item the item assigned to the article
	 */
	public static Article create (Role role, Item item) {
		return create(role, item, 0, false);
	}


	/**
	 * Creates a new article instance using the given role, item and price change.
	 * @param role The role assigned to the article
	 * @param item The item assigned to the article
	 * @param priceChange The price change applied to the item price
	 */
	public static Article create (Role role, Item item, int priceChange, boolean availableAtSectors) {

		Article article = new Article();
		article.setRoleId(role.getId());
		article.setItemId(item.getId());
		article.setDiscount(priceChange);
		article.setAvailableAtSectors(availableAtSectors);

		return article;
	}


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Article () {
	}


	private void setRoleId (int roleId) {
		this.roleId = roleId;
	}


	private void setItemId (int itemId) {
		this.itemId = itemId;
	}


	public Role getRole () {
		return role;
	}


	public Item getItem () {
		return item;
	}


	public String getName () {
		return getItem().getName();
	}


	public int getPrice () {
		return getItem().getPrice() - discount;
	}


	public int getDiscount () {
		return discount;
	}


	public void setDiscount (int discount) {
		this.discount = discount;
	}


	public boolean getAvailableAtSectors() {
		return availableAtSectors;
	}


	public void setAvailableAtSectors(boolean availableAtSectors) {
		this.availableAtSectors = availableAtSectors;
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
		if (roleId != article.roleId) {
			return false;
		}
		if (itemId != article.itemId) {
			return false;
		}

		return true;
	}


	@Override
	public int hashCode () {

		int result = roleId;
		result = 31 * result + itemId;
		return result;
	}


	@Override
	public String toString () {
		return String.format("%s{role-id=%d, item-id=%d}", Article.class.getSimpleName(), roleId, itemId);
	}


	/**
	 * Article ID class.
	 */
	public static class Id implements Serializable {


		private int roleId;
		private int itemId;


		public static Id create (int roleId, int itemId) {

			Id id = new Id();
			id.roleId = roleId;
			id.itemId = itemId;

			return id;
		}

		/**
		 * Mandatory empty constructor for JPA.
		 */
		@SuppressWarnings("unused")
		protected Id () {
		}


		@Override
		public boolean equals (Object o) {

			if (o == null) {
				return false;
			}

			if (this == o) {
				return true;
			}

			if (!(o instanceof Id)) {
				return false;
			}

			Id id = (Id) o;
			if (roleId != id.roleId) {
				return false;
			}
			if (itemId != id.itemId) {
				return false;
			}

			return true;
		}


		@Override
		public int hashCode () {

			int result = roleId;
			result = 31 * result + itemId;
			return result;
		}


		@Override
		public String toString () {
			return String.format("%s{role-id=%d, item-id=%d}", Id.class.getSimpleName(), roleId, itemId);
		}
	}
}
