package eu.sos.ttc.webapp.controller.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.Map;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Item;
import eu.sos.ttc.core.service.arma.ItemService;
import eu.sos.ttc.webapp.controller.BaseController;
import eu.sos.ttc.webapp.exception.ArticleNotFoundException;
import eu.sos.ttc.webapp.exception.ItemNotFoundException;


/**
 * @author BauerMitFackel
 */
@Controller
public class ItemController extends BaseController {


	@Autowired
	private ItemService itemService;


	@RequestMapping(value = "/game/item/{itemId}", method = RequestMethod.GET)
	public String getItem (Principal principal, @PathVariable("itemId") int itemId, Model model) {

		if (principal != null) {
			User user = getUser(principal);
			model.addAttribute("user", user);
		}

		Item item = itemService.getById(itemId);
		model.addAttribute("item", item);

		return "/item";
	}


	@RequestMapping(value = {"/game/item/{itemId}"}, method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patchItem (
			Principal principal,
			@PathVariable("itemId") int itemId,
			@RequestBody Map<String, Object> body) {

		Item item = itemService.getById(itemId);
		if (item != null) {

			if (body.containsKey("name")) {
				String name = body.get("name").toString();
				item.setName(name);
			}

			if (body.containsKey("price")) {
				int price = Integer.valueOf(body.get("price").toString());
				item.setPrice(price);
			}

			itemService.update(item);

		} else {
			throw new ItemNotFoundException();
		}
	}
}
