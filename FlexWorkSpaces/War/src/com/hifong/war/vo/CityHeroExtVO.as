/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="com.war.domain.CityHeroExt")]
	[Bindable]
	public class CityHeroExtVO implements IValueObject {

	    /** 城市英雄编号 */
	public var cityHeroID:int;
	/** 指挥装备加成 */
	public var commandEquipmentAdd:int;
	/** 指挥宝物加成 */
	public var commandTreasureAdd:int;
	/** 防护装备加成 */
	public var defenseEquipmentAdd:int;
	/** 防护宝物加成 */
	public var defenseTreasureAdd:int;
	/** 思维装备加成 */
	public var mindEquipmentAdd:int;
	/** 思维宝物加成 */
	public var mindTreasureAdd:int;
	/** 行政装备加成 */
	public var executivepowerEquipmentAdd:int;
	/** 行政宝物加成 */
	public var executivepowerTreasureAdd:int;
	/** 统驭军团加成 */
	public var reinGuildAdd:int;
	/** 统驭宝物加成 */
	public var reinTreasureAdd:int;
	/** 经验军团加成 */
	public var expGuildAdd:int;
	/** 经验宝物加成 */
	public var expTreasureAdd:int;
	/** 军队攻击加成 */
	public var militaryAttackAdd:int;
	/** 军队防御加成 */
	public var militaryDefenseAdd:int;
		/** 军队生命加成 */
		public var militaryLifeAdd:int;
	}
}