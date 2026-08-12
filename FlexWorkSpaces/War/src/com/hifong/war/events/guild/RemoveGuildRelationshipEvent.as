/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 移除军团关系
	 * @parm guildID
	 * @parm targetGuildName
	 */
	public final class RemoveGuildRelationshipEvent extends CairngormEvent
	{

		public static const REMOVEGUILDRELATIONSHIP_EVENT:String = "com.hifong.war.events.RemoveGuildRelationshipEvent";
		
		public var guildID:int;
		public var targetGuildName:String;
		public function RemoveGuildRelationshipEvent(guildID:int,targetGuildName:String) 
		{
			super( REMOVEGUILDRELATIONSHIP_EVENT );
			this.guildID=guildID;
			this.targetGuildName=targetGuildName;
		}
	}
}
