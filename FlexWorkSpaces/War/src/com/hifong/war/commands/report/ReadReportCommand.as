/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.report
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ReportDelegate;
	import com.hifong.war.events.report.ReadReportEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理阅读报告事件
     *
     */
	public final class ReadReportCommand implements ICommand, IResponder
	{
		public function execute(event:CairngormEvent) : void
		{
			var evt:ReadReportEvent = event as ReadReportEvent;
			var delegate:ReportDelegate = new ReportDelegate( this );
			delegate.readReport(evt.reportIDs);
		}
		
		public function result(data:Object) : void
		{
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
