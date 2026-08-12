package com.hifong.war.view.world
{
	import com.hifong.war.common.WorldInfo;
	import com.hifong.war.component.BaseDisplayObject;
	import com.hifong.war.model.ModelLocator;
	
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.Sprite;
	import flash.text.TextField;
	
	
	/**
	 * 世界地图的一个网格
	 */ 
	public class WorldGrid extends BaseDisplayObject
	{
//		//网格信息
//		private var _map:MapVO;  
		//
		private var worldInfo:WorldInfo = ModelLocator.getInstance().worldInfo;
		//加成贴图(怪物，城市旗帜等）
		private var img:Bitmap;
		//旗号
		private var flag:Sprite;
		private var txt:TextField;
		//背景贴图
		private var bg:Bitmap;
		
		public var posX:int;
		public var posY:int;
		//contrutor
		public function WorldGrid()
		{
//			gridInfo = map;
			
			bg=new Bitmap();
			this.addChild(bg);
			
			flag=new Sprite(); 
//			flag.width=flag.height=20;
			flag.graphics.beginFill(0x0000ff,.5); 
			flag.graphics.drawRect(0,0,20,20);
			flag.graphics.endFill();
			flag.x=50;
			flag.y=30; 
			this.addChild(flag);
			
			txt=new TextField();
			txt.textColor=0xffffff;
			txt.autoSize="left";
			flag.addChild(txt);
			
			flag.visible=false; 
		}
		
//		public function get gridInfo():MapVO{
//			return _map;
//		}
//		public function set gridInfo(map:MapVO):void{
//			this._map = map;
//
//		}
		public function set source(source:BitmapData):void{
			bg.bitmapData=source;
		}
		/**
		 * 创建贴图
		 */
		 public function addImg(source:BitmapData):void {
		 	if(img==null){
		 	 	img=new Bitmap();
		 	 	this.addChildAt(img,1);
		 	}
		 	img.bitmapData=source;
		 }
		 /**
		 * 移除贴图
		 */
		 public function removeImg():void {
		 	if(img==null)return;
	 		img.bitmapData.dispose();
	 		this.contains(img) ? this.removeChild(img) : null;
	 		img=null;
		 }
		 /**
		 * 显示旗号等级
		 */
		 public function showFlag(value:String):void{
		 	txt.text=value;
		 	flag.visible=true;
		 }
		 /**
		 * 隐藏旗号等级
		 */
		 public function hideFlag():void{
		 	txt.text="";
		 	flag.visible=false;
		 }
	}
}