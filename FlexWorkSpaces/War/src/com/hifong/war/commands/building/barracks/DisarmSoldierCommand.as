/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.barracks
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.BarracksDelegate;
	import com.hifong.war.events.building.barracks.DisarmSoldierEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityArmyVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理解除士兵武装事件
     *
     */
	public final class DisarmSoldierCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var cityArmy:CityArmyVO;
		private var num:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:DisarmSoldierEvent = event as DisarmSoldierEvent;
			this.cityArmy = evt.cityArmy;
			this.num = evt.num;
			var delegate:BarracksDelegate = new BarracksDelegate( this );
			delegate.disarmSoldier(model.cityInfo.cityID,cityArmy.armyID,num);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			model.cityInfo.recruitNum += num;
			cityArmy.num -= num
			
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新资源消耗信息
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
			
			MsgBox.showMessage("解除士兵武装成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
