/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 任务服务代理
     *
     */
	public final class TaskDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function TaskDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("taskService");
		}
		
		/**
		 * 得到玩家指定类型的任务信息列表
		 * 
		 */ 
		public function getPlayerTaskList(playerID:int,type:int):void{
			var call:Object = service.getPlayerTaskList(playerID,type);
			call.addResponder(responder);
		}
		
		/**
		 *  获取任务奖励
		 * 
		 */ 
		public function getReward(playerTaskID:int):void{
			var call:Object = service.getReward(playerTaskID);
			call.addResponder(responder);
		}

	}
}
