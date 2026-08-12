/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.BattleArmy")]
	[Bindable]
	public class BattleArmyVO implements IValueObject {
		
		/** 战斗士兵编号 */
	    public var battleArmyID:Number;
	    /** 战斗编号 */
	    public var battleID:Number;
	    /** 军队编号 */
	    public var militaryID:Number;
	    /** 士兵势力(1.进攻方 2.防守方) */
    	public var armyForce:Number;
	    /** 士兵索引 */
	    public var armyIndex:Number;
	    /** 士兵编号 */
	    public var armyID:Number;
	    /** 数量 */
	    public var amount:Number;
	    /** X坐标 */
	    public var posX:Number;
	    /** Y坐标 */
	    public var posY:Number;
	    /** 是否移动(0.未移动 1.已移动) */
	    public var haveMoved:Number;
	    /** 攻击类型(0.未操作 1.攻击 2.防御) */
	    public var attackType:Number;
	    /** 士兵信息 */
	    public var army:ArmyVO;
    	
	}
}