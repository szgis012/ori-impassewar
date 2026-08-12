package com.hifong.war.util
{
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	public class SynchUtil
	{
		private static var waitQueue:Object = [];
		private static var timer:Timer = new Timer(100,0);
		
		public static function synchronized(lock:Object,code:Function):void{
			//如果是第一次执行
			if(!waitQueue[lock]){
				waitQueue[lock] = [];
			}
			
			//加入到等待队列
			waitQueue[lock].push(code);
			
			if(!timer.running){
				timer.addEventListener(TimerEvent.TIMER,onTimer);
				timer.start();
			}
		}
		
		private static function onTimer(event:TimerEvent):void{
			var count:int = 0;
			var lock:Object;
			var code:Function;
			var len:int = 0;
			var i:int;
			var codes:Array;
			
			//所有
			for(lock in waitQueue){
				//
				codes = waitQueue[lock] as Array;
				len = codes.length;
				
				for(i=0; i<len;i++){
					code = codes.shift() as Function;
					code();
					count++;
				}
			}
			
			//如果没有同步的代码执行就把timer停了
			if(count == 0){
				timer.stop();
				timer.removeEventListener(TimerEvent.TIMER,onTimer);
			}
		}
		
	}
}