package com.war.constant;

public class GuildConstant {
	
	/** 默认军团成员权限 */
	/** 审核邀请成员-移除军团成员-维护军团信息-管理军团关系-军团消息群发-军团关系管理 */
	public static final String DEFAULT_GUILD_MEMBER_PERMISSION = "0-0-0-0-0-0";

	/**  军团科技当前状态常量 正常 **/
	public static final int GUILD_TECH_CURRENT_STATE_NORMAL = 1;
	
	/**  军团科技当前状态常量 升级中 **/
	public static final int GUILD_TECH_CURRENT_STATE_UPGRADING = 2;
	
	/**	 升级条件: 升级军团需要的成员数 **/
	public static final int[] GUILD_MAN_COUNT = {10, 20, 30, 40, 50};
	
	/**	 升级条件: 升级军团需要的金币数 **/
	public static final long[] GUILD_TOTAL_MONEY = {100000, 1000000, 5000000, 50000000, 10000000};
	
	/**	 升级条件: 升级军团需要的声望值 **/
	public static final int[] GUILD_RENOWN = {10000, 50000, 250000, 1250000, 6250000};
	
	/**	 升级条件: 升级军团需要的士官军旗数 **/
	public static final int[] GUILD_PERTYOFFICER_ENSIGN_COUNT = {0, 10, 20, 40, 70};
	
	/**	 升级条件: 升级军团需要的少将军旗数 **/
	public static final int[] GUILD_MAJOR_ENSIGN_COUNT = {0, 0, 5, 10, 25};
	
	/**	 升级条件: 升级军团需要的元帅军旗数 **/
	public static final int[] GUILD_MARSHAL_ENSIGN_COUNT = {0, 0, 0, 4, 10};
	
	/**	 升级条件: 升级军团需要的军团荣誉值 **/
	public static final int[] GUILD_HORNOR = {0, 0, 0, 0, 0};
	
	/** 每一级能任命最多官员数量 **/
	public static final int[] GUILD_OFFICER_NUM_EVERY_GRADE = {1, 2, 4, 6, 8, 10};
	
	/** 每一级能添加的最多的盟友个数 **/
	public static final int[] GUILD_FRIENDGUILD_NUM_EVERY_GRADE = {1, 2, 4, 6, 8, 10};
	
	/** 研究军团科技的队列类型 **/
	public static final int RESEARCH_GUILD_TECH_QUEUE_TYPE = 5;
	
	/** 军团之间关系状态常量：友好 **/
	public static final int GUILD_RELATIONSHIP_TYPE_FRIENDLY = 1;
	
	/** 军团之间关系状态常量：敌对 **/
	public static final int GUILD_RELATIONSHIP_TYPE_HOSTILITY = 2;
	
	// ~~~~~~ 军团捐献 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 向军团捐献1金钱所对应的军团贡献值 */
	public static final int MONEY_CONTRIBUTION_EXCHANGE_HONOR_NUM = 1;
	
	/** 向军团捐献1面士官军旗所对应的军团贡献值 */
	public static final int ORIFLAMME_LOWER_CONTRIBUTION_EXCHANGE_HONOR_NUM = 500000;

	/** 向军团捐献1面校官军旗所对应的军团贡献值 */
	public static final int ORIFLAMME_INTERMEDIATE_CONTRIBUTION_EXCHANGE_HONOR_NUM = 1000000;
	
	/** 向军团捐献1面元帅军旗所对应的军团贡献值 */
	public static final int ORIFLAMME_ADVANCED_CONTRIBUTION_EXCHANGE_HONOR_NUM = 2500000;
	
	// ~~~~~~ 军团科技名称 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 增加木材产量的军团科技编号 */
	public static final int WOOD_GUILD_TECH_ADD = 11;
	/** 增加钢铁产量的军团科技编号 */
	public static final int STEEL_GUILD_TECH_ADD = 12;
	/** 增加石油产量的军团科技编号 */
	public static final int OIL_GUILD_TECH_ADD = 13;
	/** 增加食物产量的军团科技编号 */
	public static final int FOOD_GUILD_TECH_ADD = 14;
	/** 增加金钱产量的军团科技编号 */
	public static final int MONEY_GUILD_TECH_ADD = 15;
	
	/** 降低敌方步兵攻击(生物腐蚀) */
	public static final int ARMY_ATTACK_TECH_MINUS = 21;
	/** 降低敌方步兵攻击范围(迷雾遮天) */
	public static final int ARMY_RANGE_TECH_MINUS = 22;
	/** 降低敌方步兵行动力(泥沼光波) */
	public static final int ARMY_SPEED_TECH_MINUS = 23;
	/** 提高己方步兵军队生命上限(勃然生机) */
	public static final int ARMY_LIFE_ADD = 24;
	/** 降低敌方车辆攻击力(金属腐蚀) */
	public static final int TRUCK_ATTACK_TECH_MINUS = 31;
	/** 降低敌方车辆攻击范围(干扰射线) */
	public static final int TRUCK_RANGE_TECH_MINUS = 32;
	/** 降低敌方车辆攻击行动力(动力瘫痪) */
	public static final int TRUCK_SPEED_TECH_MINUS = 33;
	/** 提高己方坦克军队生命上限(链式装甲) */
	public static final int TRUCK_LIFE_ADD = 34;
	
	/** 降低敌方飞机攻击力(惰性光波) */
	public static final int AIRPLANE_ATTACK_TECH_MINUS = 41;
	/** 降低敌方飞机攻击范围(雷达干扰) */
	public static final int AIRPLANE_RANGE_TECH_MINUS = 42;
	/** 降低敌方飞机行动力(冷凝寒气) */
	public static final int AIRPLANE_SPEED_TECH_MINUS = 43;
	/** 提高己方飞机军队生命上限(电磁机身) */
	public static final int AIRPLANE_LIFE_ADD = 44;
	
	/** 降低敌方所有军队攻击力(视觉误差) */
	public static final int MILITARY_ATTACK_TECH_MINUS = 51;
	/** 提高己方所有军队攻击(精神鼓舞) */
	public static final int MILITARY_ATTACK_TECH_ADD = 52;
	/** 提高己方所有军队行动力(迅疾如风) */
	public static final int MILITARY_SPEED_TECH_ADD = 53;
	
	// ~~~~~~ 军团科技效果 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 增加军团科技木头加成倍率 **/
	public static final int[] WOOD_GUILD_TECH_ADD_MULTIPLE = {0, 5, 8, 12, 18, 25};
	/** 增加军团科技钢铁加成倍率 **/
	public static final int[] STEEL_GUILD_TECH_ADD_MULTIPLE = {0, 5, 8, 12, 18, 25};
	/** 增加军团科技石油加成倍率 **/
	public static final int[] OIL_GUILD_TECH_ADD_MULTIPLE = {0, 5, 8, 12, 18, 25};
	/** 增加军团科技食物加成倍率 **/
	public static final int[] FOOD_GUILD_TECH_ADD_MULTIPLE = {0, 5, 8, 12, 18, 25};
	/** 增加军团科技金钱加成倍率 **/
	public static final int[] MONEY_GUILD_TECH_ADD_MULTIPLE = {0, 5, 8, 12, 18, 25};
	
	/** 降低敌方步兵攻击点数 */
	public static final int[] ARMY_ATTACK_TECH_MINUS_POINT = {0, 2, 3, 5, 8};
	/** 降低敌方步兵攻击范围点数 */
	public static final int[] ARMY_RANGE_TECH_MINUS_POINT = {0, 1, 2, 3, 5};
	/** 降低敌方步兵行动力点数 */
	public static final int[] ARMY_SPEED_TECH_MINUS_POINT = {0, 1, 2, 3, 5};
	/** 降低敌车辆攻击点数 */
	public static final int[] TRUCK_ATTACK_TECH_MINUS_POINT = {0, 4, 6, 9};
	/** 降低敌车辆攻击范围点数 */
	public static final int[] TRUCK_RANGE_TECH_MINUS_POINT = {0, 1, 2, 4};
	/** 降低敌车辆行动力点数 */
	public static final int[] TRUCK_SPEED_TECH_MINUS_POINT = {0, 1, 2, 4};
	/** 降低敌飞机攻击点数 */
	public static final int[] AIRPLANE_ATTACK_TECH_MINUS_POINT = {0, 20, 35};
	/** 降低敌飞机攻击范围点数 */
	public static final int[] AIRPLANE_RANGE_TECH_MINUS_POINT = {0, 2, 4};
	/** 降低敌飞机行动力点数 */
	public static final int[] AIRPLANE_SPEED_TECH_MINUS_POINT = {0, 2, 4};
	
	/** 增加步兵生命倍数 */
	public static final int[] ARMY_LIFE_ADD_MULTIPLE = {0, 5, 10};
	/** 增加车辆生命倍数 */
	public static final int[] TRUCK_LIFE_ADD_MULTIPLE = {0, 5, 10};
	/** 增加飞机生命倍数 */
	public static final int[] AIRPLANE_LIFE_ADD_MULTIPLE = {0, 5, 10};
	
	/** 降低敌军攻击力倍数 */
	public static final int[] MILITARY_ATTACK_TECH_MINUS_MULTIPLE = {0, 15};
	
	/** 增加军队攻击力倍数 */
	public static final int[] MILITARY_ATTACK_TECH_ADD_MULTIPLE = {0, 15};
	
	/** 增加军队行动力倍数 */
	public static final int[] MILITARY_SPEED_TECH_ADD_MULTIPLE = {0, 15};
	
	// ~~~~~~ 军团对指挥官的效果 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 
	 * 军团等级对玩家麾下所有指挥官体力恢复速度的加成比例（单位：%） 
	 * */
	public static final int[] GUILD_LEVEL_ADD_HERO_STAMINA_PERCENT = {0, 5, 10, 15, 25, 50};
	
	/**
	 * 军团等级对玩家麾下所有指挥官带兵数量上限的加成比例（单位：%） 
	 */
	public static final int[] GUILD_LEVEL_ADD_HERO_REIN_PERCENT = {0, 0, 5, 8, 15, 25};
	
	/**
	 * 军团等级对玩家麾下所有指挥官经验获取的加成比例（单位：%） 
	 */
	public static final int[] GUILD_LEVEL_ADD_HERO_EXP_PERCENT = {0, 0, 0, 10, 20, 40};
	
	// ~~~~~~ 军团补贴 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 
	 * 军团补贴二维数组
	 * 一维映射军队等级
	 * 二维映射奖励的资源(wood, steel, oil, food, money) 
	 */
	public static final int[][] GUILD_SUBSIDY = 
	{
		// 军队等级0
		{10000,10000,10000,10000,5000},
		// 军队等级1
		{50000,50000,50000,50000,10000},
		// 军队等级2
		{150000,150000,150000,150000,100000},
		// 军队等级3
		{300000,300000,300000,300000,200000},
		// 军队等级4
		{500000,500000,500000,500000,400000},
		// 军队等级5
		{1000000,1000000,1000000,1000000,800000}
	};
	
	/** 领取津贴间隔天数 */
	public static final int INTERVAL_DAY_OF_RECEIVE_GUILD_SUBSIDY = 7;
	
	// ~~~~~~ 是否允许驻军 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 不允许驻军 */
	public static final int FORBID_SUCCOR = 0;
	
	/** 允许驻军 */
	public static final int ALLOW_SUCCOR = 1;
	
	// ~~~~~~ 军团操作 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 捐献 */
	public static final int OPRATION_CONTRIBUTION = 1;
	
	/** 消费 */
	public static final int OPRATION_CONSUME = 2;
	
	// ~~~~~~ 军团旗帜类型 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 士官军旗 */
	public static final int FLAG_OF_PETTY_OFFICER = 0;
	
	/** 校官军旗 */
	public static final int FLAG_OF_FIELD_OFFICER = 1;
	
	/** 元帅军旗 */
	public static final int FLAG_OF_MARSHAL = 2;
}
