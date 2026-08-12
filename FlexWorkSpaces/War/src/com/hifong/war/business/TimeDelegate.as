/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 时间服务代理
     *
     */
	public final class TimeDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function TimeDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("timeService");
		}
		

		/**
		 * 获得服务器时间
		 */
		public function getServerTime():void{
			var call:Object = service.getServerTime();
			call.addResponder(responder);
		}		
		
	}
}
