package eu.sos.ttc.core.service.arma;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Item;
import eu.sos.ttc.core.domain.arma.Role;
import eu.sos.ttc.core.repository.arma.ArticleRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class ArticleService {


	private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);


	@Autowired
	private ArticleRepository repository;


	/**
	 * Returns a article by its id.
	 * @return The article with the given id or {@code null} if none found
	 */
	public Article getById (Article.Id id) {

		Article article = repository.findOne(id);
		if (article == null) {
			String msg = String.format("%s{id=%s} not found", Article.class.getSimpleName(), id);
			LOG.debug(msg);
			return null;
		}

		return article;
	}


	public List<Article> getByRole (Role role) {
		return repository.findByRole(role);
	}


	/**
	 * Creates a new article using the specified roleId and itemId.
	 * @return The newly created article
	 */
	public Article create (Role role, Item item) {

		Article article = Article.create(role, item);
		return repository.save(article);
	}

	/**
	 * Deletes a given article.
	 * @param article the article to delete
	 * @throws java.lang.IllegalArgumentException If {@code article} is {@code null}
	 */
	public void delete (Article article) {
		repository.delete(article);
	}


	/**
	 * Saves the given article.
	 * <p>Use the returned instance for further operations as the update operation might have changed the article instance completely.</p>
	 * @return The saved article
	 */
	public Article save (Article article) {
		return repository.save(article);
	}
}
