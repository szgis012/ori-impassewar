/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroExtByCityHeroIDEvent;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityHeroExtVO;
	
	import mx.rpc.IResponder;
	/**
	 * 获得城市英雄扩展信息
	 * @param cityID
	 */
	public final class GetCityHeroExtByCityHeroIDCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityHeroExtByCityHeroIDEvent = event as GetCityHeroExtByCityHeroIDEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.getCityHeroExtByCityHeroID(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var heroExt:CityHeroExtVO=data.result as CityHeroExtVO;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}