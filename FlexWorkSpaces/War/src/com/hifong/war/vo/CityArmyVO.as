/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.CityArmy")]
	[Bindable]
	public class CityArmyVO implements IValueObject {
		/** 城市兵力编号 */
	    public var cityArmyID:int;
	    /** 城市编号 */
	    public var cityID:int;
	    /** 兵种编号 */
	    public var armyID:int;
	    /** 兵的数量 */
	    public var num:int;
	    /** 状态 (0.未编制 1.防御 2.空闲 3.行进 4.战斗) */
	    public var state:int;
	    
	}
}