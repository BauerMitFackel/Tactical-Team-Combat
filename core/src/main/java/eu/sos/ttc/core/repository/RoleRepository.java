package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.Role;
import eu.sos.ttc.core.domain.Side;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of roles.
 * @author BauerMitFackel
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {


	public List<Role> findBySideOrderByNameAsc (Side side);
}
