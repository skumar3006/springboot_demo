package com.demo.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.learn.batch.StudentBatchProcessScheduler;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class SchedulerConfig {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("transactionManager")
	PlatformTransactionManager transactionManager;
	
	@Bean
	public LockProvider lockProvider() {
		return new JdbcTemplateLockProvider(jdbcTemplate, transactionManager);
	}
	
	@Bean
	@ConditionalOnProperty(name = "spring.batch.demo.enabled", havingValue = "true")
	StudentBatchProcessScheduler getBean() {
		return new StudentBatchProcessScheduler();
	}
}
