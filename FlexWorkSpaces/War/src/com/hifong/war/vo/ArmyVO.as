/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="com.war.domain.Army")]
	[Bindable]
	public class ArmyVO implements IValueObject {
		/** 兵种编号 */
	    public var armyID:int;
	    /** 兵种名称 */
	    public var name:String;
	    /** 图片 */
	    public var image:String;
	    /** 兵种的描述 */
	    public var description:String;
	    /** 生命值 */
	    public var life:int;
	    /** 攻击值 */
	    public var attack:int;
	    /** 防御值 */
	    public var defense:int;
	    /** 攻击范围 */
	    public var range:int;
	    /** 移动速度 */
	    public var speed:int;
	    /** 负重 */
	    public var carry:int;
	    /** 消耗食物 */
	    public var costFood:int;
	    /** 消耗军费 */
	    public var costMoney:int;
	    /** 消耗石油 */
	    public var costOil:int;
	    /** 所占人口 */
	    public var population:int;
	    /** 攻击类型 */
	    public var attackType:int;
	    /** 防御类型 */
	    public var defenseType:int;
	    /** 兵种ArmyTypeConstant中定义 */
	    public var type:int;
	    /** 兵种招募所需条件列表,ArmyDepend */
    	public var armyDependList:ArrayCollection;
    	/** 兵种招募所需的前提建筑，科技条件,ConstraintDepend*/
    	public var constraintDepend:Object;
	}
}