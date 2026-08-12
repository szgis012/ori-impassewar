/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.OrdnanceDelegate;
	import com.hifong.war.events.common.GetOrdnanceListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.OrdnanceVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     *  处理获得军械信息列表事件
     *
     */
	public final class GetOrdnanceListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator  = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetOrdnanceListEvent = event as GetOrdnanceListEvent;
			var delegate:OrdnanceDelegate = new OrdnanceDelegate( this );
			delegate.getOrdnanceListByCountry(model.playerInfo.country);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.ordnanceInfo.ordnanceList = rs.result as ArrayCollection;
			
			initOrdnanceMap();			
			splitOrdnance();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
		//将军械按类别分开
		private function splitOrdnance():void{
			model.ordnanceInfo.airframeList = new  ArrayCollection();
			model.ordnanceInfo.ammoList = new ArrayCollection();
			model.ordnanceInfo.firearmsList = new ArrayCollection();
			model.ordnanceInfo.truckbodyList = new ArrayCollection();
			
			var list:ArrayCollection = model.ordnanceInfo.ordnanceList;
			
			if(list && list.length>0){
				var ordnance:OrdnanceVO;
				
				for(var i:int=0; i<list.length; i++){
					ordnance = list.getItemAt(i) as OrdnanceVO;
					
					switch(ordnance.type){
						//枪械 
						case 1:
							model.ordnanceInfo.firearmsList.addItem(ordnance);
							break;
						//弹药	
						case 2:
							model.ordnanceInfo.ammoList.addItem(ordnance);
							break;
						//车体
						case 3:
							model.ordnanceInfo.truckbodyList.addItem(ordnance);
							break;
						//机身	
						case 4:	
							model.ordnanceInfo.airframeList.addItem(ordnance);
							break;
					}	
				}
			}
		}
		
		//方便通过军械编号找到对应的城市现有的军械信
		private function initOrdnanceMap():void{
			model.ordnanceInfo.ordnanceMap = {};
			var list:ArrayCollection = model.ordnanceInfo.ordnanceList;
			
			if(list && list.length>0){
				var ordnance:OrdnanceVO;
				
				for(var i:int=0; i<list.length; i++){
					ordnance = list.getItemAt(i) as OrdnanceVO;
					model.ordnanceInfo.ordnanceMap[ordnance.ordnanceID] = ordnance;
				}
			}
		}
	}
}
