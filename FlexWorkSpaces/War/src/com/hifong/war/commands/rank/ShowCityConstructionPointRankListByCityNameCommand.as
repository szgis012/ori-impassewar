/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.ShowCityConstructionPointRankListByCityNameEvent;
	import com.hifong.war.events.rank.ShowCityConstructionPointRankListByCityRankEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowCityConstructionPointRankListByCityNameCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowCityConstructionPointRankListByCityNameEvent = event as ShowCityConstructionPointRankListByCityNameEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getCityConstructionPointRankByCityName(evt.cityName);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().currentCityConstructionPointRank = data.result;
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowCityConstructionPointRankListByCityRankEvent(data.result as int));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}