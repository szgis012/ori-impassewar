/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.airport
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ArmyVO;
	import com.hifong.war.vo.CityArmyVO;

    /**
     * 组装飞机事件
     *
     */
	public final class AssemblePlaneEvent extends CairngormEvent
	{
		/** 兵种信息*/
		public var army:ArmyVO;
		
		/** 要更新的军队信息 */
		public var cityArmy:CityArmyVO;
		
		/** 要组装飞机的数量*/
		public var num:int;
		
		public static const ASSEMBLEPLANE_EVENT:String = "com.hifong.war.events.AssemblePlaneEvent";
		
		public function AssemblePlaneEvent(army:ArmyVO,cityArmy:CityArmyVO,num:int) 
		{
			super( ASSEMBLEPLANE_EVENT );
			
			this.army = army;
			this.cityArmy = cityArmy;
			this.num = num;
		}
	}
}
