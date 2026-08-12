/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.report
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 阅读报告事件
     *
     */
	public final class ReadReportEvent extends CairngormEvent
	{
		public static const READREPORT_EVENT:String = "com.hifong.war.events.ReadReportEvent";
		
		//报告编号数组
		public var reportIDs:Array;
		
		public function ReadReportEvent(reportIDs:Array) 
		{
			super( READREPORT_EVENT );
			this.reportIDs = reportIDs;
		}
	}
}
