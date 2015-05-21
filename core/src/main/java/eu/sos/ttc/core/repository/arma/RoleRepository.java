package eu.sos.ttc.core.repository.arma;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.domain.arma.Faction;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of roles.
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.domain.arma.Role
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

	/**
	 * Returns all roles for a given faction.
	 * @param faction The faction for which to get the roles
	 * @return A {@link java.util.List} containing all roles for the given faction
	 */
	public List<Role> findByFaction (Faction faction);

	/**
	 * Returns all roles for a given faction, ordered by the given sort.
	 * @param faction The faction for which to get the roles
	 * @param sort The sort of the roles
	 * @return A {@link java.util.List} containing all roles for the given faction ordered by the given sort
	 */
	public List<Role> findByFaction (Faction faction, Sort sort);


	@Deprecated
	public List<Role> findByFactionOrderByNameAsc (Faction faction);
}
