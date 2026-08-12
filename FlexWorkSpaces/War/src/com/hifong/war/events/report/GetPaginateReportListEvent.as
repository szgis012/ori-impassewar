/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.report
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得指定事件类型的分页报告信息事件
     *
     */
	public final class GetPaginateReportListEvent extends CairngormEvent
	{
		/** 报告类型,ReportTypeConstant中定义 */
		public var reportType:int;
		/** 要显示的页数,1开始*/
		public var pageNum:int; 

		
		public static const GETPAGINATEREPORTLIST_EVENT:String = "com.hifong.war.events.GetPaginateReportListEvent";
		
		public function GetPaginateReportListEvent(reportType:int, pageNum:int) 
		{
			super( GETPAGINATEREPORTLIST_EVENT );
			
			this.reportType = reportType;
			this.pageNum = pageNum;
		}
	}
}
