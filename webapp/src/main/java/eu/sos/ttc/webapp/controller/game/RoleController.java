package eu.sos.ttc.webapp.controller.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Category;
import eu.sos.ttc.core.domain.arma.Item;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.domain.User;

import eu.sos.ttc.core.service.arma.ArticleService;
import eu.sos.ttc.core.service.arma.CategoryService;
import eu.sos.ttc.core.service.arma.ItemService;
import eu.sos.ttc.core.service.arma.RoleService;
import eu.sos.ttc.core.service.arma.FactionService;
import eu.sos.ttc.core.service.UserService;
import eu.sos.ttc.webapp.controller.BaseController;
import eu.sos.ttc.webapp.exception.ArticleNotFoundException;
import eu.sos.ttc.webapp.exception.ForbiddenException;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Controller
public class RoleController extends BaseController {


	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private FactionService sideService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;


	@RequestMapping(value = {"/game/role/{id}"}, method = RequestMethod.DELETE)
	public String onDeleteRole (Principal principal, @PathVariable int id) {

		Role role = roleService.getById(id);
		roleService.delete(role);

		return "redirect:/game/factions";
	}


	@RequestMapping(value = {"/game/role/{id}"}, method = RequestMethod.GET)
	public String onGetRole (Principal principal, @PathVariable int id, Model model) {

		if (principal != null) {
			User user = getUser(principal);
			model.addAttribute("user", user);
		}

		List<Category> categories = categoryService.getRootCategories();
		model.addAttribute("categories", categories);
		Role role = roleService.getById(id);
		model.addAttribute("role", role);

		return "/role";
	}


	@RequestMapping(value = {"/game/role/{id}"}, method = RequestMethod.PATCH)
	public String onPatchRole (Principal principal, @PathVariable int id, @RequestParam("name") String name, @RequestParam("description") String description) {

		Role role = roleService.getById(id);
		role.setName(name);
		role.setDescription(description);

		roleService.update(role);
		return "redirect:/game/role/" + id;
	}


}
