/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.defense
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 完成所有的城防建造过程(需要道具)
     *
     */
	public final class FinishAllBuildDefenseEvent extends CairngormEvent
	{
		public static const FINISHALLBUILDDEFENSE_EVENT:String = "com.hifong.war.events.FinishAllBuildDefenseEvent";
		
		public function FinishAllBuildDefenseEvent() 
		{
			super( FINISHALLBUILDDEFENSE_EVENT );
		}
	}
}
