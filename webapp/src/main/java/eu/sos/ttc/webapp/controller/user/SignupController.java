package eu.sos.ttc.webapp.controller.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.service.UserService;


/**
 * @author BauerMitFackel
 */
@Controller
public class SignupController {


	private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);


	@Autowired
	private UserService userService;


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String onSignup (Model model) {
		return "/signup";
	}


	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String onSignup (@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {

		User user = userService.create(name, email, password);
		LOG.info(user + " has signed up");

		return "redirect:/users/me";
	}
}
