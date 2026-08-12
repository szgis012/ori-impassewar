/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroStarEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 获得城市英雄星级
	 * @param cityHeroID
	 * @return
	 */
	public final class GetCityHeroStarCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityHeroStarEvent= event as GetCityHeroStarEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.getCityHeroStar(evt.cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			var starLevel:int=data.result as int;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}