/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="com.war.domain.CityHero")]
	[Bindable]
	public class CityHeroVO implements IValueObject {

		/** 城市英雄编号 */
	    public var cityHeroID:Number;
	    /** 城市编号 */
	    public var cityID:Number;
	    /** 名称 */
	    public var name:String;
	    /** 头像 */
	    public var head:String;
	    /** 等级 */
	    public var level:Number;
	    /** 经验 */
	    public var exp:Number;
	    /** 经验最大值 */
	    public var expMax:Number;
	    /** 体力 */
	    public var stamina:Number;
	    /** 体力上限 */
	    public var staminaMax:Number;
	    /** 指挥 */
	    public var command:Number;
	    /** 防护 */
	    public var defense:Number;
	    /** 思维 */
	    public var mind:Number;
	    /** 行政 */
	    public var executivepower:Number;
	    /** 未加点数 */
	    public var unsetPoint:Number;
	    /** 忠诚 */
	    public var loyalty:Number;
	    /** 最大技能数量 */
    	public var maxSkillNum:Number;
	    /** 肩章装备 */
	    public var equipmentEpaulet:Number;
	    /** 帽子装备 */
	    public var equipmentCap:Number;
	    /** 衣服装备 */
	    public var equipmentClothes:Number;
	    /** 鞋子装备 */
	    public var equipmentShoe:Number;
	    /** 武器装备 */
	    public var equipmentWeapon:Number;
	    /** 状态 (1.空闲 2.执政 3.出征 4.驻扎 5.俘虏) */
	    public var state:Number;
	    /** 肩章装备对象 */
	    public var equipmentEpauletObject:Object;
	    /** 帽子装备对象 */
	    public var equipmentCapObject:Object;
	    /** 衣服装备对象 */
	    public var equipmentClothesObject:Object;
	    /** 鞋子装备对象 */
	    public var equipmentShoeObject:Object;
	    /** 武器装备对象 */
	    public var equipmentWeaponObject:Object;
	    /** 技能列表 */
    	public var skillList:ArrayCollection;
    	///////////////////
    	/** 领导 */
		public var leadership:int;
		/** 军魄 */
		public var militarySoul:int;
		/** 星级 */
		public var star:int;
		/** 统驭 */
		public var rein:int;
		/** 军魂 */
		public var militarySpirit:int;
		/** 已提升军魂 */
		public var addedMilitarySpirit:int;
		/** 品质(1.普通 2.稀有 3.史诗) */
 	   public var quality:int;
	}
}