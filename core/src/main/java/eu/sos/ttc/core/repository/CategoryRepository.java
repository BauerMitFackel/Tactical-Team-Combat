package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.Category;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of categories.
 * @author BauerMitFackel
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByParentIsNullOrderByRankAsc ();
}
