/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.report
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得指定类型报告的总共数量事件
     *
     */
	public final class GetReportCountEvent extends CairngormEvent
	{
		/** 报告类型 ReportTypeConstant中定义*/
		public var reportType:int;
		
		
		public static const GETREPORTCOUNT_EVENT:String = "com.hifong.war.events.GetReportCountEvent";
		
		public function GetReportCountEvent(reportType:int) 
		{
			super( GETREPORTCOUNT_EVENT );
			
			this.reportType = reportType;
		}
	}
}
