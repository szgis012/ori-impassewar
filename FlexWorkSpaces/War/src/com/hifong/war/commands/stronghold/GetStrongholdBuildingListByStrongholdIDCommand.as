/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.stronghold
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.StrongholdServiceDelegate;
	import com.hifong.war.events.stronghold.GetStrongholdBuildingListByStrongholdIDEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	
	
    /**
     * 获得要塞已建的建筑信息 
     *
     */
	public final class GetStrongholdBuildingListByStrongholdIDCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetStrongholdBuildingListByStrongholdIDEvent = event as GetStrongholdBuildingListByStrongholdIDEvent;
			var delegate:StrongholdServiceDelegate = new StrongholdServiceDelegate( this );
			delegate.getStrongholdBuildingListByStrongholdID(evt.strongholdID);
		}
		
		public function result(data:Object) : void
		{
			var list:ArrayCollection = data.result as ArrayCollection;
			
			if(list){
				for(var i:int=0; i<list.length; i++){
					model.strongholdBuildingList.addItem(list.getItemAt(i));
				}
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
