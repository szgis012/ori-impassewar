/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.report
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.ReportDelegate;
	import com.hifong.war.events.report.GetPaginateReportListEvent;
	import com.hifong.war.events.report.GetReportCountEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得分页报告信息事件
     *
     */
	public final class GetPaginateReportListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		/** 报告类型,ReportTypeConstant中定义 */
		private var reportType:int;
		/** 要显示的页数,1开始*/
		private var pageNum:int; 

		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetPaginateReportListEvent = event as GetPaginateReportListEvent;
			this.reportType = evt.reportType;
			this.pageNum = evt.pageNum;
			//获得数据开始的索引位置
			var start:int = (evt.pageNum-1) * model.reportInfo.REPORT_COUNT;
			
			var delegate:ReportDelegate = new ReportDelegate( this );
			delegate.getPaginateReportList(model.playerInfo.playerID,this.reportType,start,model.reportInfo.REPORT_COUNT);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			
			model.reportInfo.reportList = rs.result as ArrayCollection;
			model.reportInfo.currentReportType = this.reportType;
			//如果报告列表为空
			if(model.reportInfo.reportList.length>0){
				model.reportInfo.currentPageNum = this.pageNum;
			}else{
				model.reportInfo.currentPageNum = 0;
			}
			
			//获得该报告类型的总数
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetReportCountEvent(this.reportType));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
