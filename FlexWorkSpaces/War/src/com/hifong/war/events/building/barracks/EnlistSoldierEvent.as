/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.barracks
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 招募新兵事件
     *
     */
	public final class EnlistSoldierEvent extends CairngormEvent
	{
		/** 招募数量 */
		public var enlistNum:int;
		
		public static const ENLISTSOLDIER_EVENT:String = "com.hifong.war.events.EnlistSoldierEvent";
		
		public function EnlistSoldierEvent(enlistNum:int) 
		{
			super( ENLISTSOLDIER_EVENT );
			this.enlistNum = enlistNum;
		}
	}
}
