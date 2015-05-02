package eu.sos.ttc.core.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User domain object
 * @author BauerMitFackel
 */
@Entity
@Table(name = "user")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	@SuppressWarnings("unused")
	private long id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@Column(name = "locked", unique = false, nullable = false)
	private boolean locked = true;


	/**
	 * Creates a new user instance using the specified name, email and password.
	 * @param name The name of the user
	 * @param email The email address of the user
	 * @param password The password of the user
	 */
	public static User create (String name, String email, String password) {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);

		return user;
	}


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected User () {
	}


	/**
	 * Returns the id of the user.
	 */
	public long getId () {
		return id;
	}


	/**
	 * Returns the name of the user.
	 */
	public String getName () {
		return name;
	}


	/**
	 * Sets the name of the user.
	 */
	public void setName (String name) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name must be not null and not empty");
		}

		this.name = name;
	}


	/**
	 * Returns the email address of the user.
	 */
	public String getEmail () {
		return email;
	}


	/**
	 * Sets the email address of the user.
	 */
	public void setEmail (String email) {

		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("email must be not null and not empty");
		}

		this.email = email;
	}


	/**
	 * Returns the password of the user.
	 */
	public String getPassword () {
		return password;
	}


	/**
	 * Sets the password of the user.
	 */
	public void setPassword (String password) {

		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("password must be not null and not empty");
		}

		this.password = password;
	}


	/**
	 * Returns {@code true} if this user is locked, {@code false} otherwise
	 */
	public boolean isLocked () {
		return locked;
	}


	/**
	 * Sets the locked state of this user.
	 */
	public void setLocked (boolean locked) {
		this.locked = locked;
	}


	@Override
	public boolean equals (Object o) {

		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof User)) {
			return false;
		}

		User user = (User) o;

		// Compare ID
		if (getId() != user.getId()) {
			return false;
		}

		// Compare Email
		if (!getEmail().equals(user.getEmail())) {
			return false;
		}

		return true;
	}


	@Override
	public int hashCode () {

		int result = (int) (getId() ^ (getId() >>> 32));
		result = 31 * result + getEmail().hashCode();
		return result;
	}


	@Override
	public String toString () {
		return String.format("%s{id=%d, email=%s, name=%s}", User.class.getSimpleName(), getId(), getEmail(), getName());
	}
}
