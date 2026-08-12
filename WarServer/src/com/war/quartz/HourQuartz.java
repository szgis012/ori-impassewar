package com.war.quartz;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.war.common.SpringService;
import com.war.service.IQuartzService;

public class HourQuartz implements Job {

	private static IQuartzService quartzService = (IQuartzService)SpringService.getBean("quartzService");

	private static Scheduler sched = null;
	
	private static Logger logger = Logger.getLogger(HourQuartz.class);
	
	public static void run() {
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			sched = schedFact.getScheduler();
			JobDetail jobDetail = new JobDetail("hourJob", null,
					HourQuartz.class);

			CronTrigger trigger = new CronTrigger("hourTrigger",
					"hourGroup");
			trigger.setCronExpression("0 0 * * * ?");

			trigger.setName("hourTrigger");
			sched.scheduleJob(jobDetail, trigger);
			sched.start();
		} catch (SchedulerException e) {
			logger.error("异常：", e);
		} catch (ParseException e) {
			logger.error("异常：", e);
		}
	}

	public static void stop() {
		/*try {
			sched.shutdown();
		} catch (SchedulerException e) {
			logger.error("异常：", e);
		}*/
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		quartzService.handleHourEvent();
	}
	
}
