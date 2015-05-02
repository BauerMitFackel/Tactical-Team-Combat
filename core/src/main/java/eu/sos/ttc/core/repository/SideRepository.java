package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import eu.sos.ttc.core.domain.Side;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of sides.
 * @author BauerMitFackel
 */
public interface SideRepository extends JpaRepository<Side, Integer> {

}
