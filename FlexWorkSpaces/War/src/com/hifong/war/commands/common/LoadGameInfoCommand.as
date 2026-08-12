/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.common.LoadGameInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class LoadGameInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:LoadGameInfoEvent = event as LoadGameInfoEvent;
			var delegate:PlayerDelegate = new PlayerDelegate(this);
			delegate.loadGameInfo(evt.playerID,evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			//服务器时间
			model.serverTime = data.result.serverTime;
			//城市人口
			model.cityInfo.populationFree = data.result.cityPopulation.populationFree;
			model.cityInfo.populationTotal = data.result.cityPopulation.populationTotal;
			model.cityInfo.populationMax = data.result.cityPopulation.populationMax;
			//城市资源
			model.cityInfo.cityResource.woodNum = data.result.cityResourceNum.woodNum;
			model.cityInfo.cityResource.steelNum = data.result.cityResourceNum.steelNum;
			model.cityInfo.cityResource.oilNum = data.result.cityResourceNum.oilNum;
			model.cityInfo.cityResource.foodNum = data.result.cityResourceNum.foodNum;
			model.cityInfo.cityResource.moneyNum = data.result.cityResourceNum.moneyNum;
			//玩家信息
			model.playerInfo.renown = data.result.playerRenown;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}