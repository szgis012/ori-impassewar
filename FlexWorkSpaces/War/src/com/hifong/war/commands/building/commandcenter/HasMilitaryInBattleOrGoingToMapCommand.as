/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.MilitaryDelegate;
	import com.hifong.war.events.building.commandcenter.AttackEvent;
	import com.hifong.war.events.building.commandcenter.HasMilitaryInBattleOrGoingToMapEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class HasMilitaryInBattleOrGoingToMapCommand implements ICommand, IResponder
	{

		public var cityMilitaryID:int;
		
		public var posX:int;
		
		public var posY:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:HasMilitaryInBattleOrGoingToMapEvent = event as HasMilitaryInBattleOrGoingToMapEvent;
			cityMilitaryID = evt.cityMilitaryID;
			this.posX = evt.posX;
			this.posY = evt.posY;
			
			var delegate:MilitaryDelegate = new MilitaryDelegate(this);
			delegate.hasMilitaryInBattleOrGoingToMap(evt.posX,evt.posY);
		}
		
		public function result(data:Object) : void
		{
			if(data.result==1){
				MsgBox.showConfirm("该目的地已有军队正在战斗，您确定仍然要前往吗？",function ():void{
					CairngormEventDispatcher.getInstance().dispatchEvent(new AttackEvent(cityMilitaryID,posX,posY));
				});
			}else if(data.result==2){
				MsgBox.showConfirm("该目的地已有军队正在前往，您确定仍然要前往吗？",function ():void{
					CairngormEventDispatcher.getInstance().dispatchEvent(new AttackEvent(cityMilitaryID,posX,posY));
				});
			}else{
				CairngormEventDispatcher.getInstance().dispatchEvent(new AttackEvent(cityMilitaryID,posX,posY));
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}