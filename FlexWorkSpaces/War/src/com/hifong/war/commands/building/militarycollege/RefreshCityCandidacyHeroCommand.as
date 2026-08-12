/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.constant.TreasureConstant;
	import com.hifong.war.events.building.militarycollege.GetCityCandidacyHeroListEvent;
	import com.hifong.war.events.building.militarycollege.RefreshCityCandidacyHeroEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class RefreshCityCandidacyHeroCommand implements ICommand, IResponder
	{

		private var model:ModelLocator = ModelLocator.getInstance();

		public function execute(event:CairngormEvent) : void
		{
			var evt:RefreshCityCandidacyHeroEvent = event as RefreshCityCandidacyHeroEvent;
			var delegate:TreasureDelegate = new TreasureDelegate(this);
			delegate.useTreasure(model.playerInfo.playerID, TreasureConstant.EMLIST_COMMAND, null);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityCandidacyHeroListEvent(model.cityInfo.cityID));
			MsgBox.showMessage("刷新指挥官列表成功。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}