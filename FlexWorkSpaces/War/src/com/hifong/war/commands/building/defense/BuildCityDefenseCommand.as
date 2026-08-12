/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.defense
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityDefenseDelegate;
	import com.hifong.war.events.building.defense.BuildCityDefenseEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetDefenseProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityDefenseVO;
	import com.hifong.war.vo.CityVO;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理建造城市的防御事件
     *
     */
	public final class BuildCityDefenseCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var cityInfo:CityVO = model.cityInfo;
		//城防类型，CityDefenseTypeConstant中定义
		private var defenseType:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:BuildCityDefenseEvent = event as BuildCityDefenseEvent;
			this.defenseType = evt.defenseType;
			var delegate:CityDefenseDelegate = new CityDefenseDelegate( this );
			delegate.buildCityDefense(model.cityInfo.cityID,evt.defenseType,evt.num);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			var rs:ResultEvent = data as ResultEvent;
			var pp:ProductionQueueVO = rs.result as ProductionQueueVO;
			var cd:CityDefenseVO =  model.cityDefenseInfo.cityDefenseMap[this.defenseType] as CityDefenseVO;
			//更新城市防御编号
			cd.cityDefenseID = pp.targetID;
			
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));	
			//更新城防进程信息
			dispatcher.dispatchEvent(new GetDefenseProcessListEvent());
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
