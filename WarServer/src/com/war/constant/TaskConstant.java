package com.war.constant;

/**
 * 任务类别
 *
 * @author ghleed
 * @version 1.0
 */
public class TaskConstant {
	
	/** 新手任务 */
	public static final int BEGINNER = 1;
	
	/** 成长任务 */
	public static final int DEVELOPMENT = 2;
	
	/** 日常任务 */
	public static final int DAILY = 3;
	
	/** 循环任务 */
	public static final int CYCLE = 4;
	
	/** 日常任务中领取津贴任务的任务编号范围 */
	public static final int[] RECEIVE_ALLOWANCE_LIMIT = {3004, 3023};
	
	// ~~~~~~~~ Flag ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 每日任务未完成标志 */
	public static final int INCOMPLETED_DAILY_TASK_FLAG = 0;
	
	/** 每日任务已完成标志 */
	public static final int COMPLETED_DAILY_TASK_FLAG = 1;
	
}
