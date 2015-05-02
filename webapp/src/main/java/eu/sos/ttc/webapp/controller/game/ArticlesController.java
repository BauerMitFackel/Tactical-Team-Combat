package eu.sos.ttc.webapp.controller.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import eu.sos.ttc.core.domain.Article;
import eu.sos.ttc.core.repository.ArticleRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Controller
public class ArticlesController {


	@Autowired
	private ArticleRepository articleService;


	@RequestMapping(value = {"/articles"}, method = RequestMethod.GET)
	public String getView (Model model) {

		List<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);

		return "/articles";
	}

}
