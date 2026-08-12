/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ArmyDelegate;
	import com.hifong.war.events.building.commandcenter.DismissCityWoundedArmyEvent;
	
	import mx.rpc.IResponder;

	public final class DismissCityWoundedArmyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DismissCityWoundedArmyEvent = event as DismissCityWoundedArmyEvent;
			var delegate:ArmyDelegate=new ArmyDelegate(this);
			delegate.dismissCityWoundedArmy(evt.cityWoundedArmyID,evt.num);
		}
		
		public function result(data:Object) : void
		{
			
		}
		
		public function fault(info:Object) : void
		{
		
		}
		
	}
}