/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 出征队列服务代理
     *
     */
	public final class DepoyQueueDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
		public function DepoyQueueDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("depoyQueueService");
		}
		
		/**
		 * 获得出征队列信息
		 */
		public function getDepoyQueueByID(depoyQueueID:int):void{
			var call:Object = service.getDepoyQueueByID(depoyQueueID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市出征队列列表
		 */
		public function getCityDepoyQueueList(cityID:int):void{
			var call:Object = service.getCityDepoyQueueList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市进攻队列列表
		 */
		public function getCityAttackDepoyQueueList(cityID:int):void{
			var call:Object = service.getCityAttackDepoyQueueList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市防守队列列表
		 */
		public function getCityDefenseDepoyQueueList(cityID:int):void{
			var call:Object = service.getCityDefenseDepoyQueueList(cityID);
			call.addResponder(responder);
		}
		
	}
}
