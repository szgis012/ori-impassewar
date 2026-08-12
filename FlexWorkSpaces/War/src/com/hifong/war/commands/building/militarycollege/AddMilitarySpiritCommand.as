/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.AddMilitarySpiritEvent;
	import com.hifong.war.events.building.militarycollege.GetCityHeroListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 提升军魂
	 * @param cityHeroID
	 */
	public final class AddMilitarySpiritCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:AddMilitarySpiritEvent = event as AddMilitarySpiritEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.addMilitarySpirit(evt.cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功提升指挥官军魂"); 	
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityHeroListEvent(ModelLocator.getInstance().cityInfo.cityID));	
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}