/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.hifong.war.business.Services;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
    /**
     * 要塞建筑service代理
     *
     */
	public final class ShbuildingServiceDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function ShbuildingServiceDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("shbuildingService");
		}
		
	}
}
