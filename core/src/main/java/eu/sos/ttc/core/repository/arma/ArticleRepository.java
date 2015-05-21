package eu.sos.ttc.core.repository.arma;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.arma.Article;
import eu.sos.ttc.core.domain.arma.Role;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of articles.
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.domain.arma.Article
 */
public interface ArticleRepository extends JpaRepository<Article, Article.Id> {

	/**
	 * Returns all articles for the given role.
	 * @param roleId The id of the role for which to get the articles
	 * @return A {@link java.util.List} containing all articles for the given role
	 */
	@Deprecated
	public List<Article> findByRoleId (int roleId);

	/**
	 * Returns all articles for the given role.
	 * @param role The role for which to get the articles
	 * @return A {@link java.util.List} containing all articles for the given role
	 */
	public List<Article> findByRole (Role role);
}
