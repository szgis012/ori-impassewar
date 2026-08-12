/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.armory
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 军械制造事件
     *
     */
	public final class ProduceOrdnanceEvent extends CairngormEvent
	{
		 /** 军械编号 */
	    public var ordnanceID:int;
	    /** 军械数量 */
	    public var num:int;
    
    
		public static const PRODUCEORDNANCE_EVENT:String = "com.hifong.war.events.ProduceOrdnanceEvent";
		
		public function ProduceOrdnanceEvent(ordnanceID:int,num:int) 
		{
			super( PRODUCEORDNANCE_EVENT );
			
			this.ordnanceID = ordnanceID;
			this.num = num;
		}
	}
}
