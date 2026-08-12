/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.barracks
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.BarracksDelegate;
	import com.hifong.war.events.building.barracks.ArmSoldierEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityArmyVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理武装士兵事件
     *
     */
	public final class ArmSoldierCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var cityArmy:CityArmyVO;
		private var num:int;
				
		public function execute(event:CairngormEvent) : void
		{
			var evt:ArmSoldierEvent = event as ArmSoldierEvent;
			this.cityArmy = evt.cityArmy;
			this.num = evt.num;
			var delegate:BarracksDelegate = new BarracksDelegate( this );
			delegate.armSoldier(model.cityInfo.cityID,cityArmy.armyID,num);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			model.cityInfo.recruitNum -= num;
			cityArmy.num += num;
			var ca:CityArmyVO = model.armyInfo.nosetArmyMap[cityArmy.armyID] as CityArmyVO;
			//更新兵力map
			if(!ca){
				model.armyInfo.nosetArmyMap[cityArmy.armyID] = cityArmy;
			}
			
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新城市的军械信息
			dispatcher.dispatchEvent(new GetCityOrdnanceListEvent());
			//更新资源消耗信息
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
			
			MsgBox.showMessage("武装士兵成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
