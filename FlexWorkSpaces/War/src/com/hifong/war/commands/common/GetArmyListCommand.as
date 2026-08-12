/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ArmyDelegate;
	import com.hifong.war.constant.ArmyTypeConstant;
	import com.hifong.war.events.common.GetArmyListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ArmyVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得兵种信息列表事件
     *
     */
	public final class GetArmyListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetArmyListEvent = event as GetArmyListEvent;
			var delegate:ArmyDelegate = new ArmyDelegate( this );
			delegate.getArmyList(model.playerInfo.country);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.armyInfo.armyList = rs.result as ArrayCollection; 
			
			initArmyMap();
			splitResult();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
		//初始化兵种map信息
		private function initArmyMap():void{
			var armyMap:Object = {};
			var army:ArmyVO;
			
			if(model.armyInfo.armyList && model.armyInfo.armyList.length>0){
				for each(army in model.armyInfo.armyList){
					armyMap[army.armyID] = army;
				}
			}
		
			this.model.armyInfo.armyMap = armyMap;	
		}
		
		//将军队列表按照类型分类
		private function splitResult():void{
			var list:ArrayCollection = model.armyInfo.armyList;
			//初始化列表
			model.armyInfo.soldierList = new ArrayCollection();
			model.armyInfo.vehicleList = new ArrayCollection();
			model.armyInfo.planeList = new ArrayCollection();
			
			if(list && list.length>0){
				var army:ArmyVO ;
				
				for(var i:int=0; i<list.length; i++){
					army = list.getItemAt(i) as ArmyVO;
					//将不同的兵种放到相应的列表中
					switch(army.type){
						//士兵  
						case ArmyTypeConstant.TYPE_SOLDIER:
							model.armyInfo.soldierList.addItem(army);							
							break;
						//车辆	
						case ArmyTypeConstant.TYPE_VEHICLE:
							model.armyInfo.vehicleList.addItem(army);							
							break;
						//飞机	
						case ArmyTypeConstant.TYPE_PLANE:
							model.armyInfo.planeList.addItem(army);							
							break;		
					}
				}
			}
		}
	}
}
