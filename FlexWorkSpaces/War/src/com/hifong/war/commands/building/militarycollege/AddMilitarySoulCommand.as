/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.AddMilitarySoulEvent;
	import com.hifong.war.events.building.militarycollege.GetCityHeroEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 增加军魄点数
	 * @param playerID
	 * @param cityHeroID
	 * @param treasureID TreasureConstant中671~673
	 */
	public final class AddMilitarySoulCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:AddMilitarySoulEvent = event as AddMilitarySoulEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.addMilitarySoul(evt.playerID,evt.cityHeroID,evt.treasureID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功增加军魂点数");
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityHeroEvent(ModelLocator.getInstance().currentCityHero.cityHeroID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}