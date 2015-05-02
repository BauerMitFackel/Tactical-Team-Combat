package eu.sos.ttc.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import eu.sos.ttc.core.domain.Category;
import eu.sos.ttc.core.repository.CategoryRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;


	/**
	 * Returns a category by its id.
	 * @return The category with the given id or {@code null} if none found
	 * @throws java.lang.IllegalArgumentException If  {@code id} is null
	 */
	public Category getById (int id) {
		return repository.findOne(id);
	}


	/**
	 * Returns all root categories. A root category is a category without a supercategory.
	 * @return A {@link java.util.List} containing all root categories.
	 */
	public List<Category> getRootCategories () {
		return repository.findByParentIsNullOrderByRankAsc();
	}


	/**
	 * Returns all categories.
	 * @return A {@link java.util.List} containing all categories.
	 */
	public List<Category> getAll () {
		return repository.findAll();
	}
}
