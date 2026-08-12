/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.ChangeCityHeroEquipmentEvent;
	import com.hifong.war.events.equipment.GetPlayerEquipmentListByCategoryEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ChangeCityHeroEquipmentCommand implements ICommand, IResponder
	{

		private var category:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:ChangeCityHeroEquipmentEvent = event as ChangeCityHeroEquipmentEvent;
			category = evt.equipmentCategory;
			
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.changeHeroEquipment(evt.cityHeroID,evt.playerEquipmentID);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerEquipmentListByCategoryEvent(ModelLocator.getInstance().playerInfo.playerID,category));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}