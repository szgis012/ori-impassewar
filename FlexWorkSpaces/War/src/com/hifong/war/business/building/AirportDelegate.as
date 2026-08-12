/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 飞机场服务代理
     *
     */
	public final class AirportDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function AirportDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("airportService");
		}
		
		/**
		 * 组装飞机
		 * 
		 * @param cityID 城市编号
		 * @param armyID 士兵类型
		 * @param num 人数
		 */  
		public function assemblePlane(cityID:int,armyID:int, num:int):void{
			var call:Object = service.assemblePlane(cityID,armyID,num);
			call.addResponder(responder);
		}
		
		/**
		 * 拆卸飞机
		 * 
		 * @param cityID 城市编号
		 * @param armyID 士兵类型
		 * @param num 人数
		 */ 
		public function disassemblePlane(cityID:int,armyID:int, num:int):void{
			var call:Object = service.disassemblePlane(cityID,armyID,num);
			call.addResponder(responder);
		}
	}
}
