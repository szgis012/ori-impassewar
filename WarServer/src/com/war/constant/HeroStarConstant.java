package com.war.constant;

/**
 * 与指挥官星级相关的常量
 * 
 * @author JiaHL
 * @version 1.0
 */
public class HeroStarConstant {

	/** 指挥官强化星级的成功基准概率:单位% */
	public static final int[] SUCCESS_RATE_OF_UPGRADE_HERO_STAR = 
	{
		// 0: 升为一星
		80,
		// 1: 升为二星
		50,
		// 2: 升为三星
		20,
		// 3: 升为四星
		10,
		// 4: 升为五星
		1
	};
	
	/**
	 * 强化指挥官星级需要的材料
	 * 数组一维映射需要强化到得级别
	 * 数组二维映射需要的一些材料： {军魄，指挥官经验，金钱}
	 */
	public static final int[][] UPGRADE_HERO_STAR_NEEDED_STUFF =
	{
		// 0: 升为一星
		{50,400,5000},
		// 1: 升为二星
		{100,1600,15000},
		// 2: 升为三星
		{150,3600,30000},
		// 3: 升为四星
		{200,7000,100000},
		// 4: 升为五星
		{250,15000,200000}
	};
	
	/** 星级对军队防御的影响 */
	public static final int[] HERO_STAR_EFFECT_OF_DEFENSE = {0, 0, 1, 2, 3, 4};
	
	/** 星级对军队攻击的影响 (单位：%) */
	public static final int[] HERO_STAR_EFFECT_OF_ATTACK = {0, 5, 8, 10, 15, 20};
	
	/** 星级对军队士气的影响 (单位：%) */
	public static final int[] HERO_STAR_EFFECT_OF_LEADERSHIP = {0, 0, 0, 5, 10, 15};
	
	/** 星级对军队生命值的影响 (单位：%) */
	public static final int[] HERO_STAR_EFFECT_OF_LIFE = {0, 0, 0, 0, 5, 10};
	
	/** 指挥官能达到的最高星级 */
	public static final int HERO_STAR_MAX_LEVEL = 5;
}
