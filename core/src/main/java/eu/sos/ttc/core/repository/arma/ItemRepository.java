package eu.sos.ttc.core.repository.arma;


import org.springframework.data.jpa.repository.JpaRepository;

import eu.sos.ttc.core.domain.arma.Item;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of items.
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.domain.arma.Item
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
