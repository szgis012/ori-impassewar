/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.heavyfactory
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ArmyVO;
	import com.hifong.war.vo.CityArmyVO;

    /**
     * 拆卸车辆事件
     *
     */
	public final class DisassembleVehicleEvent extends CairngormEvent
	{
		/** 兵种信息*/
		public var army:ArmyVO;
		
		/** 要更新的军队信息 */
		public var cityArmy:CityArmyVO;
		
		/**  要组装车辆的数量*/
		public var num:int;
		
		public static const DISASSEMBLEVEHICLE_EVENT:String = "com.hifong.war.events.DisassembleVehicleEvent";
		
		public function DisassembleVehicleEvent(army:ArmyVO,cityArmy:CityArmyVO, num:int) 
		{
			super( DISASSEMBLEVEHICLE_EVENT );
			
			this.army = army;
			this.cityArmy = cityArmy;
			this.num = num;
		}
	}
}
