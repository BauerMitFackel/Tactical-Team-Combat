package eu.sos.ttc.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.domain.Article;
import eu.sos.ttc.core.repository.ArticleRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class ArticleService {


	@Autowired
	private ArticleRepository repository;


	/**
	 * Returns a article by its id.
	 * @return The article with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Article getById (int id) {
		return repository.findOne(id);
	}


	/**
	 * Returns all articles.
	 * @return A {@link java.util.List} containing all articles.
	 */
	public List<Article> getAll () {
		return repository.findAll();
	}
}
