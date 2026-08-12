/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.report
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.ReportDelegate;
	import com.hifong.war.common.ReportInfo;
	import com.hifong.war.events.report.GetPaginateReportListEvent;
	import com.hifong.war.events.report.SaveReportEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     * 处理报告归档事件
     *
     */
	public final class SaveReportCommand implements ICommand, IResponder
	{
		private var reportInfo:ReportInfo  = ModelLocator.getInstance().reportInfo;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:SaveReportEvent = event as SaveReportEvent;
			var delegate:ReportDelegate = new ReportDelegate( this );
			delegate.saveReport(evt.reportIDs);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPaginateReportListEvent(reportInfo.currentReportType,reportInfo.currentPageNum));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
