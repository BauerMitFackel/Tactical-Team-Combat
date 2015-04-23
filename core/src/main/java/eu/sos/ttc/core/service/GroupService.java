package eu.sos.ttc.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import eu.sos.ttc.core.domain.Group;
import eu.sos.ttc.core.repository.GroupRepository;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Service
@Transactional
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;


	public List<Group> getAll () {
		return groupRepository.findAll();
	};


	/**
	 * Returns all categories. A category is a group without a parent group.
	 * @return A List containing all categories
	 */
	public List<Group> getCategories () {
		return groupRepository.findByParentIsNull();
	}


	public Group get (int id) {
		return groupRepository.findOne(id);
	}
}
