/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.stronghold
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.StrongholdServiceDelegate;
	import com.hifong.war.events.stronghold.GetStrongoldAvailableBuildingListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
	
    /**
     * 获得要塞可建筑的建筑列表
     *
     */
	public final class GetStrongoldAvailableBuildingListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetStrongoldAvailableBuildingListEvent = event as GetStrongoldAvailableBuildingListEvent;
			var delegate:StrongholdServiceDelegate = new StrongholdServiceDelegate( this );
			delegate.getStrongoldAvailableBuildingList(evt.strongholdID);
		}
		
		public function result(data:Object) : void
		{
			model.strongholdBuildableBuildingList = data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
