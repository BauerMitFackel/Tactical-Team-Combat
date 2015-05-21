package eu.sos.ttc.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

import eu.sos.ttc.core.domain.User;


/**
 * @author BauerMitFackel
 */
@Controller
public class HomeController extends BaseController {


	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String onGetHomeView (Principal principal, Model model) {

		if (principal != null) {
			User user = getUser(principal);
			model.addAttribute("user", user);
		}

		return "/home";
	}
}
