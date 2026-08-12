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

public class BattleQuartz implements Job {

	private static IQuartzService quartzService = (IQuartzService)SpringService.getBean("quartzService");
	
	private static Scheduler sched = null;

	/* 锁对象*/
	private static Object lock = new Object();
	
	/* 任务是否正在运行的标志位*/
	private static boolean isRunning = false;
	
	private static Logger logger = Logger.getLogger(BattleQuartz.class);
	
	public static void run() {
		try {
			
			// 首先创建一个调度程序工厂 
			SchedulerFactory schedFact = new StdSchedulerFactory();
			// 从工厂获取一个调度程序实例 
			sched = schedFact.getScheduler();
			//------- 初始化完成 -----------

			// 定义一个具体作业jobDetail，并加入battleJob组，并且绑定到具体的作业类BattleQuartz上 
			JobDetail jobDetail = new JobDetail("battleJob", null,
					BattleQuartz.class);

			// 创建一个触发器 
			CronTrigger trigger = new CronTrigger("battleTrigger", "battleGroup");
			trigger.setCronExpression("5,15,25,35,45,55 * * * * ?");

			trigger.setName("battleTrigger");
			
			// 设置调度的具体作业和相关的触发器 
			sched.scheduleJob(jobDetail, trigger);
			// 启动调度程序 
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
		
		quartzService.handleBattleInfo();
		
		isRunning = false;
	}
	
}
