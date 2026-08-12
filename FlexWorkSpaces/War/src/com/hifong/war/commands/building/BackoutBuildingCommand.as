/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.BackoutBuildingEvent;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     * 处理建筑拆除事件
     *
     */
	public final class BackoutBuildingCommand extends SequenceCommand implements ICommand, IResponder
	{
		public override function execute(event:CairngormEvent) : void
		{
			var evt:BackoutBuildingEvent = event as BackoutBuildingEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.backoutBuilding(evt.cityBuildingID);
			this.nextEvent = new RefreshCityBuildingEvent(evt.cityBuildingID);
		}
		
		public function result(data:Object) : void
		{
			this.executeNextCommand();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
