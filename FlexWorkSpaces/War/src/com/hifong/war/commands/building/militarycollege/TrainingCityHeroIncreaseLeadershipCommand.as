/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.TrainingCityHeroIncreaseLeadershipEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 训练指挥官
	 */
	public final class TrainingCityHeroIncreaseLeadershipCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:TrainingCityHeroIncreaseLeadershipEvent = event as TrainingCityHeroIncreaseLeadershipEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.trainingCityHeroIncreaseLeadership(evt.cityHeroID);
		}
		
		public function result(data:Object) : void
		{
						
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}