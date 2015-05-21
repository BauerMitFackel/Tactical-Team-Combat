package eu.sos.ttc.cg;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import eu.sos.ttc.cg.configuration.CodeGeneratorConfiguration;
import eu.sos.ttc.core.configuration.CoreConfiguration;
import eu.sos.ttc.core.configuration.PersistenceConfiguration;


/**
 * @author Ulrich Raab
 */
public class Main {


	private Main (){}


	public static void main (String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(
				CoreConfiguration.class,
				PersistenceConfiguration.class,
				CodeGeneratorConfiguration.class
		);

		App app = context.getBean(App.class);
		app.run();
	}
}
