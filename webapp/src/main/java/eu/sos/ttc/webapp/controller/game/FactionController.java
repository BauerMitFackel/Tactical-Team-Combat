package eu.sos.ttc.webapp.controller.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.transaction.Transactional;

import eu.sos.ttc.core.domain.Image;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.domain.arma.Faction;
import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.service.arma.RoleService;
import eu.sos.ttc.core.service.arma.FactionService;
import eu.sos.ttc.webapp.controller.BaseController;


/**
 * @author BauerMitFackel
 */
@Controller
public class FactionController extends BaseController {


	@Autowired
	private FactionService factionService;
	@Autowired
	private RoleService roleService;



	@RequestMapping(value = {"/game/factions"}, method = RequestMethod.GET)
	public String getFactions (Principal principal, Model model) {

		// Add user to model if the principal is not null
		if (principal != null) {
			User user = getUser(principal);
			model.addAttribute("user", user);
		}

		// Add factions to model
		Map<Faction, List<Role>> factions = new TreeMap<>(new FactionComparator());
		for (Faction faction : factionService.getAll()) {
			List<Role> roles = roleService.getByFaction(faction);
			if (!roles.isEmpty()) {
				factions.put(faction, roles);
			}
		}
		model.addAttribute("factions", factions);

		return "/factions";
	}


	@RequestMapping(value = {"/game/factions/{id}/flag"}, method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFactionFlag (@PathVariable int id) {

		Faction faction = factionService.getById(id);
		Image flag = faction.getFlag();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(flag.getType()));
		headers.setContentLength(flag.getBytes().length);

		return new ResponseEntity<>(flag.getBytes(), headers, HttpStatus.OK);
	}


	private class FactionComparator implements Comparator<Faction> {

		@Override
		public int compare (Faction left, Faction right) {
			return left.getName().compareToIgnoreCase(right.getName());
		}
	}
}
