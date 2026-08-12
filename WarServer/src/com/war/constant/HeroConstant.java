package com.war.constant;

/**
 * 英雄常量
 * 
 * @author TopTong
 * @version 1.0
 */
public class HeroConstant {

	/** 默认英雄忠诚 */
	public static final int DEFAULT_HERO_LOYALTY = 80;
	
	/** 默认英雄领导力 */
	public static final int DEFAULT_HERO_LEADERSHIP = 70;
	
	/** 英雄基础点数 */
	public static final int HERO_BASE_POINT = 30;
	
	/** 默认英雄最多学习技能数量 */
	public static final int DEFAULT_MAX_SKILL_NUM = 3;
	
	/** 英雄数量倍数(建筑等级倍数) */
	public static final int HERO_AMOUNT_MULTIPLE = 2;
	
	/** 英雄点数最大倍数(英雄等级倍数) */
	public static final int HERO_POINT_MULTIPLE_MAX = 6;
	
	/** 英雄等级点数(建筑等级倍数) */
	public static final int HERO_LEVEL_MULTIPLE = 1;
	
	/** 英雄升级增加可用点数 */
	public static final int HERO_LEVEL_ADD_POINT = 4;
	
	/** 军事学院等级英雄倍数 */
	public static final int MILITARY_COLLEGE_HERO_NUM_MULTIPLE = 1;
	
	/** 指挥官基础体力 */
	public static final int HERO_BASE_STAMINA = 20;
	
	/** 指挥官思维附加体力 */
	public static final int HERO_MIND_ADD_STAMINA = 3;
	
	/** 不同品质的指挥官在计算统驭数量时所具有的倍数 {普通，稀有，史诗}*/
	public static final int[] REIN_MUTIPLE = {40,60,100};
	
	/** 训练指挥官所获得的领导力加成点数 */
	public static final int TRAINING_HERO_ADDED_LEADERSHIP_POINT = 5;
	
	//~~~~~~~ Gender ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 英雄性别(男) */
	public static final int MALE = 1;
	
	/** 英雄性别(女) */
	public static final int FEMALE = 2;
	
	//~~~~~~~ Quality ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 普通品质 */
	public static final int QUALITY_NORMAL = 1;
	
	/** 稀有品质 */
	public static final int QUALITY_SINGULARITY = 2;
	
	/** 史诗品质 */
	public static final int QUALITY_EPIC = 3;
}
