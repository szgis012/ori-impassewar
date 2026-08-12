/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.SaveHeroPointEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class SaveHeroPointCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:SaveHeroPointEvent = event as SaveHeroPointEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.addHeroPoint(evt.cityHeroID,evt.commandAdded,evt.defenseAdded,evt.mindAdded,evt.executivepowerAdded);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("保存指挥官属性成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage(info.fault.rootCause.message);
		}
		
	}
}