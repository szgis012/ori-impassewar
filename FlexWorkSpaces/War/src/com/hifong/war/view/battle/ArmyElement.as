package com.hifong.war.view.battle
{
	import com.greensock.TweenLite;
	import com.greensock.easing.Linear;
	import com.hifong.war.view.BattleConfig;
	
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	import flash.events.Event;
	
	import mx.containers.Canvas;
	import mx.controls.Label;
	import mx.controls.SWFLoader;
	
	/**
	 *  军队组成元素
	 * @author Powerleader
	 */
	public class ArmyElement extends Canvas
	{
		private var swf:SWFLoader;
		/** 方向   0正面  1向右  2背面  3向左 */
		private var direction:int=1;
		/** 当前动作 0走 1站 2开枪 3死亡*/
		private var motion:int=1;
		/** 动作列表*/
		private var action:Array=BattleConfig.PLAYER_ACTION;
		/** 路径*/
		private var pathArr:Array;
		/** 速度*/
		private var speed:Number=1	;
		
		private var _url:String	;
		
		/** 玩家状态 :0走 1站 2开火 */
		private var _state:int=1	;
		/** 是否可开火*/
		private var canFight:Boolean=true;
		private var fightDirection:int;
		private var haveFight:Boolean=false;
		
		private var player:MovieClip;
		
		private var gridSize:int=50;
		
		public var txt:Label;
		//
		private var container:Canvas;
		
		public static const WALK_ARRIVE:String="walkArrive";
		public static const FIGHT_OVER:String="fightOver";
		public function ArmyElement(container:Canvas,url:String="",size:int=50,x:int=0,y:int=0,direction:int=1,motion:int=1)
		{
			this.width=this.height=size;
			this.graphics.beginFill(0,.5);
			this.graphics.drawRect(0,0,size,size);
			this.graphics.endFill();
			
			super();
			container.addChild(this);
			this.container=container;
			
			this.posX=x;
			this.posY=y;
			this.direction=direction;
			this.motion=motion;
			
			this.gridSize=size;
			swf=new SWFLoader();
			this.addChild(swf);
			swf.addEventListener(Event.COMPLETE,onLoadComplete);
			this.url=url;
			
			txt=new Label();
			txt.x = 0;
			txt.y = 35;
			txt.width = 50;
			txt.height = 15;
			txt.setStyle("color","#FFFF00");
			txt.setStyle("fontFamily","Arial");
			txt.setStyle("fontSize",10);
			txt.setStyle("textAlign","right");
			this.addChild(txt);
			
			
		}
		private function onLoadComplete(e:Event):void{
			player=swf.content as MovieClip;
			player.addEventListener(FIGHT_OVER,onFightOver);
//			player.x=player.width/2;
			player.y=-player.height/2;
			
			playerAction();
		}
		/**
		 * 玩家行动控制
		 */
		private function playerAction( ):void{
			player.gotoAndPlay(action[direction][motion]);
		}
		public function get url():String
		{
			return _url;
		}
		public function set url(value:String):void
		{
			if(value=="" || value==null) return;
			_url = value;
			swf.load(_url);
		}
		/**
		 * 行走
		 */
		public function walk(arr:Array):void{
			this.pathArr=arr;
			if(this.pathArr!=null && this.pathArr.length>0){
				_state=0;
				var path:Array=this.pathArr.shift();
				if(path!=null && path.length>0){
					var atan : Number = Math.atan2(path[1] - posY,path[0] - posX);
					var angle:int= Math.round(atan * 180 / Math.PI / 45);
//					trace("angle:"+angle);
					switch(angle){
						case 0:
							direction=1;
							break;
						case -1:
						case -2:
							direction=2;
							break;
						case -3:
						case 3:
						case 4:
						case -4:
							direction=3;
							break;
						case 1:
						case 2:
							direction=0;
							break;
					}
					motion=0;
					playerAction();
					var t:Number=Math.sqrt(Math.pow(path[0]-posX,2)+Math.pow(path[1]-posY,2))/speed;
					var vx:Number=path[0]*gridSize;
					var vy:Number=path[1]*gridSize;
					
					
					TweenLite.to(this,t, {x:vx, y:vy, ease:Linear.easeNone, onComplete:onFinish});
				}
			}else{
				//到达目的地
				_state=1;
				motion=1;
				playerAction();
				if(haveFight){
					fight(fightDirection);
					haveFight=false;
				}
				//注册到达事件	
				this.dispatchEvent(new Event(WALK_ARRIVE));			
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/**
		 * 设置开火
		 */
		 public function setFightDirection(dir:int):void{ 
		 	haveFight=true;
		 	fightDirection=dir;
		 }
		/**
		 * 直接开火
		 */
		public  function fight(dir:int=100):void{
			if(_state!=1 || !canFight) return;
			if(dir!=100) this.direction=dir;
			motion=2;
			playerAction();
//			canFight=false;
		}
		/**
		 * 设置开火权
		 */
		public function setFight():void{
			canFight=true;
		}
		/**
		 * 开火完毕
		 */
		private function onFightOver(e:Event):void{
			//to do
			dispatchEvent(new Event(FIGHT_OVER));
			trace("fightOver");
		}
		/**
		 * 每走完一步时回调事件
		 */
		private function onFinish() : void {
			walk(pathArr);
		}
		
		/**
		 * 按X取格子坐标
		 * @return 
		 */
		public function get posX():int{
			return Math.round((this.x)/gridSize);
		}
		/**
		 * 按格子设置X坐标
		 * @param value
		 */
		public function set posX(value:int):void{
			this.x=value*gridSize;
		}
		/**
		 * 按Y取格子坐标
		 * @return 
		 */
		public function get posY():int{
			return Math.round((this.y)/gridSize);
		}
		/**
		 * 按格子设置Y坐标
		 * @param value
		 */
		public function set posY(value:int):void{
			this.y=value*gridSize;
		}
		/**
		 *  是否在走
		 * @return 
		 */
		public function get state():int{
			return _state;
		}
		/**
		 * 重写基类的SET Y属性，以处理前后关系
		 */
		override public function set  y(value : Number) : void { 
			super.y = value;
			
			if(this.container == null) return;
			var at : uint = this.container.getChildIndex(this);
			if(at < this.container.numChildren - 1) {
				var fore :DisplayObject= this.container.getChildAt(at + 1) ;
				if(this.y > fore.y ) {
					this.container.setChildIndex(this,this.container.getChildIndex(fore));
//					this.parent.swapChildren(fore, this); 
				}
			}
			if(at > 0) {
				var back :DisplayObject= this.container.getChildAt(at - 1) ;
				if(this.y < back.y) {
					this.container.setChildIndex(this,this.container.getChildIndex(back));
//					this.parent.swapChildren(this, back); 
				}
			}	
			
	}
	}
}