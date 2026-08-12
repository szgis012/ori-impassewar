/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
		/**
		 * 获取工会所有收入明细列表
		 */
	public final class GetAllGuildIncomeInfoEvent extends CairngormEvent
	{

		public static const GETALLGUILDINCOMEINFO_EVENT:String = "com.hifong.war.events.GetAllGuildIncomeInfoEvent";
		public var guildID:int;
		public function GetAllGuildIncomeInfoEvent(guildID:int) 
		{
			super( GETALLGUILDINCOMEINFO_EVENT );
			this.guildID=guildID;
		}
	}
}
