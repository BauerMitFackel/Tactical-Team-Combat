package eu.sos.ttc.core.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


/**
 * Persistence configuration.
 * @author BauerMitFackel
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("eu.sos.ttc.core")
@PropertySource("classpath:persistence.properties")
public class PersistenceConfiguration {


	private static final String PROPERTY_DATABASE_DRIVER = "database.driver";
	private static final String PROPERTY_DATABASE_URL = "database.url";
	private static final String PROPERTY_DATABASE_USERNAME = "database.username";
	private static final String PROPERTY_DATABASE_PASSWORD = "database.password";


	@Autowired
	private Environment environment;


	@Bean
	public DataSource dataSource () {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(getProperty(PROPERTY_DATABASE_DRIVER));
		dataSource.setUrl(getProperty(PROPERTY_DATABASE_URL));
		dataSource.setUsername(getProperty(PROPERTY_DATABASE_USERNAME));
		dataSource.setPassword(getProperty(PROPERTY_DATABASE_PASSWORD));

		return dataSource;
	}


	@Bean
	public EntityManagerFactory entityManagerFactory () throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setJpaProperties(hibernateProperties());
		factory.setPackagesToScan(packagesToScan());
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}


	@Bean
	public PlatformTransactionManager transactionManager (EntityManagerFactory entityManagerFactory) {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}


	@Bean
	public JpaDialect hibernateJpaDialect () {
		return new HibernateJpaDialect();
	}


	private Properties hibernateProperties () {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "false");

		return properties;
	}


	private String[] packagesToScan () {
		return new String[] {"eu.sos.ttc.core"};
	}


	private String getProperty (String key) {
		return environment.getRequiredProperty(key);
	}
}
