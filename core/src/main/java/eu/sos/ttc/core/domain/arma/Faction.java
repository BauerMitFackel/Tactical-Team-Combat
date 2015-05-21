package eu.sos.ttc.core.domain.arma;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import eu.sos.ttc.core.domain.Image;


/**
 * Arma 3 faction object.
 * @author BauerMitFackel
 */
@Entity
@Table(name = "faction")
public class Faction {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "acronym", unique = false, nullable = false)
	private String acronym;

	@Enumerated(EnumType.STRING)
	@Column(name = "side", unique = false, nullable = false)
	private Side side;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flag_id")
	private Image flag;


	/**
	 * Creates a new faction instance using the specified name, acronym and side.
	 * @param name The name of the faction
	 * @param acronym The acronym of the faction
	 * @param side The side of the faction
	 * @return The newly created faction instance
	 * @throws java.lang.IllegalArgumentException If any parameter is {@code null}
	 */
	public static Faction create (String name, String acronym, Side side) {
		return create(name, acronym, side, null);
	}


	/**
	 * Creates a new faction instance using the specified name, acronym, side and flag.
	 * @param name The name of the faction
	 * @param acronym The acronym of the faction
	 * @param side The side of the faction
	 * @param flag The flag of the faction
	 * @return The newly created faction instance
	 * @throws java.lang.IllegalArgumentException If name, acronym or side is {@code null}
	 */
	public static Faction create (String name, String acronym, Side side, Image flag) {

		Faction faction = new Faction();
		faction.setName(name);
		faction.setAcronym(acronym);
		faction.setSide(side);
		faction.setFlag(flag);

		return faction;
	}


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Faction () {
	}


	/**
	 * Returns the id of this side.
	 */
	public int getId () {
		return id;
	}


	/**
	 * Returns the name of this faction.
	 */
	public String getName () {
		return name;
	}


	/**
	 * Sets the name of this faction.
	 * @param name The name to set
	 * @throws java.lang.IllegalArgumentException If {@code name} is {@code null} or {@code empty}
	 */
	public void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	/**
	 * Returns the acronym of this faction.
	 */
	public String getAcronym () {
		return acronym;
	}


	/**
	 * Sets the acronym of this faction.
	 * @param acronym The acronym to set
	 * @throws java.lang.IllegalArgumentException If {@code acronym} is {@code null} or {@code empty}
	 */
	public void setAcronym (String acronym) {

		if (acronym == null || acronym.isEmpty()) {
			throw new IllegalArgumentException("acronym must be not null and not empty");
		}

		this.acronym = acronym;
	}


	/**
	 * Returns the side of this faction.
	 */
	public Side getSide () {
		return side;
	}


	/**
	 * Sets the side of this faction.
	 * @param side The side to set
	 * @throws java.lang.IllegalArgumentException If {@code side} is {@code null}
	 */
	public void setSide (Side side) {

		if (side == null) {
			throw new IllegalArgumentException("side must be not null");
		}

		this.side = side;
	}


	/**
	 * Returns the flag of this faction.
	 * <p>Note: The flag image is lazily fetched</p>
	 */
	public Image getFlag () {
		return flag;
	}


	/**
	 * Sets the flag of this faction.
	 * Setting the flag to {@code null} will delete the current flag.
	 */
	public void setFlag (Image flag) {
		this.flag = flag;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Faction)) {
			return false;
		}


		Faction faction = (Faction) o;
		return getId() == faction.getId();
	}


	@Override
	public int hashCode () {
		return getId();
	}


	@Override
	public String toString () {
		return Faction.class.getSimpleName() + " {id:" + getId() + ", acronym:" + getAcronym() + ", side:" + getSide() + "}";
	}
}
