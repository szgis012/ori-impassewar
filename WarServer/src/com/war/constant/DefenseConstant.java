package com.war.constant;

/**
 * 城市防御常量
 * 
 * @author jiaHL
 * @version 1.0
 */
public class DefenseConstant {
	
	/** 围墙 */
	public static final int FENCE = 1;
	
	/** 碉堡 */
	public static final int BUNKER = 2;

	/** 火炮 */
	public static final int GUN = 3;

	/** 防空炮 */
	public static final int ANTIGUN = 4;
	
	
	// ~~~~~~~~ 城市防御障碍数组坐标 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** 围墙 X 坐标 */
	public static final int FENCE_BARRIER_POSX = 11;
	/** 围墙1 Y 坐标 */
	public static final int FIRST_FENCE_BARRIER_POSY = 0;
	/** 围墙2 Y 坐标 */
	public static final int SECOND_FENCE_BARRIER_POSY = 1;
	/** 围墙3 Y 坐标 */
	public static final int THIRD_FENCE_BARRIER_POSY = 2;
	/** 围墙4 Y 坐标 */
	public static final int FOURTH_FENCE_BARRIER_POSY = 3;
	/** 围墙5 Y 坐标 */
	public static final int FIFTH_FENCE_BARRIER_POSY = 4;
	/** 围墙6 Y 坐标 */
	public static final int SIXTH_FENCE_BARRIER_POSY = 5;
	/** 围墙7 Y 坐标 */
	public static final int SEVENTH_FENCE_BARRIER_POSY = 6;
	/** 围墙8 Y 坐标 */
	public static final int EIGHTH_FENCE_BARRIER_POSY = 7;
	
	
	/** 碉堡 X 坐标  */
	public static final int BUNKER_BARRIER_POSX = 10;
	/** 上碉堡 Y 坐标  */
	public static final int ABOVE_BUNKER_BARRIER_POSY = 2;
	/** 下碉堡 Y 坐标  */
	public static final int AFTER_BUNKER_BARRIER_POSY = 5;
	
	/** 火炮 X 坐标  */
	public static final int GUN_BARRIER_POSX = 12;
	/** 上火炮 Y 坐标  */
	public static final int ABOVE_GUN_BARRIER_POSY = 2;
	/** 下火炮 Y 坐标  */
	public static final int AFTER_GUN_BARRIER_POSY = 5;
	
	/** 防空炮 X 坐标  */
	public static final int ANTIGUN_BARRIER_POSX = 12;
	/** 上防空炮 Y 坐标  */
	public static final int ABOVE_ANTIGUN_BARRIER_POSY = 1;
	/** 下防空炮 Y 坐标  */
	public static final int AFTER_ANTIGUN_BARRIER_POSY = 6;
}
