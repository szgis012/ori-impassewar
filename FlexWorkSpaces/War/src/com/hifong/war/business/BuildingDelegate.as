/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 建筑服务代理
     *
     */
	public final class BuildingDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function BuildingDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("buildingService");
		}
		
		/**
		 * 获得所有的建筑信息列表
		 */ 
		public function getBuildingList():void{
			var call:Object = service.getBuildingList();
			call.addResponder(responder);
		}
		
		/** 客户端建造，升级，拆除完成时调用该方法可以及时刷新信息*/
		public function clientProcessFinished( processQueueID:int ):void{
			var call:Object = service.clientProcessFinished(processQueueID);
			call.addResponder(responder);
		}

		/**
        * 获得指定编号的CityBuilding对象
        * 注意：result函数接收的结果是服务器端CityBuilding对象，而不是客户端的CityBuilding
        */ 
        public function getCityBuildingByID(cityBuildingID:int):void{
        	var call:Object = service.getCityBuildingByID(cityBuildingID);
			call.addResponder(responder);
        }
        
        /**
        * 获得指定城市编号的CityBuilding对象列表
        * 注意：result函数接收的结果是服务器端CityBuilding对象列表，而不是客户端的CityBuilding对象列表
        */ 
		public function getCityBuildingListByCityID(cityID:int):void{
			var call:Object = service.getCityBuildingListByCityID(cityID);
			call.addResponder(responder);
		}
		
		/**
        * 获得指定城市编号可建造的CityBuilding对象列表
        * 注意：result函数接收的结果是服务器端CityBuilding对象列表，而不是客户端的CityBuilding对象列表
        */ 
		public function getCityAvailableBuildingList(cityID:int):void{
			var call:Object = service.getCityAvailableBuildingList(cityID);
			call.addResponder(responder);
		}
		
		/**
        * 建造(或升级)建筑
        * 注意：result函数接收的结果是cityBuildingID
        */ 
		public function buildBuilding(cityID:int,buildingID:int,position:int):void{
			var call:Object = service.buildBuilding(cityID,buildingID,position);
			call.addResponder(responder);
		}
		
		/** 拆除建筑 */
		public function backoutBuilding(cityBuildingID:int):void {
			var call:Object = service.backoutBuilding(cityBuildingID);
			call.addResponder(responder);
		}
		
		/** 取消建造，升级，拆除进程 */
		public function cancelProcess(processQueueID:int):void {
			var call:Object = service.cancelProcess(processQueueID);
			call.addResponder(responder);
		}
	}
}
