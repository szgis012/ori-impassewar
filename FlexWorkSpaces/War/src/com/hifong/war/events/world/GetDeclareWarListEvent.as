/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得所有对外宣战列表
     *
     */
	public final class GetDeclareWarListEvent extends CairngormEvent
	{
		public static const GETDECLAREWARLIST_EVENT:String = "com.hifong.war.events.GetDeclareWarListEvent";
		
		public function GetDeclareWarListEvent() 
		{
			super( GETDECLAREWARLIST_EVENT );
		}
	}
}
