/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 军械服务代理
     *
     */
	public final class OrdnanceDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function OrdnanceDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("ordnanceService");
		}
		
		
		/**
		 * 获得所有军械信息列表
		 */
		public function getOrdnanceListByCountry(contry:int):void{
			var call:Object = service.getOrdnanceListByCountry(contry);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市拥有的可用军械信息列表
		 */
		public function getCityOrdnanceList(cityID:int):void{
			var call:Object = service.getCityOrdnanceList(cityID);
			call.addResponder(responder);
		}
		
	}
}
