package com.hifong.war.common
{
	import com.hifong.war.business.TimeDelegate;
	import com.hifong.war.util.DateFormatUtil;
	
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	
	[Bindable]
	/**
	 * 服务端排程的客户端表示
	 */ 
	public class ClientProcess extends Timer
	{
		//每小时的秒数
		private static const CLIPS_PER_HOUR:int =  60 * 60;
		//每分钟的秒数
		private static const CLIPS_PER_MINUTE:int = 60 ;
		
		//服务端的Process象 
		private var _process:Object;
		//进程剩余秒数
		private var leavingSeconds:int;
		/** 是否为启动后第一秒 */
		private var isFirstSecond:Boolean = true;
		
		/** 剩余时间,默认格式为: 10小时20分33秒 */
		public var leavingTime:String = "";
		/**完成时间,默认格式为: 2008-12-10 00:00:00*/
		public var finishTime:String = "";
		
		public function ClientProcess(pq:Object=null)
		{
			super(10);
			process = pq;
		}

		public function set process(pq:Object):void{
			_process = pq
		}
		
		public function get process():Object{
			return _process;
		}
		
		/**
		 * 启动进程
		 */ 
		public function startProcess():void{
			//如果已经在运行先停止原来的进程
			if(this.running){
				stopProcess();
			}
			
			if(!process){
				throw new Error("无法启动进程！");
			}
			
			startTimer(process.finishTime as Date);
			
		}
		
		/**
		 * 停止进程
		 */ 
		public function stopProcess():void{
			this.stop();
		}
		
		//启动时间计数器
		private function startTimer(finishDate:Date):void{
			//todo 
			//this.leavingSeconds = Math.ceil((finishDate.time - new Date().time)/1000);
			this.leavingSeconds = Math.ceil((finishDate.time - new Date().time)/1000);
			//var timeDelegate:TimeDelegate = new TimeDelegate();
			//timeDelegate.getServerTime();
			this.repeatCount = this.leavingSeconds;
			
			//格式化完成时间
			this.finishTime = DateFormatUtil.formatTime(finishDate);
			
			this.addEventListener(TimerEvent.TIMER,countTime);
//			this.addEventListener(TimerEvent.TIMER_COMPLETE,completeProcess);
			this.start();
		}
		
		//TODO 动态计算剩余时间
		private function countTime(event:TimerEvent):void{
			
			if(isFirstSecond){
				this.delay = 1000;
				isFirstSecond = false;
				leavingSeconds += 1;
			}
			
			leavingSeconds -= 1;
			
			//格式化剩余时间
			this.leavingTime = DateFormatUtil.convertSecondToTime(this.leavingSeconds);
		}
		
		//进程结束时的处理函数
		private function completeProcess(event:TimerEvent):void{
			
		}
		
	}
}