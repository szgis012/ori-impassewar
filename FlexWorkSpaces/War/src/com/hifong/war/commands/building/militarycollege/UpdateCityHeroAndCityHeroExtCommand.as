/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.UpdateCityHeroAndCityHeroExtEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 更新城市英雄以及其扩展信息
	 * @param cityHero
	 * @param cityHeroExt
	 */
	public final class UpdateCityHeroAndCityHeroExtCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:UpdateCityHeroAndCityHeroExtEvent = event as UpdateCityHeroAndCityHeroExtEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.updateCityHeroAndCityHeroExt(evt.cityHero,evt.cityHeroExt);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("更新城市英雄以及其扩展信息");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);	
		}
		
	}
}