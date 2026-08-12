package com.war.constant;

public class TechnologyConstant {
	
	/* ~~~ 科技名称 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	
	/** 增加木材产量(工业砍伐) */
	public static final int WOOD_OUTPUT_ADD = 11;
	/** 增加钢铁产量(高炉炼钢) */
	public static final int STEEL_OUTPUT_ADD = 12;
	/** 增加石油产量(深层开采) */
	public static final int OIL_OUTPUT_ADD = 13;
	/** 增加食物产量(农耕机械) */
	public static final int FOOD_OUTPUT_ADD = 14;
	
	
	/** 增加士兵攻击(抵近射击) */
	public static final int ARMY_ATT_ADD = 21;
	/** 增加士兵防御(防弹陶瓷) */
	public static final int ARMY_DEF_ADD = 22;
	/** 增加士兵速度(高速行军) */
	public static final int ARMY_SPEED_ADD = 23;
	/** 增加士兵射程(概率射击) */
	public static final int ARMY_RANGE_ADD = 24;
	
	/** 增加车辆攻击(瞄准技术) */
	public static final int TRUCK_ATT_ADD = 31;
	/** 增加车辆防御(附加装甲) */
	public static final int TRUCK_DEF_ADD = 32;
	/** 增加车辆速度(突破前进) */
	public static final int TRUCK_SPEED_ADD = 33;
	/** 增加车辆射程(远程射击) */
	public static final int TRUCK_RANGE_ADD = 34;
	
	/** 增加飞机攻击(攻击火箭) */
	public static final int AIRPLANE_ATT_ADD = 41;
	/** 增加飞机防御(阻燃材料) */
	public static final int AIRPLANE_DEF_ADD = 42;
	/** 增加飞机速度(高压燃烧) */
	public static final int AIRPLANE_SPEED_ADD = 43;
	/** 增加飞机射程(机载雷达) */
	public static final int AIRPLANE_RANGE_ADD = 44;
	
	/** 提升仓库最大容量(仓储管理) */
	public static final int OTHER_STORAGE_ADD = 51;
	/** 增加部队负重能力(物流管理) */
	public static final int OTHER_CARRY_ADD = 52;
	/** 训练侦察兵的侦察能力，侦察兵将送回更加详细的侦查报告(侦查能力) */
	public static final int OTHER_SPY_ADD = 53;
	/** 研究有效的城市防卫方式，提升城防设施战斗力(城市协防)*/
	public static final int OTHER_DEFENSE_ADD = 54;
	/** 提升伤兵转化率(高效治疗) */
	public static final int MILITARY_WOUNDED_TRANSFORM_RATE_ADD = 55; 
	/** 资源保护(资源保护) */
	public static final int RESOURCES_PROTECT = 56;
	
	
	/* ~~~ 科技效果 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	
	/** 科技士兵攻击增加倍率 */
	public static final int TECH_ARMY_ATTACK_ADD_MULTIPLE = 5;
	/** 科技士兵防御增加倍率 */
	public static final int TECH_ARMY_DEFENSE_ADD_MULTIPLE = 5;
	/** 科技士兵速度增加倍率 */
	public static final int TECH_ARMY_SPEED_ADD_MULTIPLE = 10;
	/** 科技士兵攻击范围增加倍率 */
	public static final int TECH_ARMY_RANGE_ADD_MULTIPLE = 10;
	
	/** 科技车辆攻击增加倍率 */
	public static final int TECH_TRUCK_ATTACK_ADD_MULTIPLE = 3;
	/** 科技车辆防御增加倍率 */
	public static final int TECH_TRUCK_DEFENSE_ADD_MULTIPLE = 5;
	/** 科技车辆速度增加倍率 */
	public static final int TECH_TRUCK_SPEED_ADD_MULTIPLE = 10;
	/** 科技车辆攻击范围增加倍率 */
	public static final int TECH_TRUCK_RANGE_ADD_MULTIPLE = 10;
	
	/** 科技飞机攻击增加倍率 */
	public static final int TECH_AIRPLANE_ATTACK_ADD_MULTIPLE = 1;
	/** 科技飞机防御增加倍率 */
	public static final int TECH_AIRPLANE_DEFENSE_ADD_MULTIPLE = 5;
	/** 科技飞机速度增加倍率 */
	public static final int TECH_AIRPLANE_SPEED_ADD_MULTIPLE = 10;
	/** 科技飞机攻击范围增加倍率 */
	public static final int TECH_AIRPLANE_RANGE_ADD_MULTIPLE = 10;
	
	/** 科技增加负重(单位：百分之) */
	public static final int TECH_ADD_CARRY = 2;
	
	/** 科技增加城市防御攻击倍率 */
	public static final int TECH_ADD_DEFENSE_ATTACK_MULTIPLE = 5;
	
}
