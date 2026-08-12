/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.events.building.citycenter.GetEnlistCitizenProcessEvent;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.events.building.commandcenter.GetFreeCityHeroListCommandEvent;
	import com.hifong.war.events.building.militarycollege.GetCityHeroListEvent;
	import com.hifong.war.events.common.GetArmyListEvent;
	import com.hifong.war.events.common.GetCityArmyListEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetDefenseProcessListEvent;
	import com.hifong.war.events.common.GetOrdnanceListEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.events.world.GetDeclareWarListEvent;
	import com.hifong.war.model.ModelLocator;
	
    /**
     *  加载用户需要缓存的数据
     *
     */
	public final class LoadPlayerCacheDataCommand implements ICommand
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
				
			//加载城市英雄列表
			dispatcher.dispatchEvent(new GetCityHeroListEvent(model.cityInfo.cityID));
			//加载城市已编制军队列表
			dispatcher.dispatchEvent(new GetCityMilitaryListEvent(model.cityInfo.cityID));
			//加载城市空闲英雄列表
			dispatcher.dispatchEvent(new GetFreeCityHeroListCommandEvent(model.cityInfo.cityID));
			//加载所有兵种列表
			dispatcher.dispatchEvent(new GetArmyListEvent());
			//加载城市部队列表
			dispatcher.dispatchEvent(new GetCityArmyListEvent());
			//加载所有军械列表
			dispatcher.dispatchEvent(new GetOrdnanceListEvent());
			//加载城市军械列表
			dispatcher.dispatchEvent(new GetCityOrdnanceListEvent());
			//加载军械制造进程列表
			dispatcher.dispatchEvent(new GetOrdnanceProcessListEvent());
			//获得宣战列表
			//dispatcher.dispatchEvent(new GetDeclareWarListEvent());
			//加载招募市民的进程信息
			dispatcher.dispatchEvent(new  GetEnlistCitizenProcessEvent());
			//获得城防建造列表			
			dispatcher.dispatchEvent(new GetDefenseProcessListEvent());
			
		}
		

	}
}
