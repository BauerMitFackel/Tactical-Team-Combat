package eu.sos.ttc.core.comparator;


import java.util.Comparator;

import eu.sos.ttc.core.domain.Article;


/**
 * A {@link java.util.Comparator} that compares {@link eu.sos.ttc.core.domain.Article Articles} by id.
 * @author BauerMitFackel
 */
public class ArticleIdComparator implements Comparator<Article> {

	@Override
	public int compare (Article b1, Article b2) {

		if (b1.getId() > b2.getId()) {
			return 1;
		} else if (b1.getId() < b2.getId()) {
			return -1;
		}

		return 0;
	}
}
