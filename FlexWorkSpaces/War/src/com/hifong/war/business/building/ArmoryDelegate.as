/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 兵工厂服务代理
     *
     */
	public final class ArmoryDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function ArmoryDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("armoryService");
		}
		
		
		/**
		 * 生产指定军械
		 * 
		 * @param cityID 城市编号
		 * @param ordnanceID 军械编号
		 * @param num 军械数量
		 * @return CityOrdnanceVO
		 */
		public function produceOrdnance(cityID:int, ordnanceID:int, num:int):void{
			var call:Object = service.produceOrdnance(cityID,ordnanceID,num);
			call.addResponder(responder);
		}
		
		/**
		 * 拆卸指定数量的军械
		 * 
		 * @param cityOrdnanceID 城市军械编号
		 * @param num 军械数量
		 */
		public function backoutOrdnance(cityOrdnanceID:int, num:int):void{
			var call:Object = service.backoutOrdnance(cityOrdnanceID,num);
			call.addResponder(responder);
		}
		
		/**
		 * 客户端完成生产计算时调用该方法可以及时刷新信息
		 * @param productionProcessID
		 */
		public function clientProcessFinished(productionProcessID:int):void{
			var call:Object = service.clientProcessFinished(productionProcessID);
			call.addResponder(responder);
		}
		
		/**
		 * 取消军械生产
		 * @param productionProcessID
		 */
		public function cancelProduceOrdnance(productionProcessID:int):void{
			var call:Object = service.cancelProduceOrdnance(productionProcessID);
			call.addResponder(responder);
		}
		
		/**
		 * 立即完成所有的军械生产进程
		 */ 
		public function finishAllProduceProcess(cityID:int):void{
			var call:Object = service.finishAllProduceProcess(cityID);
			call.addResponder(responder);
		}
	}
}
