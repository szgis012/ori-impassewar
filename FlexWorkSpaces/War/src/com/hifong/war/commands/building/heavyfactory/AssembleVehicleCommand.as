/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.heavyfactory
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.HeavyFactoryDelegate;
	import com.hifong.war.events.building.heavyfactory.AssembleVehicleEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ArmyVO;
	import com.hifong.war.vo.CityArmyVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理组装车辆事件
     *
     */
	public final class AssembleVehicleCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var army:ArmyVO ;
		private var cityArmy:CityArmyVO;
		private var num:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:AssembleVehicleEvent = event as AssembleVehicleEvent;
			this.army = evt.army;
			this.cityArmy = evt.cityArmy;
			this.num = evt.num;
			var delegate:HeavyFactoryDelegate = new HeavyFactoryDelegate( this );
			delegate.assembleVehicle(model.cityInfo.cityID,cityArmy.armyID,num);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			model.cityInfo.recruitNum -= (num * army.population);
			cityArmy.num += num;
			var ca:CityArmyVO = model.armyInfo.nosetArmyMap[cityArmy.armyID] as CityArmyVO;
			//更新兵力map
			if(!ca){
				model.armyInfo.nosetArmyMap[cityArmy.armyID] = cityArmy;
			}
			
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新资源消耗信息
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
			//更新城市的军械信息
			dispatcher.dispatchEvent(new GetCityOrdnanceListEvent());
			
			//todo 更新城市的军队信息
			MsgBox.showMessage("组装车辆成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
