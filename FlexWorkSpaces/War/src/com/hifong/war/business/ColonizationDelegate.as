/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	/**
     * 宣战服务代理
     *
     */
	public final class ColonizationDelegate
	{
		 //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function ColonizationDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("colonizationService");
		}
		
		/**
		 * 获得城市殖民数量
		 */
		public function getCityColonizationNum(cityID:int):void{
			var call:Object = service.getCityColonizationNum(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市殖民列表
		 */
		public function getCityColonizationList(cityID:int):void{
			var call:Object = service.getCityColonizationList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 征收物资
		 */
		public function impose(colonizationID:int,type:int):void{
			var call:Object = service.impose(colonizationID,type);
			call.addResponder(responder);
		}
		
	}
}
