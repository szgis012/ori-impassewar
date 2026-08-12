package com.hifong.war.util
{
	import flash.external.ExternalInterface;
	
	/**
	 * 系统工具类
	 * 
	 */ 
	public class SystemUtil
	{
		/**
		 * 刷新浏览器
		 */ 
		public static function refreshBrowser():void{
 			 ExternalInterface.call("eval", "location.reload();");
		}

	}
}