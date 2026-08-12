/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.HeroRenameEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class HeroRenameCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:HeroRenameEvent = event as HeroRenameEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.heroRename(evt.cityHeroID,evt.newHeroName);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("指挥官改名成功");
		}
		
		public function fault(info:Object) : void
		{
			trace();
		}
		
	}
}