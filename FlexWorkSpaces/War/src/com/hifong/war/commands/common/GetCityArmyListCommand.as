/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ArmyDelegate;
	import com.hifong.war.events.common.GetCityArmyListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityArmyVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市拥有的军队列表
     *
     */
	public final class GetCityArmyListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityArmyListEvent = event as GetCityArmyListEvent;
			var delegate:ArmyDelegate = new ArmyDelegate( this );
			delegate.getCityArmyList(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.armyInfo.cityArmyList = rs.result as ArrayCollection;
			
			splitNosetArmyMap();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
		//从城市军队中分离出未编制的兵种
		private function splitNosetArmyMap():void{
			var list:ArrayCollection = model.armyInfo.cityArmyList;
			//重置map
			model.armyInfo.nosetArmyMap = {};
			
			if(list && list.length>0){
				var ca:CityArmyVO;
				
				for(var i:int=0;i<list.length; i++){
					ca = list.getItemAt(i) as CityArmyVO;
					
					//未编制状态
					if(ca.state == 0){
						model.armyInfo.nosetArmyMap[ca.armyID] = ca;
					}
				}
			}
			
		}
	}
}
