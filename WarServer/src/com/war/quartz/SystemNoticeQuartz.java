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

public class SystemNoticeQuartz implements Job {

	private static IQuartzService quartzService = (IQuartzService)SpringService.getBean("quartzService");
	
	private static Scheduler sched = null;

	private static Logger logger = Logger.getLogger(SystemNoticeQuartz.class);
	
	public static void run() {
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			sched = schedFact.getScheduler();
			JobDetail jobDetail = new JobDetail("systemNoticeJob", null,
					SystemNoticeQuartz.class);

			CronTrigger trigger = new CronTrigger("systemNoticeTrigger", "systemNoticeGroup");
			trigger.setCronExpression("0 0,10,15,20,25,30,35,40,45,50,55 * * * ?");

			trigger.setName("tsystemNoticeTrigger");
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
		quartzService.handleSystemNoticeEvent();
	}
	
}
