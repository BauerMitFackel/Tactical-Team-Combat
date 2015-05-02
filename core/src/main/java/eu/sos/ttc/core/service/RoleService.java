package eu.sos.ttc.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.domain.Role;
import eu.sos.ttc.core.domain.Side;
import eu.sos.ttc.core.repository.RoleRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class RoleService {


	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);


	@Autowired
	private RoleRepository repository;


	/**
	 * Returns a role by its id.
	 * @return The role with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Role getById (int id) {
		return repository.findOne(id);
	}


	/**
	 * Returns all roles assigned to the given side.
	 * @return A {@link java.util.List} containing all roles assigned to the given side.
	 */
	public List<Role> getBySide (Side side) {
		return repository.findBySideOrderByNameAsc(side);
	}


	/**
	 * Returns all roles.
	 * @return A {@link java.util.List} containing all roles.
	 */
	public List<Role> getAll () {
		return repository.findAll();
	}


	/**
	 * Updates the specified role.
	 * <p>Use the returned instance for further operations as the update operation might have changed the role instance completely.</p>
	 * @return The updated role instance, or {@code null} if an update is not possible
	 */
	public Role update (Role role) {

		if (!repository.exists(role.getId())) {

			String msg = String.format(role + " not found");
			LOG.debug(msg);

			return null;
		}

		return repository.save(role);
	}
}
