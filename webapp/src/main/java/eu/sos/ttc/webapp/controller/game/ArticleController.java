package eu.sos.ttc.webapp.controller.game;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.Map;

import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Item;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.service.arma.ArticleService;
import eu.sos.ttc.core.service.arma.ItemService;
import eu.sos.ttc.core.service.arma.RoleService;
import eu.sos.ttc.webapp.controller.BaseController;
import eu.sos.ttc.webapp.exception.ArticleNotFoundException;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Controller
public class ArticleController extends BaseController {


	public static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);


	@Autowired
	private ArticleService articleService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private RoleService roleService;



	@RequestMapping(value = {"/game/role/{roleId}/articles/{itemId}"}, method = RequestMethod.GET)
	public String getArticle (Principal principal, @PathVariable("roleId") int roleId, @PathVariable("itemId") int itemId, Model model) {

		LOG.debug("Get article ");

		Role role = roleService.getById(roleId);
		Item item = itemService.getById(itemId);

		model.addAttribute("item", item);
		model.addAttribute("role", role);

		Article article = role.getArticleByItemId(itemId);
		if (article != null) {
			model.addAttribute("article", article);
		}

		if (principal != null) {
			return "/fragment/article/article-list-item :: edit";
		}

		return "/fragment/article/article-list-item :: read-only";
	}


	@RequestMapping(value = {"/game/role/{roleId}/articles"}, method = RequestMethod.POST)
	public String createArticle (Principal principal, @PathVariable("roleId") int roleId, @RequestParam("itemId") int itemId, Model model) {

		LOG.debug("Create article ");

		Role role = roleService.getById(roleId);
		Item item = itemService.getById(itemId);
		articleService.create(role, item);

		return getArticle(principal, roleId, itemId, model);
	}


	@RequestMapping(value = {"/game/role/{roleId}/articles/{itemId}"}, method = RequestMethod.DELETE)
	public String deleteArticle (Principal principal, @PathVariable("roleId") int roleId, @PathVariable("itemId") int itemId, Model model) {

		LOG.debug("Delete article ");

		Article article = articleService.getById(Article.Id.create(roleId, itemId));
		if (article != null) {
			articleService.delete(article);
		} else {
			throw new ArticleNotFoundException();
		}

		return getArticle(principal, roleId, itemId, model);
	}


	@RequestMapping(value = {"/game/role/{roleId}/articles/{itemId}"}, method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patchArticle (
			Principal principal,
			@PathVariable("roleId") int roleId,
			@PathVariable("itemId") int itemId,
			@RequestBody Map<String, Object> body) {


		Article article = articleService.getById(Article.Id.create(roleId, itemId));
		if (article != null) {
			int discount = Integer.valueOf(body.get("discount").toString());
			article.setDiscount(discount);
			articleService.save(article);
		} else {
			throw new ArticleNotFoundException();
		}
	}
}
