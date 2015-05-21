package eu.sos.ttc.core.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Test class for the {@link eu.sos.ttc.core.service.UserService}
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.service.UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CoreConfiguration.class,
		PersistenceConfiguration.class
})
@Transactional
public class UserServiceTest {


	@Autowired
	private UserService userService;


	@Test
	public void testGetById () {

		User user = userService.getById(1L);
		assertNotNull(user);
		assertEquals(1L, user.getId());
	}


	@Test
	public void testGetByEmail () {

		String email = "john@doe.de";

		User user = userService.getByEmail(email);
		assertNotNull(user);
		assertEquals(email, user.getEmail());
	}


	@Test
	public void testCreate () {
		// TODO: Implement
	}
}
