package eu.sos.ttc.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.service.UserService;
import eu.sos.ttc.webapp.exception.UserNotFoundException;


/**
 * @author BauerMitFackel
 */
@Controller
public abstract class BaseController {


	@Autowired
	private UserService userService;


	protected User getUser (Principal principal) {
		return getUser(principal, true);
	}


	protected User getUser (Principal principal, boolean throw404) {

		// Principal name is email address
		String email = principal.getName();

		// Get user -> return 404 NOT FOUND if null
		User user = userService.getByEmail(email);
		if (user == null && throw404) {
			throw new UserNotFoundException();
		}

		return user;
	}
}
