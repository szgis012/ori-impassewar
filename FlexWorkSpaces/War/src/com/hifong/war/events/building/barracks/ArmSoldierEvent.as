/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.barracks
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.CityArmyVO;

    /**
     * 武装士兵事件
     *
     */
	public final class ArmSoldierEvent extends CairngormEvent
	{
		/** 要更新的军队信息 */
		public var cityArmy:CityArmyVO;
		
		/** 要武装士兵数量*/
		public var num:int;
		
		public static const ARMSOLDIER_EVENT:String = "com.hifong.war.events.ArmSoldierEvent";
		
		public function ArmSoldierEvent(cityArmy:CityArmyVO,num:int) 
		{
			super( ARMSOLDIER_EVENT );
			
			this.cityArmy = cityArmy;
			this.num = num;
		}
	}
}
