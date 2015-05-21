package eu.sos.ttc.core.service.arma;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.domain.arma.Faction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Test class for the {@link eu.sos.ttc.core.service.arma.RoleService}
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.service.arma.RoleService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CoreConfiguration.class,
		PersistenceConfiguration.class
})
public class RoleServiceTest {


	@Autowired
	private FactionService factionService;
	@Autowired
	private RoleService roleService;


	@Test
	public void testGetByFaction () {

		List<Faction> factions = factionService.getAll();
		for (Faction faction : factions) {
			List<Role> roles = roleService.getByFaction(faction);
			for (Role role : roles) {
				assertEquals(faction, role.getFaction());
			}
		}
	}


	@Test
	public void testGetAll () {

		List<Role> roles = roleService.getAll();
		assertNotNull(roles);
		assertNotEmpty(roles);
	}


	private static void assertNotEmpty (List<Role> roles) {
		assertTrue(roles.size() > 0);
	}
}
