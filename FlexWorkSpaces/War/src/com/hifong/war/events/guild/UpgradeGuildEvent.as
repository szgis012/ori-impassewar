/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 升级军团
	 * @param guildID 	军团ID
	 */
	public final class UpgradeGuildEvent extends CairngormEvent
	{

		public static const UPGRADEGUILD_EVENT:String = "com.hifong.war.events.UpgradeGuildEvent";
		
		public var guildID:int;
		public function UpgradeGuildEvent(guildID:int) 
		{
			super( UPGRADEGUILD_EVENT );
			this.guildID=guildID;
		}
	}
}
