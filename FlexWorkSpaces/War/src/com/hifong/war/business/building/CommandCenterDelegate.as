/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 指挥中心服务代理
     *
     */
	public final class CommandCenterDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function CommandCenterDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("militaryService");
		}
		
		/**
		 * 创建军队
		 */
		public function createCityMilitary(cityID:int,name:String,cityHeroID:int):void{
			var call:Object = service.createCityMilitary(cityID,name,cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 解散军队
		 */
		public function dismissCityMilitary(cityMilitaryID:int):void{
			var call:Object = service.dismissCityMilitary(cityMilitaryID);
			call.addResponder(responder);
		}
		
		/**
		 * 更换指挥官
		 */
		public function changeOfficer(cityMilitaryID:int,cityHeroID:int):void{
			var call:Object = service.changeOfficer(cityMilitaryID,cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 军队更名
		 */
		public function renameCityMilitary(cityMilitaryID:int,name:String):void{
			var call:Object = service.renameCityMilitary(cityMilitaryID,name);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市军队列表
		 */
		public function getCityMilitaryList(cityID:int):void{
			var call:Object = service.getCityMilitaryList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市兵力列表
		 */
		public function getCityArmyList(cityID:int):void{
			var call:Object = service.getCityArmyList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 编制军队
		 */ 
		public function tuneCityMilitary(cityMilitaryID:int, militaryArmyStr:String):void{
			var call:Object = service.tuneCityMilitary(cityMilitaryID, militaryArmyStr);
			call.addResponder(responder);
		}
		
		/**
		 * 处理批量更改城市未编制军队信息
		 */ 
		public function batchModifyCityArmy(cityArmyInfo:String):void{
			var call:Object = service.batchModifyCityArmy(cityArmyInfo);
			call.addResponder(responder);
		}
		
		/**
		 * 侦察地图上的某个点的信息
		 * @param fromCityID 侦察方城市编号
		 * @param num 侦察兵的数量
		 * @param toPosX 目标点X坐标值
		 * @param toPosY 目标点Y坐标值
		 * @return
		 */
		public function spy(fromCityID:int,num:int,toPosX:int,toPosY:int):void{
			var call:Object = service.spy(fromCityID,num,toPosX,toPosY);
			call.addResponder(responder);
		}
		
		/**
		 * 攻击地图上的某个点
		 * @param cityMilitaryID 已编制的城市军队编号
		 * @param posX 地图X坐标
		 * @param posY 地图Y坐标
		 * @param policy 策略
		 * @return DepoyQueue
		 */
		public function attack(cityMilitaryID:int,posX:int,posY:int):void{
			var call:Object = service.attack(cityMilitaryID,posX,posY);
			call.addResponder(responder);
		}
		
		/**
		 * 向地图上的某个点派遣军队
		 * @param cityMilitaryID 已编制的城市军队编号
		 * @param posX 地图X坐标
		 * @param posY 地图Y坐标
		 * @param carryFood 携带食物的数量
		 * @param carryWood 携带木材的数量
		 * @param carryOil 携带石油的数量
		 * @param carrySteel 携带钢铁的数量
		 * @param carryMoney 携带金钱的数量
		 * @return DepoyQueue
		 */
		public function dispatch(cityMilitaryID:int,posX:int,posY:int,carryFood:int,carryWood:int,carryOil:int,carrySteel:int,carryMoney:int):void{
			var call:Object = service.dispatch(cityMilitaryID,posX,posY,carryFood,carryWood,carryOil,carrySteel,carryMoney);
			call.addResponder(responder);
		}
		
		/**
		 * 客户端军队到达
		 */
		public function clientMilitaryArrived(depoyQueueID:int):void{
			var call:Object = service.clientMilitaryArrived(depoyQueueID);
			call.addResponder(responder);
		}
		
		/**
		 * 设置留守军队
		 * @param cityMilitaryID
		 */
		public function setDefensiveMilitary(cityMilitaryID:int):void{
			var call:Object = service.setDefensiveMilitary(cityMilitaryID);
			call.addResponder(responder);
		}
		
		/**
		 * 取消留守部队
		 * @param cityMilitaryID
		 */
		public function cancelDefensiveMilitary(cityMilitaryID:int):void{
			var call:Object = service.cancelDefensiveMilitary(cityMilitaryID);
			call.addResponder(responder);
		}
		
	}
}
