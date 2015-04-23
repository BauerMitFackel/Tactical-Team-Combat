package eu.sos.ttc.sqf.serializer;


import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;
import eu.sos.ttc.core.domain.Group;
import eu.sos.ttc.core.service.GroupService;

import static junit.framework.Assert.fail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				CoreConfiguration.class,
				PersistenceConfiguration.class
		}
)
@Transactional
public class CategorySerializerTest {


	private static final Logger LOG = LoggerFactory.getLogger(CategorySerializerTest.class);


	@Autowired
	private GroupService groupService;


	@Test
	public void test () {

		List<Group> categories = groupService.getCategories();

		CategorySerializer serializer = new CategorySerializer();
		String result = serializer.serialize(categories);

		System.out.println(result);
		/*
		File file = new File("E:\\categories.sqf");
		try {
			FileUtils.writeStringToFile(file, result);
		} catch (IOException e) {
			LOG.debug(e.getMessage(), e);
			fail(e.getClass().getName());
		}
		*/
	}
}
