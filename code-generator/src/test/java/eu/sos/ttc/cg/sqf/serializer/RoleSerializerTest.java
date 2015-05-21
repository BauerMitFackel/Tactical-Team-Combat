package eu.sos.ttc.cg.sqf.serializer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import eu.sos.ttc.cg.sqf.RoleSerializer;
import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.service.arma.CategoryService;
import eu.sos.ttc.core.service.arma.RoleService;


/**
 * Test class for the {@link eu.sos.ttc.cg.sqf.RoleSerializer}
 * @author BauerMitFackel
 * @see eu.sos.ttc.cg.sqf.RoleSerializer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CoreConfiguration.class,
		PersistenceConfiguration.class
})
public class RoleSerializerTest {


	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RoleService roleService;


	@Test
	public void testSerialize () {

		List<Role> roles = roleService.getAll();
		Role role = roles.get(0);

		RoleSerializer serializer = new RoleSerializer();
		String result = serializer.serialize(role, categoryService.getRootCategories());

		System.out.println(result);
	}
}
