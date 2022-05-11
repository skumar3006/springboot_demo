package com.demo.learn.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory1", transactionManagerRef = "transactionManager1", basePackages = {
		"com.demo.learn.user.repositories" })
public class DatabaseConfig2 {

	@Bean(name = "transactionManager1")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean(name = "entityManagerFactory1")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource1") DataSource dataSource) {

		return builder.dataSource(dataSource).packages("com.demo.learn.user.entities").build();
	}

	@Bean(name = "dataSource1")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplate1")
	public JdbcTemplate portalJdbcTemplete(@Qualifier("dataSource1") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
