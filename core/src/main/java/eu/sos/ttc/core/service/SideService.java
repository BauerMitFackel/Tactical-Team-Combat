package eu.sos.ttc.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.sos.ttc.core.domain.Side;
import eu.sos.ttc.core.repository.SideRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class SideService {


	public static final int SIDE_ID_WEST = 1;
	public static final int SIDE_ID_EAST = 2;
	public static final int SIDE_ID_GUER = 3;


	@Autowired
	private SideRepository repository;


	/**
	 * Returns a side by its id.
	 * @return The side with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Side getById (int id) {
		return repository.findOne(id);
	}
}
