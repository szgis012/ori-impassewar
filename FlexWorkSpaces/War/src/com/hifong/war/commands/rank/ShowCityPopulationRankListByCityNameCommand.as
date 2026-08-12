/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.ShowCityPopulationRankListByCityNameEvent;
	import com.hifong.war.events.rank.ShowCityPopulationRankListByCityRankEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowCityPopulationRankListByCityNameCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowCityPopulationRankListByCityNameEvent = event as ShowCityPopulationRankListByCityNameEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getCityPopulationRankByCityName(evt.cityName);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().currentCityPopulationRank = data.result;
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowCityPopulationRankListByCityRankEvent(data.result as int));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}