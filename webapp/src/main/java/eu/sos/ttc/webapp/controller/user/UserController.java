package eu.sos.ttc.webapp.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.service.UserService;
import eu.sos.ttc.webapp.exception.UserNotFoundException;


/**
 * @author BauerMitFackel
 */
@Controller
public class UserController {


	@Autowired
	private UserService userService;


	@RequestMapping(value = "/users/me", method = RequestMethod.GET)
	public String getView (Principal principal, Model model) {

		User user = getUser(principal);
		model.addAttribute("user", user);

		return "/user";
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
