/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
    /**
     * 装备服务代理
     *
     */
	public final class EquipmentDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
		
		//远程军队对象
		private var equipmentService:Object;
	
	
		public function EquipmentDelegate(responder:IResponder)
		{
			this.responder = responder;
			
			this.equipmentService = ServiceLocator.getInstance().getRemoteObject("equipmentService");
		}

		
		/**
		 * 根据种类获得玩家装备列表
		 */
		public function getPlayerEquipmentListByCategory(playerID:int,category:int):void{
			var call:Object = equipmentService.getPlayerEquipmentListByCategory(playerID,category);
			call.addResponder(responder);
		}
		
		
	}
}
