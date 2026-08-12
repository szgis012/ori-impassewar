/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 升级军团科技
	 * @param guildID
	 * @param technologyID
	 */
	public final class UpgradeTechnologyEvent extends CairngormEvent
	{

		public static const UPGRADETECHNOLOGY_EVENT:String = "com.hifong.war.events.UpgradeTechnologyEvent";
		
		public var guildID:int;
		public var technologyID:int;
		public function UpgradeTechnologyEvent(guildID:int,technologyID:int) 
		{
			super( UPGRADETECHNOLOGY_EVENT );
			this.guildID=guildID;
			this.technologyID=technologyID;
		}
	}
}
