/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.hifong.war.vo.ResTradeVO;
	import com.hifong.war.vo.ResTransportationVO;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 市场服务代理
     *
     */
	public final class MarketDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function MarketDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("marketService");
		}
		
		
		/**
		 * 运送资源
		 */
		public function transportResouce(resTransportation:ResTransportationVO,cityID:int,targetCityID:int):void{
			var call:Object = service.transportResouce(resTransportation,cityID,targetCityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得资源销售列表
		 */
		public function getResourceSalesList(cityID:int,resourceType:int,start:int,offset:int):void{
			var call:Object = service.getResourceSalesList(cityID,resourceType,start,offset);
			call.addResponder(responder);
		}
		
		/**
		 * 获得资源交易数量
		 */
		public function getResourceSalesAmount(cityID:int,resouceType:int):void{
			var call:Object = service.getResourceSalesAmount(cityID,resouceType);
			call.addResponder(responder);
		}
		
		/**
		 * 购买资源
		 */
		public function buyResource(resTradeID:int,cityID:int):void{
			var call:Object = service.buyResource(resTradeID,cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市资源销售列表
		 */
		public function getCityResourceSalesList(cityID:int):void{
			var call:Object = service.getCityResourceSalesList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 出售资源
		 */
		public function sellResource(resTrade:ResTradeVO):void{
			var call:Object = service.sellResource(resTrade);
			call.addResponder(responder);
		}
		
		/**
		 * 取消资源交易
		 */
		public function cancelResourceSale(resTradeID:int):void{
			var call:Object = service.cancelResourceSale(resTradeID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市交易队列列表
		 */
		public function getCityTradeQueueList(cityID:int):void{
			var call:Object = service.getCityTradeQueueList(cityID);
			call.addResponder(responder);
		}
		
	}
}
