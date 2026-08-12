/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.colonization
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ColonizationDelegate;
	import com.hifong.war.events.colonization.GetCityColonzationListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;

	public final class GetCityColonzationListCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityColonzationListEvent = event as GetCityColonzationListEvent;
			var delegate:ColonizationDelegate = new ColonizationDelegate(this);
			delegate.getCityColonizationList(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().colonizationList = data.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}