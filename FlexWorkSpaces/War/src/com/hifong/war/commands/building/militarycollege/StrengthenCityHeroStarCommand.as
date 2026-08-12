/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroEvent;
	import com.hifong.war.events.building.militarycollege.StrengthenCityHeroStarEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.view.building.militarycollege.StrengthenCityHeroStarWindow;
	
	import mx.rpc.IResponder;
	/**
	 * 强化城市英雄星级
	 * @param playerID
	 * @param cityHeroID
	 * @param upgradeLuckTreasureID: (强运符)TreasureConstant中661~662, 0 代表不用道具
	 * @param stimulateBloodTreasureID: (血激符)TreasureConstant中663~664, 0 代表不用道具
	 */
	public final class StrengthenCityHeroStarCommand implements ICommand, IResponder
	{
		private var window:StrengthenCityHeroStarWindow;
		public function execute(event:CairngormEvent) : void
		{
			var evt:StrengthenCityHeroStarEvent = event as StrengthenCityHeroStarEvent;
			window=evt.window;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.strengthenCityHeroStar(evt.playerID,evt.cityHeroID,evt.upgradeLuckTreasureID,evt.stimulateBloodTreasureID);
		}
		
		public function result(data:Object) : void
		{
			var result:Boolean=data.result as Boolean;
			window.backHandler(result); 
			MsgBox.showMessage(result?"强化成功。" : "强化失败。");
			//更新
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityHeroEvent(ModelLocator.getInstance().currentCityHero.cityHeroID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}