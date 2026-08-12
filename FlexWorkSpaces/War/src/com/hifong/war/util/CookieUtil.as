package com.hifong.war.util
{
	import flash.net.SharedObject;
	
	/**
	 * 用于操作cookie的工具类
	 */ 
	public class CookieUtil
	{
		/**
		 * 在cookie里添加一项属性值
		 */ 
		public static function setProperty(name:String,value:*):void{
			try{
				var so:SharedObject = SharedObject.getLocal("war","/");
				so.data[name] = value;
				so.flush();
			}catch(error:*){
				//do nothing
			}
		}
		
		/**
		 * 从cookie中获得指定属性的值
		 */ 
		public static function getProperty(name:String):*{
			try{
				var so:SharedObject = SharedObject.getLocal("war","/");
				return so.data[name];
			}catch(error:*){
				return null;
			}
		}

	}
}