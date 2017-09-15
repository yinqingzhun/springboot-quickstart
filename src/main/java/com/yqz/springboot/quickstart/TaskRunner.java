package com.yqz.springboot.quickstart;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.yqz.springboot.quickstart.task.HelloTask;

@Component
public class TaskRunner implements CommandLineRunner {

	@Resource
	SchedulerFactoryBean schedulerFactoryBean;

	@Override
	public void run(String... args) throws Exception {

//		JobDetail jobDetail = JobBuilder.newJob(HelloTask.class).withIdentity("helloJob", "helloGroup")
//				.usingJobData("timestamp", new Date().getTime()).build();
//
//		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startNow()
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(10).repeatForever()).build();
//
//		schedulerFactoryBean.getObject().scheduleJob(jobDetail, trigger);
	}

}
