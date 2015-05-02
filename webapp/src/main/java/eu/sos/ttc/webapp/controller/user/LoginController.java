package eu.sos.ttc.webapp.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author BauerMitFackel
 */
@Controller
public class LoginController {


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String  onLogin () {
		return "/login";
	}
}
