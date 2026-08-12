/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildRelationshipListEvent extends CairngormEvent
	{

		public var guildID:int;

		public static const SHOWGUILDRELATIONSHIPLIST_EVENT:String = "com.hifong.war.events.ShowGuildRelationshipListEvent";

		public function ShowGuildRelationshipListEvent(guildID:int) 
		{
			super( SHOWGUILDRELATIONSHIPLIST_EVENT );
			this.guildID = guildID;
		}
	}
}
