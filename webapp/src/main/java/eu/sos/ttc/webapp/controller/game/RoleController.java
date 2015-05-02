package eu.sos.ttc.webapp.controller.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import eu.sos.ttc.core.domain.Article;
import eu.sos.ttc.core.domain.Category;
import eu.sos.ttc.core.domain.Role;
import eu.sos.ttc.core.domain.Side;
import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.service.ArticleService;
import eu.sos.ttc.core.service.CategoryService;
import eu.sos.ttc.core.service.RoleService;
import eu.sos.ttc.core.service.SideService;
import eu.sos.ttc.core.service.UserService;
import eu.sos.ttc.webapp.exception.UserNotFoundException;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Controller
public class RoleController {


	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SideService sideService;

	@Autowired
	private UserService userService;


	@RequestMapping(value = {"/roles"}, method = RequestMethod.GET)
	public String onGetRoles (Principal principal, Model model) {

		User user = getUser(principal);
		model.addAttribute("user", user);

		Side west = sideService.getById(SideService.SIDE_ID_WEST);
		List<Role> rolesWest = roleService.getBySide(west);
		model.addAttribute("rolesWest", rolesWest);
		Side guer = sideService.getById(SideService.SIDE_ID_GUER);
		List<Role> rolesGuer = roleService.getBySide(guer);
		model.addAttribute("rolesGuer", rolesGuer);

		return "/roles";
	}


	@RequestMapping(value = {"/role/{id}"}, method = RequestMethod.GET)
	public String onGetRole (Principal principal, @PathVariable int id, Model model) {

		User user = getUser(principal);
		model.addAttribute("user", user);

		List<Article> articles = articleService.getAll();
		model.addAttribute("articles", articles);
		List<Category> categories = categoryService.getRootCategories();
		model.addAttribute("categories", categories);
		Role role = roleService.getById(id);
		model.addAttribute("role", role);

		return "/role";
	}


	@RequestMapping(value = {"/role/{id}"}, method = RequestMethod.PATCH)
	public String onPatchRole (Principal principal, @PathVariable int id, @RequestParam("name") String name, @RequestParam("description") String description) {

		Role role = roleService.getById(id);
		role.setName(name);
		role.setDescription(description);

		roleService.update(role);
		return "redirect:/role/" + id;
	}


	@RequestMapping(value = {"/role/{id}/articles"}, method = RequestMethod.POST)
	public String onPostArticles (Principal principal, @PathVariable int id, @RequestParam(value = "articles", required = true) List<Integer> articleIds) {

		Role role = roleService.getById(id);

		List<Article> articles = new ArrayList<>(articleIds.size());
		for (int articleId : articleIds) {
			Article article = articleService.getById(articleId);
			articles.add(article);
		}

		role.setArticles(articles);
		roleService.update(role);
		return "redirect:/role/" + id;
	}


	private User getUser (Principal principal) {

		// Principal name is email address
		String email = principal.getName();

		// Get user -> return 404 NOT FOUND if null
		User user = userService.getByEmail(email);
		if (user == null) {
			throw new UserNotFoundException();
		}

		return user;
	}
}
