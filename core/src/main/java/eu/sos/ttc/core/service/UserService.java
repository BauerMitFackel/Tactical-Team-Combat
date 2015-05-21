package eu.sos.ttc.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.repository.UserRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class UserService {


	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


	@Autowired
	private UserRepository repository;


	/**
	 * Returns a user by its id.
	 * @return The user with the given id or {@code null} if none found
	 */
	public User getById (long id) {

		User user = repository.findOne(id);
		if (user == null) {
			String msg = String.format("%s{id=%d} not found", User.class.getSimpleName(), id);
			LOG.debug(msg);
			return null;
		}

		return user;
	}


	/**
	 * Returns a user by its email address.
	 * @return The user with the specified email address or {@code null} if none found
	 */
	public User getByEmail (String email) {

		User user = repository.findByEmail(email);
		if (user == null) {
			String msg = String.format("%s{email=%s} not found", User.class.getSimpleName(), email);
			LOG.debug(msg);
			return null;
		}

		return user;
	}


	/**
	 * Creates a new user using the specified name, e-mail address and password.
	 * <p>The password is stored as a hash value. Here, a BCrypt strong hashing function is used.</p>
	 * @param name The name of the user
	 * @param email The e-mail address of the user
	 * @param password The password of the user
	 * @return The newly created user
	 * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
	 */
	public User create (String name, String email, String password) {

		User user = User.create(name, email, PASSWORD_ENCODER.encode(password));
		return repository.save(user);
	}


	/**
	 * Deletes the user with the specified id.
	 */
	public void delete (Long id) {

		if (!repository.exists(id)) {

			String msg = String.format("%s{id=%d} not found", User.class.getSimpleName(), id);
			LOG.debug(msg);

		} else {

			repository.delete(id);

			String msg = String.format("%s{id=%d} deleted", User.class.getSimpleName(), id);
			LOG.debug(msg);
		}
	}


	/**
	 * Updates the specified user.
	 * <p>Use the returned instance for further operations as the update operation might have changed the user instance completely.</p>
	 * @return The updated user instance, or {@code null} if an update is not possible
	 */
	public User update (User user) {

		if (!repository.exists(user.getId())) {
			String msg = String.format("%s{id=%d} not found", User.class.getSimpleName(), user.getId());
			LOG.debug(msg);
			return null;
		}

		return repository.save(user);
	}
}
