package com.demo.learn.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.demo.learn.user.service.StudentService;

import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Log4j2
public class StudentBatchProcessScheduler {
	
	@Autowired
	private StudentService studentService;
	
	@Scheduled(cron = "${spring.batch.demo.expression}", zone = "")
	@SchedulerLock(name = "demoSchedule", lockAtLeastForString = "PT5M", lockAtMostForString = "PT14M")
	public void scheduleBatch() {
		log.info("######## Started Batch Processing #########");
		studentService.processData();
		log.info("######## Completed Batch Processing #########");
	}
}
