package eu.sos.ttc.core.service.arma;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.domain.arma.Faction;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.domain.arma.Side;
import eu.sos.ttc.core.repository.arma.FactionRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class FactionService {


	private static final Logger LOG = LoggerFactory.getLogger(FactionService.class);

	private static final Sort SORT_BY_NAME_ASC = new Sort(Sort.Direction.ASC, "name");
	private static final Sort SORT_BY_SIDE_NAME_ASC = new Sort(Sort.Direction.ASC, "side", "name");


	@Autowired
	private FactionRepository repository;


	/**
	 * Returns all factions.
	 * @return A {@link java.util.List} containing all factions.
	 */
	public List<Faction> getAll () {
		return repository.findAll(SORT_BY_SIDE_NAME_ASC);
	}


	/**
	 * Returns a faction by its id.
	 * @return The faction with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Faction getById (int id) {
		return repository.findOne(id);
	}


	/**
	 * Returns a faction by its id.
	 * @param id The id of the faction to get
	 * @param initialize Flag determining if lazy attributes should be initialized
	 * @return The faction with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Faction getById (int id, boolean initialize) {

		/*
		Faction faction = repository.findOne(id);
		if (initialize) {
			faction.getFlag().getBytes();
		}
		*/

		return null;
	}


	/**
	 * Returns all factions for a given side.
	 * @param side The side of the factions to retrieve
	 * @return A {@link java.util.List} containing all factions of the given side
	 */
	public List<Faction> getBySide (Side side) {
		return repository.findBySide(side, SORT_BY_NAME_ASC);
	}


	/**
	 * Creates a new faction.
	 * TODO: Implement
	 * @return The newly created faction
	 */
	public Role create () {

		LOG.debug("NOT IMPLEMENTED: " + FactionService.class.getSimpleName() + "#create(...)");
		return null;
	}


	/**
	 * Deletes the given role
	 * TODO: Implement
	 */
	public void delete (Faction faction) {
		LOG.debug("NOT IMPLEMENTED: " + FactionService.class.getSimpleName() + "#delete(faction)");
	}


	/**
	 * Updates the given faction.
	 * <p>Use the returned instance for further operations as the update operation might have changed the faction instance completely.</p>
	 * @return The updated faction instance, or {@code null} if an update is not possible
	 */
	public Faction update (Faction faction) {

		if (!repository.exists(faction.getId())) {
			String msg = String.format(faction + " not found");
			LOG.debug(msg);
			return null;
		}

		return repository.save(faction);
	}
}
