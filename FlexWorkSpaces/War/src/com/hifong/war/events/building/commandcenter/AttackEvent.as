/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 攻击事件
     *
     */
	public final class AttackEvent extends CairngormEvent
	{
		/** 已编制的城市军队编号 */
		public var cityMilitaryID:int;
		/** 目标X坐标*/
		public var posX:int;
		/** 目标Y坐标*/
		public var posY:int;
		
		
		public static const ATTACK_EVENT:String = "com.hifong.war.events.AttackEvent";
		
		public function AttackEvent(cityMilitaryID:int,posX:int,posY:int) 
		{
			super( ATTACK_EVENT );
			
			this.cityMilitaryID = cityMilitaryID;
			this.posX = posX;
			this.posY = posY;
		}
	}
}
