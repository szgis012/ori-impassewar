/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 排程服务代理
     *
     */
	public final class ProductionQueueDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function ProductionQueueDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("productionQueueService");
		}
		
		/**
	     * 获得城市指定类型的进程列表
	     * @param cityID
	     * @param type ProductionProcessTypeConstant中定义
	     */
	    public function getProductionProcessList(cityID:int,type:int):void{
	    	var call:Object = service.getProductionQueueList(cityID,type);
			call.addResponder(responder);
	    }

	}
}
