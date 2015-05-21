package eu.sos.ttc.cg;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * TODO: Write documentation
 * @author BauerMitFackel
 */
@Component
public class SpringContext implements ApplicationContextAware {


	private static ApplicationContext applicationContext;


	@Override
	public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}


	public static ApplicationContext getApplicationContext () {
		return applicationContext;
	}
}
