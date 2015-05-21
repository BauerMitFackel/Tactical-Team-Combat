package eu.sos.ttc.cg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import eu.sos.ttc.cg.sqf.FileGenerator;
import eu.sos.ttc.cg.sqf.RoleFileGenerator;
import eu.sos.ttc.core.domain.arma.Category;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.service.arma.CategoryService;
import eu.sos.ttc.core.service.arma.RoleService;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Component
@Transactional
public class App {


	//private static final String DIR = "E:\\TTC\\Shop\\Database";
	private static final String DIR = "E:\\Workspace\\Arma\\TacticalTeamCombat\\TacticalTeamCombat.Altis\\SOS\\TTC\\Shop\\Database";


	@Autowired
	private CategoryService categoryService;

	@Autowired
	private RoleService roleService;


	public void run () {
		createFileGenerators().forEach(eu.sos.ttc.cg.sqf.FileGenerator::generateFile);
	}


	private List<FileGenerator> createFileGenerators () {

		List<FileGenerator> fileGenerators = new ArrayList<>();
		List<Category> categories = categoryService.getRootCategories();
		fileGenerators.addAll (
				roleService.getAll()
						   .stream()
						   .map(role -> createRoleFileGenerator(role, categories))
						   .collect(Collectors.toList())
		);

		return fileGenerators;
	}


	private FileGenerator createRoleFileGenerator (Role role, List<Category> categories) {

		File file = new File(DIR + "\\" + role.getFaction().getSide() + "\\" + role.getName() + ".sqf");
		return new RoleFileGenerator(file, role, categories);
	}
}
