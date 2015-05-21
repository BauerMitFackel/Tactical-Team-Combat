package eu.sos.ttc.core.service.arma;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.domain.arma.Faction;
import eu.sos.ttc.core.repository.arma.RoleRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class RoleService {


	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

	private static final Sort SORT_BY_NAME_ASC = new Sort(Sort.Direction.ASC, "name");


	@Autowired
	private RoleRepository repository;
	@Autowired
	private ArticleService articleService;


	/**
	 * Returns all roles.
	 * @return A {@link java.util.List} containing all roles.
	 */
	public List<Role> getAll () {
		return repository.findAll(SORT_BY_NAME_ASC);
	}


	/**
	 * Returns a role by its id.
	 * @return The role with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Role getById (int id) {
		return repository.findOne(id);
	}


	/**
	 * Returns all roles assigned to the given faction.
	 * @return A {@link java.util.List} containing all roles assigned to the given side.
	 */
	public List<Role> getByFaction (Faction faction) {
		return repository.findByFaction(faction, SORT_BY_NAME_ASC);
	}


	/**
	 * Creates a new role.
	 * TODO: Implement
	 * @return The newly created role
	 */
	public Role create () {

		LOG.debug("NOT IMPLEMENTED: " + Role.class.getSimpleName() + "#create(...)");
		return null;
	}


	/**
	 * Deletes the given role
	 * TODO: Implement
	 */
	public void delete (Role role) {
		LOG.debug("NOT IMPLEMENTED: " + Role.class.getSimpleName() + "#delete(role)");
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

		role.getArticles().forEach(articleService::save);
		articleService.getByRole(role)
					  .stream()
					  .filter(article -> !role.getArticles().contains(article))
					  .forEach(articleService::delete);

		role.setArticles(null);
		return repository.save(role);
	}
}
