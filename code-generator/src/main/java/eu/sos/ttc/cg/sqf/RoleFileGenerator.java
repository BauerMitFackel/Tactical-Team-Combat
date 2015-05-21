package eu.sos.ttc.cg.sqf;


import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import eu.sos.ttc.cg.utils.VelocityHelper;
import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Category;
import eu.sos.ttc.core.domain.arma.Role;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
public class RoleFileGenerator implements FileGenerator {


	private static final Logger LOG = LoggerFactory.getLogger(RoleFileGenerator.class);


	private File file;
	private Role role;
	private List<Category> categories;


	public RoleFileGenerator (File file, Role role, List<Category> categories) {

		setFile(file);
		setRole(role);
		setCategories(categories);
	}


	@Override
	public boolean generateFile () {

		RoleSerializer serializer = new RoleSerializer();
		String result = serializer.serialize(role, categories);

		try {

			FileUtils.writeStringToFile(file, result);
			return true;

		} catch (IOException e) {
			LOG.debug(e.getMessage(), e);
			return false;
		}
	}


	private void setFile (File file) {

		if (file == null) {
			throw new IllegalArgumentException("file must be not null");
		}

		this.file = file;
	}


	private void setRole (Role role) {

		if (role == null) {
			throw new IllegalArgumentException("role must be not null");
		}

		this.role = role;
	}


	private void setCategories (List<Category> categories) {

		if (categories == null) {
			throw new IllegalArgumentException("categories must be not null");
		}

		this.categories = categories;
	}
}
