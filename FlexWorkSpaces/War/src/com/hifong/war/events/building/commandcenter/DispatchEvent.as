/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 派遣事件
     *
     */
	public final class DispatchEvent extends CairngormEvent
	{
		/** 已编制的城市军队编号 */
		public var cityMilitaryID:int;
		/** 目标X坐标*/
		public var posX:int;
		/** 目标Y坐标*/
		public var posY:int;
		/** 携带食物数量*/
		public var carryFood:int;
		/** 携带木材数量*/
		public var carryWood:int;
		/** 携带石油数量*/
		public var carryOil:int;
		/** 携带钢铁数量*/
		public var carrySteel:int;
		/** 携带金钱数量*/
		public var carryMoney:int;
		
		public static const DISPATCH_EVENT:String = "com.hifong.war.events.DispatchEvent";
		
		public function DispatchEvent(cityMilitaryID:int,posX:int,posY:int,carryFood:int,carryWood:int,carryOil:int,carrySteel:int,carryMoney:int) 
		{
			super( DISPATCH_EVENT );
			
			this.cityMilitaryID = cityMilitaryID;
			this.posX = posX;
			this.posY = posY;
			this.carryFood = carryFood;
			this.carryMoney = carryMoney;
			this.carryOil = carryOil ;
			this.carrySteel = carrySteel;
			this.carryWood = carryWood;
		}
	}
}
