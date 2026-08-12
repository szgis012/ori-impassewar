/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.report
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 报告存档事件 
     *
     */
	public final class SaveReportEvent extends CairngormEvent
	{
		/** 要保存的报告数组*/
		public var reportIDs:Array;
		
		public static const SAVEREPORT_EVENT:String = "com.hifong.war.events.SaveReportEvent";
		
		public function SaveReportEvent(reportIDs:Array) 
		{
			super( SAVEREPORT_EVENT );
			
			this.reportIDs = reportIDs;
		}
	}
}
