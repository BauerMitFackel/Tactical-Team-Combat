package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import eu.sos.ttc.core.domain.User;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of users.
 * @author BauerMitFackel
 */
public interface UserRepository extends JpaRepository<User, Long> {


	/**
	 * Retrieves an user by its email address.
	 * @param email The email address of the user
	 */
	User findByEmail (String email);
}
