package eu.sos.ttc.core.service.arma;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.domain.arma.Item;
import eu.sos.ttc.core.repository.arma.ItemRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class ItemService {


	private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);


	@Autowired
	private ItemRepository repository;


	/**
	 * Returns all items.
	 * @return A {@link java.util.List} containing all items
	 */
	public List<Item> getAll () {
		return repository.findAll();
	}


	/**
	 * Returns a item by its id.
	 * @return The item with the given id or {@code null} if none found
	 */
	public Item getById (int id) {

		Item item = repository.findOne(id);
		if (item == null) {
			String msg = String.format("%s{id=%d} not found", Item.class.getSimpleName(), id);
			LOG.debug(msg);
			return null;
		}

		return item;
	}


	/**
	 * Updates the specified item.
	 * <p>Use the returned instance for further operations as the update operation might have changed the item instance completely.</p>
	 * @return The updated item instance, or {@code null} if an update is not possible
	 */
	public Item update (Item item) {

		if (!repository.exists(item.getId())) {
			String msg = String.format("%s{id=%d} not found", Item.class.getSimpleName(), item.getId());
			LOG.debug(msg);
			return null;
		}

		return repository.save(item);
	}
}
