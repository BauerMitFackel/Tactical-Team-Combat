package eu.sos.ttc.sqf.utils;


import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;


/**
 * Velocity helper class.
 * @author BauerMitFackel
 */
public enum VelocityHelper {
	;


	/**
	 * Creates a new {@link org.apache.velocity.app.VelocityEngine} that searches for
	 * <b>.vm</b> files in the classpath.
	 */
	public static VelocityEngine createVelocityEngine () {

		Properties properties = new Properties();
		properties.setProperty("resource.loader", "file");
		properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init(properties);

		return velocityEngine;
	};
}
