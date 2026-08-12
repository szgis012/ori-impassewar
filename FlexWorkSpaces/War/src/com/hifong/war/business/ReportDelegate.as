/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 报告信息代理
     *
     */
	public final class ReportDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function ReportDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("reportService");
		}
		
		/**
	     * 删除多个报告信息
	     * @param reportIDs
	     */
	    public function deleteReport(reportIDs:Array):void{
	    	var call:Object = service.deleteReport(reportIDs);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 保存多个报告信息
	     * @param reportIDs
	     */
	    public function saveReport(reportIDs:Array):void{
	    	var call:Object = service.saveReport(reportIDs);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 设置多个报告为已读状态
	     * @param reportIDs
	     */
	    public function readReport(reportIDs:Array):void{
	    	var call:Object = service.readReport(reportIDs);
			call.addResponder(responder);
	    }
	    
	    
	    /**
	     * 得到玩家某类报告信息(带分页)
	     * @param playerID 玩家编号
	     * @param type 报告类型ReportTypeConstant定义
	     * @param start 记录的开始位置,以0开始的索引
	     * @param offset 获取的记录条数
	     * @return ArrayCollection 其中元素ReportVO
	     */
	    public function getPaginateReportList( playerID:int, type:int, start:int, offset:int):void{
	    	var call:Object = service.getPaginateReportList(playerID,type,start,offset);
			call.addResponder(responder);
	    }
	    
	     /**
		 * 获得某个玩家某种报告的总共数量
		 * @param playerID 玩家编号
		 * @param type 报告类型ReportTypeConstant定义
		 * @return
		 */
		public function getReportCount(playerID:int, type:int):void{
			var call:Object = service.getReportCount(playerID,type);
			call.addResponder(responder);
		}
		
	}
}
