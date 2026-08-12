/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.hifong.war.vo.CityHeroExtVO;
	import com.hifong.war.vo.CityHeroVO;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
	
	
    /**
     * 英雄服务代理
     *
     */
	public final class HeroDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function HeroDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("heroService");
		}
		
		/**
		 * 获得城市候选英雄列表
		 */
		public function getCityCandidacyHeroList(cityID:int):void{
			var call:Object = service.getCityCandidacyHeroList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得空闲城市英雄列表
		 */
		public function getFreeCityHeroList(cityID:int):void{
			var call:Object = service.getFreeCityHeroList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市英雄列表
		 */
		public function getCityHeroList(cityID:int):void{
			var call:Object = service.getCityHeroList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 招募英雄
		 */
		public function recruitHero(cityCandidacyHeroID:int):void{
			var call:Object = service.recruitHero(cityCandidacyHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 解雇英雄
		 */
		public function dismissHero(cityHeroID:int):void{
			var call:Object = service.dismissHero(cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市英雄编号获得城市英雄信息
		 */
		public function getCityHeroInfoByCityHeroID(cityHeroID:int):void{
			var call:Object = service.getCityHeroInfoByCityHeroID(cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 英雄改名
		 */
		public function heroRename(cityHeroID:int,name:String):void{
			var call:Object = service.heroRename(cityHeroID,name);
			call.addResponder(responder);
		}
		
		/**
		 * 英雄升级
		 */
		public function heroLevelUp(cityHeroID:int):void{
			var call:Object = service.heroLevelUp(cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 更换英雄装备
		 */
		public function changeHeroEquipment(cityHeroID:int,playerEquipmentID:int):void{
			var call:Object = service.changeHeroEquipment(cityHeroID,playerEquipmentID);
			call.addResponder(responder);
		}
		
		/**
		 * 卸下英雄装备
		 */
		public function offloadHeroEquipment(playerID:int,cityHeroID:int,category:int):void{
			var call:Object = service.offloadHeroEquipment(playerID,cityHeroID,category);
			call.addResponder(responder);
		}
		
		/**
		 * 增加城市英雄忠诚
		 */
		public function addCityHeroLoyalty(cityHeroID:int, addLoyalty:int):void{
			var call:Object = service.addCityHeroLoyalty(cityHeroID, addLoyalty);
			call.addResponder(responder);
		}
		
		/**
		 * 英雄加点
		 */
		public function addHeroPoint(cityHeroID:int,commandAdded:int,defenseAdded:int,mindAdded:int,executivepowerAdded:int):void{
			var call:Object = service.addHeroPoint(cityHeroID,commandAdded,defenseAdded,mindAdded,executivepowerAdded);
			call.addResponder(responder);
		}
		
		/**
		 * 任命城市执政官
		 */
		public function setCityOfficer(cityHeroID:int):void{
			var call:Object = service.setCityOfficer(cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 取消设置城市执政官
		 */
		public function cancelCityOfficer(cityHeroID:int):void{
			var call:Object = service.cancelCityOfficer(cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据编号获得城市英雄
		 */
		public function getCityHero(cityHeroID:int):void{
			var call:Object = service.getCityHero(cityHeroID);
			call.addResponder(responder);
		}
		
		/**
		 * 学习技能
		 */
		public function studySkill(cityHeroID:int,skillID:int):void{
			var call:Object = service.studySkill(cityHeroID,skillID);
			call.addResponder(responder);
		}
		
		/**
		 * 遗忘技能
		 */
		public function forgetSkill(cityHeroID:int,heroSkillID:int):void{
			var call:Object = service.forgetSkill(cityHeroID,heroSkillID);
			call.addResponder(responder);
		}
		
		/**
		 * 升级技能
		 */
		public function levelUpSkill(cityHeroID:int,heroSkillID:int):void{
			var call:Object = service.levelUpSkill(cityHeroID,heroSkillID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得等级为1的技能列表
		 */
		public function getLevel1SkillList():void{
			var call:Object = service.getLevel1SkillList();
			call.addResponder(responder);
		}
		
		/**
		 * 重置英雄点数
		 */
		public function resetHeroPoint(playerID:int, cityHeroID:int, command:int, defense:int, mind:int, executivepower:int):void{
			var call:Object = service.resetHeroPoint(playerID, cityHeroID, command, defense, mind, executivepower);
			call.addResponder(responder);
		}
		/* new  add */
		/**
		 * 训练指挥官
		 */
		 public function trainingCityHeroIncreaseLeadership(cityHeroID:int):void{
		 	var call:AsyncToken=service.trainingCityHeroIncreaseLeadership(cityHeroID);
		 	call.addResponder(responder);
		 }
	/**
	 * 强化城市英雄星级
	 * @param playerID
	 * @param cityHeroID
	 * @param upgradeLuckTreasureID: (强运符)TreasureConstant中661~662, 0 代表不用道具
	 * @param stimulateBloodTreasureID: (血激符)TreasureConstant中663~664, 0 代表不用道具
	 */
	 public function 	strengthenCityHeroStar(playerID:int,cityHeroID:int,upgradeLuckTreasureID:int,stimulateBloodTreasureID:int):void{
	 	var call:AsyncToken=service.strengthenCityHeroStar(playerID,cityHeroID,upgradeLuckTreasureID,stimulateBloodTreasureID);
	 	call.addResponder(responder);
	 }
	 /**
	 * 获得城市英雄扩展信息
	 * @param cityID
	 */
	 public function getCityHeroExtByCityHeroID(cityHeroID:int):void{
	 	var call:AsyncToken=service.getCityHeroExtByCityHeroID(cityHeroID);
	 	call.addResponder(responder);
	 }
	 /**
	 * 获得城市英雄星级
	 * @param cityHeroID
	 * @return
	 */
	 public function getCityHeroStar(cityHeroID:int):void{
	 	var call:AsyncToken=service.getCityHeroStar(cityHeroID);
	 	call.addResponder(responder);
	 }
	 /**
	 * 更新城市英雄以及其扩展信息
	 * @param cityHero
	 * @param cityHeroExt
	 */
	 public function updateCityHeroAndCityHeroExt(cityHero:CityHeroVO,cityHeroExt:CityHeroExtVO):void{
	 	var call:AsyncToken=service.updateCityHeroAndCityHeroExt(cityHero,cityHeroExt); 
	 	call.addResponder(responder);
	 }
	 /**
	 * 增加军魄点数
	 * @param playerID
	 * @param cityHeroID
	 * @param treasureID TreasureConstant中671~673
	 */
	 public function addMilitarySoul(playerID:int,cityHeroID:int,treasureID:int):void{
	 	var call:AsyncToken=service.addMilitarySoul(playerID,cityHeroID,treasureID); 
	 	call.addResponder(responder);
	 }
	 /**
	 * 提升军魂
	 * @param cityHeroID
	 */
	 public function addMilitarySpirit(cityHeroID:int):void{
	 	var call:AsyncToken=service.addMilitarySpirit(cityHeroID);
	 	call.addResponder(responder);
	 }
	/**
	 * 根据城市英雄编号更改统御
	 * @param cityHeroID
	 * @param rein
	 */
	 public function updateReinByCityHeroID(cityHeroID:int,rein:int):void{
	 	var call:AsyncToken=service.updateReinByCityHeroID(cityHeroID,rein);
	 	call.addResponder(responder);
	 }
	}
}
