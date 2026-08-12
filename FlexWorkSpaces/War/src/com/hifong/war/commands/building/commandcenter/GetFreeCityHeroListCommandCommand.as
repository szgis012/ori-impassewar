/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.commandcenter.GetFreeCityHeroListCommandEvent;
	import com.hifong.war.model.ModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	public final class GetFreeCityHeroListCommandCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetFreeCityHeroListCommandEvent = event as GetFreeCityHeroListCommandEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.getFreeCityHeroList(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var arrayCollection:ArrayCollection = data.result as ArrayCollection;
			var array:Array = arrayCollection.toArray();
			for(var i:int=0;i<array.length;i++){
				array[i] = new ObjectProxy(array[i]);
			}
			ModelLocator.getInstance().freeCityHeroList = new ArrayCollection(array);
		}
		
		public function fault(info:Object) : void
		{
		
		}
		
	}
}