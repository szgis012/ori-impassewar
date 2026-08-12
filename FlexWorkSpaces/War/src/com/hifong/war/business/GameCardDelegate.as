/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 游戏卡服务代理
     *
     */
	public final class GameCardDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;


		public function GameCardDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("gameCardService");
		}
		
		/**
		 * 激活游戏卡
		 */
		public function activateGameCard(playerID:int,gameCardNO:String,type:int):void{
			var call:Object = service.activateGameCard(playerID,gameCardNO,type);
			call.addResponder(responder);
		}
		
	}
}
