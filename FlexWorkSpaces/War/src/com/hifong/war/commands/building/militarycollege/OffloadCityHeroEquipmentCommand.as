/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.OffloadCityHeroEquipmentEvent;
	import com.hifong.war.events.equipment.GetPlayerEquipmentListByCategoryEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class OffloadCityHeroEquipmentCommand implements ICommand, IResponder
	{
		
		private var category:int;
		
		private var currentCategory:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:OffloadCityHeroEquipmentEvent = event as OffloadCityHeroEquipmentEvent;
			category = evt.category;
			currentCategory = evt.currentCategory;
			
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.offloadHeroEquipment(ModelLocator.getInstance().playerInfo.playerID,evt.cityHeroID,evt.category);
		}
		
		public function result(data:Object) : void
		{
			if(category==currentCategory){
				CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerEquipmentListByCategoryEvent(ModelLocator.getInstance().playerInfo.playerID,category));
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}