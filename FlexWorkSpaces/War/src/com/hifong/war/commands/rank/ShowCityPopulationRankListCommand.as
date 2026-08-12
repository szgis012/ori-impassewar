/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.ShowCityPopulationRankListByCityRankEvent;
	import com.hifong.war.events.rank.ShowCityPopulationRankListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowCityPopulationRankListCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowCityPopulationRankListEvent = event as ShowCityPopulationRankListEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getCityConstructionPointRankByCityID(evt.cityID);
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