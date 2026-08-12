package com.hifong.war.util
{
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	public class ConfigUtil
	{
		
		private static var httpService:HTTPService;
		public static var haveInited:Boolean = false;

		public static function doInit():void{
			httpService = new HTTPService();
			httpService.url = "config/config.xml";
			httpService.addEventListener(ResultEvent.RESULT,configUtilInited);
			httpService.addEventListener(FaultEvent.FAULT,configUtilFault);
			httpService.send();
		}
		
		private static function configUtilInited(event:ResultEvent):void{
			haveInited = true;
		}
		
		private static function configUtilFault(event:FaultEvent):void{
			Alert.show(event.toString());
		}

		public static function getProperty(key:String):String{
			return httpService.lastResult[key];
		}

	}
}