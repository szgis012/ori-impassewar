package com.hifong.war.view.building
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.events.building.BuildingStateConstant;
	import com.hifong.war.events.building.ClientProcessFinishedEvent;
	import com.hifong.war.util.DateFormatUtil;
	
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	
	
	/**
	 * 城市建筑 
	 * 
	 */ 
	[Bindable]
	public class CityBuilding
	{
		//建筑排程类型
		private var processType:int;
		//对应服务端的CityBuilding对象
		private var _cityBuilding:Object;
		//对应的建筑对象
		private var _building:Building;
		//进程时间计数器
		private var timer:Timer;
		
		/** 剩余多少秒*/
		public var leavingSeconds:Number;
		/** 剩余的时间,长字符串表示*/
		public var leavingTime:String;
		/** 剩余的时间,短字符串表示*/
		public var leavingShortTime:String;
		/** 完成时间，字符串表示*/
		public var finishTime:String;
	
		/** 建筑状态*/
		private var _state:int ;
		
		/** 显示该citybuilding的BuildingGrid对象*/
		public var buildingGrid:BuildingGrid;
		
		public function set state(st:int):void{
			this._state = st;
		}
		
		public function get state():int{
			return this._state ;
		}
		public function CityBuilding(cb:Object){
			cityBuilding = cb;
		}
		
		public function set cityBuilding(cb:Object):void{
			if(this.timer)
				this.timer.stop();
				
			this._cityBuilding = cb;
			this._building = new Building(cityBuilding.building);	
			//如果处在升级或者拆除状态，就启动计时器
			this.state = cb.state;
			if(this.state != BuildingStateConstant.STATE_NORMAL){
				startTimer(cb.processQueue.finishTime);
			} 
		}
		
		/** 建筑是否在升级或者拆除中*/
		public function get inProgress():Boolean{
			return (this.state != BuildingStateConstant.STATE_NORMAL);
		}
		
		
		/** 建筑名称*/
		public function get name():String{
			return this.building.name;
		}
		
		/**
		 * 升级或拆除任务信息 
		 */
		public function get task():String{
			var info:String = "从" + this.level + "级";
			if(state==BuildingStateConstant.STATE_UPDATING){
				info += "升到"+(this.level+1)+"级";
			}else{
				info += "拆到"+(this.level-1)+"级";
			}
			
			return info;
		}
		
		//getter processQueueID
		public function get processQueueID():int{
			if( cityBuilding.processQueue)
				return cityBuilding.processQueue.processQueueID;
			else
				return -1;				
		}
		
		public function get cityBuilding():Object{
			return this._cityBuilding;
		}
		
		public function get building():Building{
			return this._building;
		}
		
		public function set building(b:Building):void{
			this._building = b;
		}		
		
		//getter cityBuildingID
		public function get cityBuildingID():int{
			return cityBuilding.cityBuildingID;
		}
		
		//getter 建筑id
		public function get buildingID():int{
			return cityBuilding.buildingID;
		}
		
		//getter 建筑位置
		public function get position():int{
			return cityBuilding.position;
		}
		
		//getter 建筑等级
		public function get level():int{
			return cityBuilding.level;
		}
		
		//getter 建筑图片
		public function get image():String{
			if(building)
				return building.image;
				
			return null;	
		}
		
		//getter 建筑描述
		public function get description():String{
			return this.building.description;
		}
		
        
        //getter cityID
        public function get cityID():int{
        	return cityBuilding.cityID;
        }

		//启动时间计数器
		private function startTimer(finishTime:Date):void{
			this.timer = new Timer(1000);
			this.leavingSeconds = Math.ceil((finishTime.time - new Date().time)/1000);
			this.finishTime = DateFormatUtil.formatTime(finishTime);
			this.timer.addEventListener(TimerEvent.TIMER,countTime);
			this.timer.start();
		}
		
		//TODO 动态计算剩余时间
		private function countTime(event:TimerEvent):void{
			leavingSeconds-= 1;
			
			//如果时间到了，给服务器发送进程完成的信息
			if(this.leavingSeconds<0){
				this.timer.stop();
				CairngormEventDispatcher.getInstance().dispatchEvent(new ClientProcessFinishedEvent(this));
			}else{
				refreshLeavingTime();
				refreshLeavingShortTime();
			}
		}
		
		//刷新剩余时间
		private function refreshLeavingTime():void{
			var d:Date = new Date();
			d.time += (leavingSeconds * 1000);
			var str:String = (state==BuildingStateConstant.STATE_UPDATING)?"正在升级  ":"正在拆除  ";
			str+= "剩余：" + DateFormatUtil.convertSecondToTime(this.leavingSeconds);
			str+= " 结束：" + this.finishTime;
			
			this.leavingTime = str ;
		}
		
		private function refreshLeavingShortTime():void{
			this.leavingShortTime = DateFormatUtil.convertSecondToTime(this.leavingSeconds);
		}
		
	}
	
}