/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.report
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ReportDelegate;
	import com.hifong.war.common.ReportInfo;
	import com.hifong.war.events.report.GetReportCountEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得指定类型报告的总共数量事件
     *
     */
	public final class GetReportCountCommand implements ICommand, IResponder
	{
		private var model:ModelLocator =  ModelLocator.getInstance();
		private var reportInfo:ReportInfo = model.reportInfo;
		
		private var reportType:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetReportCountEvent = event as GetReportCountEvent;
			this.reportType = evt.reportType;
			var delegate:ReportDelegate = new ReportDelegate( this );
			delegate.getReportCount(model.playerInfo.playerID,evt.reportType);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			
			//保证类型一致
			if(this.reportType == reportInfo.currentReportType){
				var num:int = int(rs.result);
			
				reportInfo.totalPageNum = Math.ceil(num / reportInfo.REPORT_COUNT);
			}
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
