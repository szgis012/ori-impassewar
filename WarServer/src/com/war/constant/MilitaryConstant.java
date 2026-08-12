package com.war.constant;

public class MilitaryConstant {

	public static final String[] MILITARY_NAME = {"","排","连","营","团","旅","师","军","集团军","集团军群"};
	
	/** 军队默认速度 */
	public static final int MILITARY_DEFAULT_SPEED = 99;
	
	/** 出征资源消耗倍数 */
	public static final int CAMPAIGN_COST_RESOURCE_MULTIPLE = 1;
	
	/** 能够驻扎的最大援军数 */
	public static final int CITY_MILITARY_SUCCOR_MAX_NUM = 4;
	
	/** 被攻击的最大敌方队列数 */
	public static final int CITY_UNDER_ATTACK_MAX_MILITARY_NUM = 10;
	
	/** 出征士兵消耗基数 */
	public static final int ARMY_COST_BASE = 1;
	
	/** 士气高涨触发伤害加成的概率（单位：%） */
	public static final int HEGH_LEADERSHIP_ATTACK_TOUCH_OFF_RATE = 10;
	
	/** 士气低迷触发受到伤害加成的概率 */
	public static final int LOW_LEADERSHIP_BEATTACK_TOUCH_OFF_RATE = 10;
	
	/** 毫无士气时逃跑士兵触发的概率（单位：%） */
	public static final int FEW_LEADERSHIP_MILITARY_FLEE_TOUCH_OFF_RATE = 5;
	
	/** 士气高涨对伤害加成比例（单位：%） */
	public static final int HIGH_LEADERSHIP_ATTACK_PLUS_PERCENT = 5;
	
	/** 士气低迷时受到的伤害加成比例（单位：%） */
	public static final int LOW_LEADERSHIP_BEATTACK_PLUS_PERCENT = 5;
	
	/** 毫无士气时逃跑士兵比例（单位：%） */
	public static final int FEW_LEADERSHIP_MILITARY_FLEE_PERCENT = 5;
	
	/** 士气高涨范围 [90, 100]*/
	public static final int[] LIMIT_OF_HIGH_LEADERSHIP = {90, 100};
	
	/** 士气正常范围 */
	public static final int[] LIMIT_OF_NORMAL_LEADERSHIP = {70, 89};
	
	/** 士气低迷范围 */
	public static final int[] LIMIT_OF_LOW_LEADERSHIP = {30, 69};
	
	/** 毫无士气范围 */
	public static final int[] LIMIT_OF_FEW_LEADERSHIP = {0, 29};
}
