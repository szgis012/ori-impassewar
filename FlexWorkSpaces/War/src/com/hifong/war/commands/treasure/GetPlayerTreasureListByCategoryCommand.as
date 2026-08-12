/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.events.treasure.GetPlayerTreasureListByCategoryEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;

	public final class GetPlayerTreasureListByCategoryCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetPlayerTreasureListByCategoryEvent = event as GetPlayerTreasureListByCategoryEvent;
			var delegate:TreasureDelegate = new TreasureDelegate(this);
			delegate.getPlayerTreasureListByCategory(evt.playerID, evt.category);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().itemList_hero = data.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}