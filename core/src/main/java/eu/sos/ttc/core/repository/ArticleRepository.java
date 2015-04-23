package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import eu.sos.ttc.core.domain.Article;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of articles.
 * @author BauerMitFackel
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
