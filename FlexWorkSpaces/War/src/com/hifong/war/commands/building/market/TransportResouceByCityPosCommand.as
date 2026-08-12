/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.market
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.building.market.TransportResouceByCityPosEvent;
	import com.hifong.war.events.building.market.TransportResourceEvent;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ResTransportationVO;
	
	import mx.rpc.IResponder;

	public final class TransportResouceByCityPosCommand extends SequenceCommand implements ICommand, IResponder
	{

		private var resTransportation:ResTransportationVO;

		private var cityID:int;

		private var targetPosX:int;
		
		private var targetPosY:int;

		public override function execute(event:CairngormEvent) : void
		{
			var evt:TransportResouceByCityPosEvent = event as TransportResouceByCityPosEvent;
			var delegate:CityDelegate = new CityDelegate(this);
			
			this.resTransportation = evt.resTransportation;
			this.cityID = evt.cityID;
			this.targetPosX = evt.targetPosX;
			this.targetPosY = evt.targetPosY;

			delegate.getCityIDByCityPos(evt.targetPosX,evt.targetPosY);
		}
		
		public function result(data:Object) : void
		{
			if(data.result==null){
				MsgBox.showMessage("坐标 X:" + targetPosX + ",Y:" + targetPosY + " 不存在城市");
			}else{
				this.nextEvent = new TransportResourceEvent(resTransportation,cityID,data.result);
				this.executeNextCommand();
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}