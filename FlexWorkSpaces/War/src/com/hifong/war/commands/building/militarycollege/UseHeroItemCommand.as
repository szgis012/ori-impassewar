/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.events.building.militarycollege.UseHeroItemEvent;
	import com.hifong.war.events.treasure.GetPlayerTreasureListByCategoryEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class UseHeroItemCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:UseHeroItemEvent = event as UseHeroItemEvent;
			var delegate:TreasureDelegate = new TreasureDelegate(this);
			delegate.useTreasure(evt.playerID,evt.treasureID,evt.params);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerTreasureListByCategoryEvent(ModelLocator.getInstance().playerInfo.playerID,6));
			MsgBox.showMessage("使用道具成功。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}