/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 重型工厂服务代理
     *
     */
	public final class HeavyFactoryDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function HeavyFactoryDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("heavyFactoryService");
		}
		
		/**
		 * 组装车辆
		 * 
		 * @param cityID 城市编号
		 * @param armyID 士兵类型
		 * @param num 人数
		 */  
		public function assembleVehicle(cityID:int,armyID:int, num:int):void{
			var call:Object = service.assembleVehicle(cityID,armyID,num);
			call.addResponder(responder);
		}
		
		/**
		 * 拆卸车辆
		 * 
		 * @param cityID 城市编号
		 * @param armyID 士兵类型
		 * @param num 人数
		 */ 
		public function disassembleVehicle(cityID:int,armyID:int, num:int):void{
			var call:Object = service.disassembleVehicle(cityID,armyID,num);
			call.addResponder(responder);
		}
	}
}
