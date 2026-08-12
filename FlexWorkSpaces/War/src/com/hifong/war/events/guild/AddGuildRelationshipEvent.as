/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class AddGuildRelationshipEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var targetGuildName:String;
		
		public var targetType:int;

		public static const ADDGUILDRELATIONSHIP_EVENT:String = "com.hifong.war.events.AddGuildRelationshipEvent";

		public function AddGuildRelationshipEvent(guildID:int,targetGuildName:String,targetType:int) 
		{
			super( ADDGUILDRELATIONSHIP_EVENT );
			this.guildID = guildID;
			this.targetGuildName = targetGuildName;
			this.targetType = targetType;
		}
	}
}
