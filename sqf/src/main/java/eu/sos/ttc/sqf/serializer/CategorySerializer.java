package eu.sos.ttc.sqf.serializer;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.sos.ttc.core.domain.Group;
import eu.sos.ttc.sqf.utils.VelocityHelper;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
public class CategorySerializer implements Serializer<List<Group>>{


	@Override
	public String serialize (List<Group> groups) {

		VelocityContext context = new VelocityContext();
		context.put("categories", getCategoryMapList(groups));

		Template template = getTemplate();
		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();
	}


	private List<Map<String, String>> getCategoryMapList (List<Group> categories) {

		ArrayList<Map<String, String>> list = new ArrayList<>();
		for (Group category : categories) {
			Map<String, String> dto = createCategoryDto(category);
			list.add(dto);
		}

		return list;
	}


	private Map<String, String> createCategoryDto (Group category) {

		Map<String, String> map = new HashMap<>();
		map.put("id", Integer.toString(category.getId()));
		map.put("varName", category.getName().toLowerCase());
		map.put("name", category.getName());
		map.put("icon", category.getIcon());

		return map;
	}


	private Template getTemplate () {

		VelocityEngine velocityEngine = VelocityHelper.createVelocityEngine();
		return velocityEngine.getTemplate("ttc_shop_categories.vm");
	}
}
