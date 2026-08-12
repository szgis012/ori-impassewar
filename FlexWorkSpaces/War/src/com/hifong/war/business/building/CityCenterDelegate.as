/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 城镇中心服务代理
     *
     */
	public final class CityCenterDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function CityCenterDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("cityCenterService");
		}
		
		
		/**
		 * 更改城市名称
		 */
		public function changeCityName(cityID:int,newCityName:String):void{
			var call:Object = service.changeCityName(cityID,newCityName);
			call.addResponder(responder);
		}
		
		/**
		 * 征召市民
		 */
		public function enlistCitizen(cityID:int,enlistNumber:int):void{
			var call:Object = service.enlistCitizen(cityID,enlistNumber);
			call.addResponder(responder);
		}
		
		/**
		 * 获取城市建造队列
		 */ 
		public function getBuildProcess(cityID:int):void{
			var call:Object = service.getBuildProcess(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 进行阅兵仪式
		 */ 
		public function doGuardsParade(cityID:int):void{
			var call:Object = service.doGuardsParade(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 进行节日庆典
		 */ 
		public function doHolidayCelebrate(cityID:int):void{
			var call:Object = service.doHolidayCelebrate(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 进行安全巡查
		 */ 
		public function doSafetyPatrol(cityID:int):void{
			var call:Object = service.doSafetyPatrol(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 征收物资
		 */ 
		public function imposeMaterial(cityID:int):void{
			var call:Object = service.imposeMaterial(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 调整税率
		 */ 
		public function adjustTax(cityID:int,newValue:Number):void{
			var call:Object = service.adjustTax(cityID,newValue);
			call.addResponder(responder);
		}
		
		/**
		 * 取消征召市民
		 */  
		public function cancelEnlistCitizen(productionProcessID:int):void{
			var call:Object = service.cancelEnlistCitizen(productionProcessID);
			call.addResponder(responder);
		}
		
		 /**
		 *  客户端完成征召市民计算时调用该方法可以及时刷新信息
		 * @param productionProcessID
		 */
		public function clientEnlistCitizenFinished(productionProcessID:int):void{
			var call:Object = service.clientEnlistCitizenFinished(productionProcessID);
			call.addResponder(responder);
		}
		
		/**
		 *  获得招募市民的进程
		 */
		public function getEnlistCitizenProcess(cityID:int):void{
			var call:Object = service.getEnlistCitizenProcess(cityID);
			call.addResponder(responder);
		}
	}
}
