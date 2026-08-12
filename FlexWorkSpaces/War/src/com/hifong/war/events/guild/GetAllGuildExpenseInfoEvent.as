/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
		/**
		 * 获取工会所有支出明细列表
		 */ 
	public final class GetAllGuildExpenseInfoEvent extends CairngormEvent
	{

		public static const GETALLGUILDEXPENSEINFO_EVENT:String = "com.hifong.war.events.GetAllGuildExpenseInfoEvent";

		public var guildID:int;
		public function GetAllGuildExpenseInfoEvent(guildID:int) 
		{
			super( GETALLGUILDEXPENSEINFO_EVENT );
			this.guildID=guildID;
		}
	}
}
