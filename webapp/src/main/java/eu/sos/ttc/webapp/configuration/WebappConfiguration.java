package eu.sos.ttc.webapp.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.nio.charset.StandardCharsets;


/**
 * Webapp module spring configuration.
 * @author BauerMitFackel
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "eu.sos.ttc.webapp")
public class WebappConfiguration extends WebMvcConfigurerAdapter {


	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


	@Bean
	public SpringTemplateEngine templateEngine () {

		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}


	@Bean
	public ServletContextTemplateResolver templateResolver () {

		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
		resolver.setCacheable(false);
		resolver.setOrder(1);
		return resolver;
	}


	@Bean
	public ViewResolver viewResolver () {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] {"*"});
		viewResolver.setCache(false);
		return viewResolver;
	}
}
