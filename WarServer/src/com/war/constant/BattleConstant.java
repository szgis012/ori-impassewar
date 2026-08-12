package com.war.constant;

public class BattleConstant {

	/** 回合时间(单位：秒) 150 */
	public static final int ROUND_TIME = 150;
	
	/** 掠夺战 */
	public static final int TYPE_PLUNDER = 1;
	
	/** 攻城战 */
	public static final int TYPE_SIEGE_WARFARE = 2;
	
	
	/** 进攻方 */
	public static final int FORCE_ATTACKER = 1;
	
	/** 防守方 */
	public static final int FORCE_DEFENDER = 2;
	
	
	/** 纵向网格数量 */
	public static final int BATTLE_V_GRID_NUM = 8;
	
	/** 横向网格数量 */
	public static final int BATTLE_H_GRID_NUM = 15;
	
	/** 战斗失败减少指挥官忠诚 */
	public static final int FAIL_MINUT_HERO_LOYALTY = 5;
	
	/** 战斗逃跑减少指挥官忠诚 */
	public static final int RETREAT_MINUT_HERO_LOYALTY = 20;
	
	/** 战斗失败减少指挥官领导力 */
	public static final int FAIL_MINUT_HERO_LEADERSHIP = 20;
	
	/** 战斗逃跑减少指挥官领导力 */
	public static final int RETREAT_MINUT_HERO_LEADERSHIP = 20;
	
	/** 战斗间歇时间 (单位：分钟) */
	public static final int INTERVAL_OF_BATTLE = 5;

	/**
	 * 军队损失减少指挥官领导力
	 * 数组一维映射损失等级
	 * 数组二维映射减少的领导力数值 {射损失临界百分比, 需要减少的士气数值}
	 */
	public static final int[][] MILITARY_LOSING_MINUT_HERO_LEADERSHIP = 
	{
		//0 较少损失
		{40,5},
		//1 中等损失
		{60,10},
		//2 较多损失
		{80,15}
	};
	
	/** 兵种经验 */
	public static final int[] ARMY_EXP = 
	{
		//0 空余
		0,
		//1 步枪兵
		1,
		//2 机枪兵
		2,
		//3 突击兵
		3,
		//4 反装甲兵
		5,
		//5 狙击手
		5,
		//6 侦察兵
		1,
		//7 特种部队(自由联邦)
		8,
		//8 特种部队(联合帝国)
		8,
		//9 装甲车
		10,
		//10 反坦克战车
		11,
		//11 自行火炮
		12,
		//12 中型坦克
		12,
		//13 重型坦克(自由联邦)
		20,
		//14 重型坦克(联合帝国)
		20,
		//15 战斗机
		15,
		//16 轰炸机
		25,
		//17 俯冲轰炸机
		20
	};
	
	/**
	 * 城市防御属性
	 * 数组一维映射城市防御编号
	 * 数组二维映射城市防御属性 {生命,攻击力,防御力,攻击范围}
	 */
/*	public static final int[][] CITY_DEFENSE_ATTRIBUTE = 
	{
		//0 空余
		{0,0,0,0},
		//1 围墙
		{150,0,7,0},
		//2 碉堡
		{120,10,4,3},
		//3 火炮
		{90,40,4,5},
		//4 防空炮
		{90,70,3,4}
	};*/
	
	/**
	 * 城市防御相克关系
	 * 数组一维映射兵种编号
	 * 数组二维映射相克关系 {步兵,装甲,飞机}
	 * 单位 % 
	 */
	public static final int[][] CITY_DEFENSE_ATTACK_RELATIONSHIP = 
	{
		//0 空余
		{0,0,0},
		//1 围墙
		{0,0,0},
		//2 碉堡
		{100,75,75},
		//3 火炮
		{75,100,75},
		//4 防空炮
		{75,75,100}
	};
	
	/**
	 * 兵种相克关系
	 * 数组一维映射兵种编号
	 * 数组二维映射相克关系 {步兵,装甲,飞机,城市防御}
	 * 单位 % 
	 */
	public static final int[][] ARMY_ATTACK_RELATIONSHIP = 
	{
		//0 空余
		{0,0,0,0},
		//1 步枪兵
		{120,50,0,50},
		//2 机枪兵
		{120,50,50,50},
		//3 突击兵
		{130,50,0,50},
		//4 反装甲兵
		{50,160,50,60},
		//5 狙击手
		{140,40,0,50},
		//6 侦察兵
		{100,0,0,50},
		//7 特种部队(联合帝国)
		{120,70,0,50},
		//8 特种部队(自由联邦)
		{120,70,0,50},
		//9 装甲车
		{120,70,80,50},
		//10 反坦克战车
		{60,160,0,70},
		//11 自行火炮
		{60,60,0,150},
		//12 中型坦克
		{60,120,0,70},
		//13 重型坦克(联合帝国)
		{60,120,0,70},
		//14 重型坦克(自由联邦)
		{60,120,0,70},
		//15 战斗机
		{0,0,180,50},
		//16 轰炸机
		{100,100,0,120},
		//17 俯冲轰炸机
		{70,70,0,70}
	};
	
}
