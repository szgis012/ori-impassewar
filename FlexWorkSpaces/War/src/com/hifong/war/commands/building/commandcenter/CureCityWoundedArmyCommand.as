/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.commandcenter
{
	
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ArmyDelegate;
	import com.hifong.war.events.building.commandcenter.CureCityWoundedArmyEvent;
	
	import mx.rpc.IResponder;

	/**
	 * 治愈伤兵 
	 * @author Powerleader
	 */
	public final class CureCityWoundedArmyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:CureCityWoundedArmyEvent = event as CureCityWoundedArmyEvent;
			var delegate:ArmyDelegate=new ArmyDelegate(this);
			delegate.cureCityWoundedArmy(evt.cityWoundedArmyID,evt.num);
		}
		
		/**
		 * 
		 * @param data
		 */
		public function result(data:Object) : void
		{
			
		}
		
		/**
		 * 
		 * @param info
		 */
		public function fault(info:Object) : void
		{
		
		}
		
	}
}