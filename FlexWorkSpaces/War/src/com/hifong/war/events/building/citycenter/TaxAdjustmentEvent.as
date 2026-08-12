/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 税率调整事件
     *
     */
	public final class TaxAdjustmentEvent extends CairngormEvent
	{
		/** 税率新值 */
		public var newValue:Number;
		
		public static const TAXADJUSTMENT_EVENT:String = "com.hifong.war.events.TaxAdjustmentEvent";
		
		public function TaxAdjustmentEvent(newValue:Number) 
		{
			super( TAXADJUSTMENT_EVENT );
			
			this.newValue = newValue;
		}
	}
}
