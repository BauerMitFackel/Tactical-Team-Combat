package eu.sos.ttc.core.service.arma;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.arma.Faction;
import eu.sos.ttc.core.domain.arma.Side;
import eu.sos.ttc.core.service.arma.FactionService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Test class for the {@link eu.sos.ttc.core.service.arma.FactionService}
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.service.arma.FactionService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CoreConfiguration.class,
		PersistenceConfiguration.class
})
public class FactionServiceTest {


	@Autowired
	private FactionService factionService;


	@Test
	public void testGetById () {

		Faction faction = factionService.getById(1);
		assertNotNull(faction);
		assertEquals(1, faction.getId());
	}


	@Test
	public void testGetBySide () {

		List<Faction> factions = factionService.getBySide(Side.WEST);
		assertNotNull(factions);
		assertNotEmpty(factions);
	}


	@Test
	public void testGetAll () {

		List<Faction> factions = factionService.getAll();
		assertNotNull(factions);
		assertNotEmpty(factions);
	}


	private static void assertNotEmpty (List<? extends Object> factions) {
		assertTrue(factions.size() > 0);
	}
}
