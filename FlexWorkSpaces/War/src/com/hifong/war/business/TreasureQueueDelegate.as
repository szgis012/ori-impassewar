/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 宝物效果进程代理
     *
     */
	public final class TreasureQueueDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function TreasureQueueDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("treasureQueueService");
		}
		
		
		/**
	     * 获得城市的所有宝物效果进程列表
	     */
	    public function getTreasureQueueListByCityID(cityID:int):void{
	    	var call:Object = service.getTreasureQueueListByCityID(cityID);
			call.addResponder(responder);
	    }
		
		public function deleteTreasureQueue(treasureQueueID:int):void{
			var call:Object = service.cancelTreasureQueue(treasureQueueID);
			call.addResponder(responder);
		}
	}
}
