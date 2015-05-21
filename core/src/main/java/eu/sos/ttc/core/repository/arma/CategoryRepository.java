package eu.sos.ttc.core.repository.arma;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import eu.sos.ttc.core.domain.arma.Category;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of categories.
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.domain.arma.Category
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	/**
	 * Returns all categories without a supercategory.
	 * @return A {@link java.util.List} containing all categories without a supercategory
	 */
	List<Category> findBySupercategoryIsNullOrderByRankAsc ();
}
