package eu.sos.ttc.webapp.controller.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;


import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.domain.arma.Category;
import eu.sos.ttc.core.service.arma.CategoryService;
import eu.sos.ttc.webapp.controller.BaseController;


/**
 * @author BauerMitFackel
 */
@Controller
public class ShopController extends BaseController {


	@Autowired
	private CategoryService categoryService;


	@RequestMapping(value = {"/game/shop"}, method = RequestMethod.GET)
	public String onGetShopView (Principal principal, Model model) {

		if (principal != null) {
			User user = getUser(principal);
			model.addAttribute("user", user);
		}

		List<Category> categories = categoryService.getRootCategories();
		model.addAttribute("categories", categories);

		return "/shop";
	}
}
