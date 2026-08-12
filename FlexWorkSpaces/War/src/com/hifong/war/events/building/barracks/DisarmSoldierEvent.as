/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.barracks
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.CityArmyVO;

    /**
     * 解除士兵武装事件
     * 
     *
     */
	public final class DisarmSoldierEvent extends CairngormEvent
	{
		/** 要更新的军队信息 */
		public var cityArmy:CityArmyVO;
		
		/** 士兵数量*/
		public var num:int;
		
		public static const DISARMSOLDIER_EVENT:String = "com.hifong.war.events.DisarmSoldierEvent";
		
		public function DisarmSoldierEvent(cityArmy:CityArmyVO,num:int) 
		{
			super( DISARMSOLDIER_EVENT );
			
			this.cityArmy = cityArmy;
			this.num = num;
		}
	}
}
