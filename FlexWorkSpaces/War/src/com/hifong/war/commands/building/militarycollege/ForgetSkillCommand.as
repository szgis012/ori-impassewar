/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.ForgetSkillEvent;
	import com.hifong.war.events.building.militarycollege.GetCityHeroEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ForgetSkillCommand implements ICommand, IResponder
	{

		private var cityHeroID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:ForgetSkillEvent = event as ForgetSkillEvent;
			cityHeroID = evt.cityHeroID;
			
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.forgetSkill(evt.cityHeroID,evt.heroSkillID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("指挥官遗忘技能成功");
			//刷新指挥官信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityHeroEvent(cityHeroID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}