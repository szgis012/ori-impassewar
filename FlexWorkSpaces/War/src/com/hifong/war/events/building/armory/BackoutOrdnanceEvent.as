/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.armory
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 拆卸军械事件
     *
     */
	public final class BackoutOrdnanceEvent extends CairngormEvent
	{
		/** 城市军械编号 */
	    public var cityOrdnanceID:int;
	    /** 军械数量 */
	    public var num:int;
	    
		public static const BACKOUTORDNANCE_EVENT:String = "com.hifong.war.events.BackoutOrdnanceEvent";
		
		public function BackoutOrdnanceEvent(cityOrdnanceID:int,num:int) 
		{
			super( BACKOUTORDNANCE_EVENT );
			
			this.cityOrdnanceID = cityOrdnanceID;
			this.num = num;
		}
	}
}
