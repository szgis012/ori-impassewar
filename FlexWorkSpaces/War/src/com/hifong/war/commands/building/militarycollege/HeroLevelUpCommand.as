/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.HeroLevelUpEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class HeroLevelUpCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:HeroLevelUpEvent = event as HeroLevelUpEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.heroLevelUp(evt.cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("升级成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage(info.fault.rootCause.message);
		}
		
	}
}