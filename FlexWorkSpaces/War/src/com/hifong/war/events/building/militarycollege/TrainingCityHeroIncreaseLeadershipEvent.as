/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 训练指挥官
	 */
	public final class TrainingCityHeroIncreaseLeadershipEvent extends CairngormEvent
	{

		public static const TRAININGCITYHEROINCREASELEADERSHIP_EVENT:String = "com.hifong.war.events.TrainingCityHeroIncreaseLeadershipEvent";
		public var cityHeroID:int;
		public function TrainingCityHeroIncreaseLeadershipEvent(cityHeroID:int) 
		{
			super( TRAININGCITYHEROINCREASELEADERSHIP_EVENT );
			this.cityHeroID=cityHeroID;
		}
	}
}
