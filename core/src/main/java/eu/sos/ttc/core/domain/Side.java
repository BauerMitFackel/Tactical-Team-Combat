package eu.sos.ttc.core.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Side domain object
 * @author BauerMitFackel
 */
@Entity
@Table(name = "side")
public class Side {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;


	/**
	 * Returns the id of this side.
	 */
	public int getId () {
		return id;
	}


	/**
	 * Returns the name of this side.
	 */
	public String getName () {
		return name;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Side)) {
			return false;
		}


		Side side = (Side) o;
		return getId() == side.getId();
	}


	@Override
	public int hashCode () {
		return getId();
	}


	@Override
	public String toString () {
		return Side.class.getSimpleName() + " {id:" + getId() + ", name:" + getName() + "}";
	}
}
