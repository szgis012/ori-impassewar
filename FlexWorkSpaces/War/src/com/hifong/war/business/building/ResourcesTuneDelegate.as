/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
    /**
     * 木材,钢铁,石油,粮食资源操作服务代理
     *
     */
	public final class ResourcesTuneDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function ResourcesTuneDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("resourcesTuneService");
		}
		
		/**
		 * 修改木材厂工作人数
		 * 
		 */ 
		public function modifyWoodWorkerNum(cityID:int,workerNum:int):void{
			var call:Object = service.modifyWoodWorkerNum(cityID,workerNum);
			call.addResponder(responder);
		}
		
		/**
		 * 修改炼钢厂工作人数
		 * 
		 */ 
		public function modifySteelWorkerNum(cityID:int,workerNum:int):void{
			var call:Object = service.modifySteelWorkerNum(cityID,workerNum);
			call.addResponder(responder);
		}
		
		/**
		 * 修改油井工作人数
		 * 
		 */ 
		public function modifyOilWorkerNum(cityID:int,workerNum:int):void{
			var call:Object = service.modifyOilWorkerNum(cityID,workerNum);
			call.addResponder(responder);
		}
		
		/**
		 * 修改农场工作人数
		 * 
		 */ 
		public function modifyFoodWorkerNum(cityID:int,workerNum:int):void{
			var call:Object = service.modifyFoodWorkerNum(cityID,workerNum);
			call.addResponder(responder);
		}
		
		/**
		 * 同时更新四种资源工作人数
		 */ 
		public function updateResourcesWorkerEvent(cityID:int,woodWorkerNum:int,steelWorkerNum:int,oilWorkerNum:int,foodWorkerNum:int):void{
			var call:Object = service.modifyResourcesWorkerNum(cityID,woodWorkerNum,steelWorkerNum,oilWorkerNum,foodWorkerNum);
			call.addResponder(responder);
		}
		
	}
}
