/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 加载用户需要缓存的数据
	 */ 
	public final class LoadPlayerCacheDataEvent extends CairngormEvent
	{

		public static const LOADPLAYERCACHEDATA_EVENT:String = "com.hifong.war.events.LoadPlayerCacheDataEvent";

		public function LoadPlayerCacheDataEvent() 
		{
			super( LOADPLAYERCACHEDATA_EVENT );
		}
	}
}
