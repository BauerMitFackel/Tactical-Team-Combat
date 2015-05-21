package eu.sos.ttc.core.repository.arma;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.arma.Faction;
import eu.sos.ttc.core.domain.arma.Side;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of factions.
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.domain.arma.Faction
 */
public interface FactionRepository extends JpaRepository<Faction, Integer> {


	/**
	 * Returns all factions for the given side.
	 * @param side The side of the factions
	 * @return A {@link java.util.List} containing all factions for the given side
	 */
	List<Faction> findBySide (Side side);


	/**
	 * Returns all factions for the given side, ordered by the given sort.
	 * @param side The side of the factions
	 * @param sort The sort of the factions
	 * @return A {@link java.util.List} containing all factions for the given side ordered by the given sort
	 */
	List<Faction> findBySide (Side side, Sort sort);
}