package eu.sos.ttc.webapp.configuration;


import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
public class WebappInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	@Override
	public void onStartup (ServletContext servletContext) throws ServletException {

		super.onStartup(servletContext);
		servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter())
					  .addMappingForUrlPatterns(null, true, "/*");
	}


	@Override
	protected Class<?>[] getRootConfigClasses () {

		return new Class<?>[] {
				CoreConfiguration.class,
				PersistenceConfiguration.class,
				SecurityConfiguration.class
		};
	}


	@Override
	protected Class<?>[] getServletConfigClasses () {

		return new Class<?>[] {
				WebappConfiguration.class
		};
	}


	@Override
	protected String[] getServletMappings () {
		return new String[] {"/"};
	}
}
