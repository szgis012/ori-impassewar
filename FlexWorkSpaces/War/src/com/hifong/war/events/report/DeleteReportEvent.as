/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.report
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 删除报告事件
     *
     */
	public final class DeleteReportEvent extends CairngormEvent
	{
		/** 要保存的报告数组*/
		public var reportIDs:Array;
		
		public static const DELETEREPORT_EVENT:String = "com.hifong.war.events.DeleteReportEvent";
		
		public function DeleteReportEvent(reportIDs:Array) 
		{
			super( DELETEREPORT_EVENT );
			
			this.reportIDs  = reportIDs;
		}
	}
}
