/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.building.commandcenter.GetPlayerGuildAppInvListEvent;
	import com.hifong.war.model.ModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	public final class GetPlayerGuildAppInvListCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetPlayerGuildAppInvListEvent = event as GetPlayerGuildAppInvListEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getPlayerAppInvList(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			var arrayCollection:ArrayCollection = data.result as ArrayCollection;
			var array:Array = arrayCollection.toArray();
			for(var i:int=0;i<array.length;i++){
				array[i] = new ObjectProxy(array[i]);
			}
			ModelLocator.getInstance().playerGuildAppInvList = new ArrayCollection(array);
		}
		
		public function fault(info:Object) : void
		{
		
		}
		
	}
}