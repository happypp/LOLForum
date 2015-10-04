package com.lolforum.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {
	public static int runTime = 1;
	
//	@Scheduled(cron = "* 30 * * * * ")
	public void updateTrafficInfo() {
		
	}
	
	// @Scheduled(cron = "*/5 * * * * *")
	public void testJob() {
		System.out.println("Job start...");
		if (TaskJob.runTime > 1)
			return;
		TaskJob.runTime++;
		System.out.println("The job is running....");
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"xml/applicationContext.xml");
	}
}
