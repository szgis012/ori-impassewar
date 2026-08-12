/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.barracks
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.BarracksDelegate;
	import com.hifong.war.constant.ConsumeConstant;
	import com.hifong.war.events.building.barracks.EnlistSoldierEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理招募新兵事件
     *
     */
	public final class EnlistSoldierCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var enlistNum:int ;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:EnlistSoldierEvent = event as EnlistSoldierEvent;
			enlistNum = evt.enlistNum;
			var delegate:BarracksDelegate = new BarracksDelegate( this );
			delegate.enlistSoldier(model.cityInfo.cityID,enlistNum);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.populationFree -= enlistNum;
			model.cityInfo.cityResource.moneyNum -= enlistNum * ConsumeConstant.SOLDIER_COST_MONEY;
			model.cityInfo.cityResource.foodNum -= enlistNum * ConsumeConstant.SOLDIER_COST_FOOD;
			model.cityInfo.recruitNum += enlistNum;
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新资源产量信息
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			//更新资源消耗信息
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
			
			MsgBox.showMessage("招募成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
