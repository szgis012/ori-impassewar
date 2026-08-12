/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.MilitaryArmy")]
	[Bindable]
	public class MilitaryArmyVO implements IValueObject {
		
		/** 兵种编号 */
		public var armyID:Number;
		/** 数量 */
		public var amount:Number;
		/** 战场X坐标 */
		public var posX:Number;
		/** 战场Y坐标 */
		public var posY:Number;
		/** 是否移动 */
		public var haveMoved:Boolean;
		/** 攻击类型(0.未操作 1.攻击 2.防御) */
		public var attackType:Number;
		/** 兵种信息 */
		public var army:ArmyVO;
    	
	}
}