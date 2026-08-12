/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 城市服务代理
     *
     */
	public final class CityDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function CityDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("cityService");
		}
		
		/**
		 * 获得城市资源
		 */
		public function getCityResourcesNum(cityID:int):void{
			var call:Object = service.getCityResourcesNum(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市资源产量
		 */
		public function getCityResourcesOutput(cityID:int):void{
			var call:Object = service.getCityResourcesOutput(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市人口
		 */
		public function getCityPopulation(cityID:int):void{
			var call:Object = service.getCityPopulation(cityID);
			call.addResponder(responder);
		}
				
		/**
		 * 根据城市名称获得城市编号
		 */
		public function getCityIDByCityName(cityName:String):void{
			var call:Object = service.getCityIDByCityName(cityName);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市坐标获得城市编号
		 */
		public function getCityIDByCityPos(posX:int,posY:int):void{
			var call:Object = service.getCityIDByCityPos(posX,posY);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市编号获得CityInfoVO信息
		 */
		public function getCityInfoByCityID(cityID:int):void{
			var call:Object = service.getCityInfoByCityID(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市资源上限
		 */
		public function getCityResourcesNumMax(cityID:int):void{
			var call:Object = service.getCityResourcesNumMax(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市的税率和治安信息
		 */ 
		public function getCityTaxAndSecurity(cityID:int):void{
			var call:Object = service.getCityTaxAndSecurity(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市编号获得城市的资源消耗信息(包括金钱，食物，石油的消耗)
		 * @param cityID
		 * @return
		 */
		public function getCityResourcesConsume(cityID:int):void{
			var call:Object = service.getCityResourcesConsume(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市编号获得城市信息(返回整个City对象 )
		 * @param cityID
		 * @return
		 */
		public function getCityByID(cityID:int):void{
			var call:Object = service.getCityByID(cityID);
			call.addResponder(responder);
		}
		/**
		 * 根据城市编号获得城市及城市资源信息 (返回整个City对象 )
		 * @param cityID
		 * @return
		 */
		 public function getCityWithCityResourceByID(cityID:int):void{
		 	var call:Object=service.getCityWithCityResourceByID(cityID);
		 	call.addResponder(responder);
		 }
		
		/**
		 * 获得城市空闲商人数量
		 * @param cityID
		 * @return
		 */
		public function getCityBusinessFree(cityID:int):void{
			var call:Object = service.getCityBusinessFree(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 判断城市名是否存在
		 */
		public function isCityNameExisted(cityName:String):void{
			var call:Object = service.isCityNameExisted(cityName);
			call.addResponder(responder);
		}
		
		/**
		 * 交换(重置)城市资源
		 */
		public function exchangeCityResources(cityID:int, exchangedWoodNum:Number, exchangedSteelNum:Number, exchangedOilNum:Number, exchangedFoodNum:Number):void{
			var call:Object = service.exchangeCityResources(cityID, exchangedWoodNum, exchangedSteelNum, exchangedOilNum, exchangedFoodNum);
			call.addResponder(responder);
		}
		
	}
}
