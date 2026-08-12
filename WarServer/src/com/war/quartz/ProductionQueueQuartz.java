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

public class ProductionQueueQuartz implements Job{

	private static IQuartzService quartzService = (IQuartzService)SpringService.getBean("quartzService");
	
	private static Scheduler sched = null;

	/* 锁对象*/
	private static Object lock = new Object();
	
	/* 任务是否正在运行的标志位*/
	private static boolean isRunning = false;
	
	private static Logger logger = Logger.getLogger(BattleQuartz.class);
	
	public static void run() {
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			sched = schedFact.getScheduler();
			JobDetail jobDetail = new JobDetail("productionQueueJob", null,
					ProductionQueueQuartz.class);

			CronTrigger trigger = new CronTrigger("productionQueueTrigger", "productionQueueGroup");
			trigger.setCronExpression("0,10,20,30,40,50 * * * * ?");

			trigger.setName("productionQueueTrigger");
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
		synchronized (lock) {
			if(isRunning){
				return;
			}else{
				isRunning = true;
			}
		}
		
		quartzService.handleProductionQueue();
		
		isRunning = false;
	}
	
}
