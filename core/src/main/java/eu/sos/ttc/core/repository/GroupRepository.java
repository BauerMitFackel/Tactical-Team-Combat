package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.Group;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of groups.
 * @author BauerMitFackel
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

	List<Group> findByParentIsNull ();
}
