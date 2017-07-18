package com.yqz.springboot.quickstart.task;

import java.time.LocalDateTime;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class HelloTask implements Job {

	public void sayHello() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello,buddy!!" + LocalDateTime.now().toLocalTime());
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		long timestamp = (long) context.getMergedJobDataMap().get("timestamp");

		System.out.println("hello,woker!" + new Date(timestamp));

	}

}
