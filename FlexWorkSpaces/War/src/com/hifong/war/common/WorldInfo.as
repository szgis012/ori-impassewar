package com.hifong.war.common
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.events.world.GetMapListByMapPosXYListEvent;
	import com.hifong.war.events.world.LoadMapDataEvent;
	import com.hifong.war.util.LoadingUtil;
	import com.hifong.war.view.assets.WorldMapAssets;
	import com.hifong.war.vo.MapVO;
	
	import flash.geom.Point;
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 世界地图相关的信息
	 */ 
	 [Bindable]
	public class WorldInfo
	{
		/** 模拟数据库的地图数据 */
		public var mapdata:Array;
		/** 世界地图使用的贴图 */
		public var worldAssets:WorldMapAssets =new WorldMapAssets();
		/** 地图信息 key=WorldUtil.getIndexFromXY的值,value=MapVO*/
		public var gridInfoMap:Object=new Object() ;
		
		/******* 加载的地图数据范围 *******/
		
		/** 最小X坐标值(Map坐标)*/
		public var minMapX:int = 0;
		/** 最小Y坐标值(Map坐标)*/
		public var minMapY:int = 0;
		/** 最大X坐标值(Map坐标)*/
		public var maxMapX:int = 0;
		/** 最大Y坐标值(Map坐标)*/
		public var maxMapY:int = 0 ;
		
		/** 地图起始坐标 */
        public const MIN_RANGE_X:int = 1; 
        public const MIN_RANGE_Y:int = 1;
        /** 地图终点坐标 */
        public const MAX_RANGE_X:int = 400;
        public const MAX_RANGE_Y:int = 400;
		/** 加载地图时X,Y的坐标范围*/
        public const LOADRANGE_X:int =28;//23;//9;
        public const LOADRANGE_Y:int =28;//27;//9;
        
        /** 当前加载地图的中心坐标(Map坐标)*/
        public var mapX:int = 0;
        public var mapY:int = 0;
        
        /** 地图上是否有显示的窗口,为tooltip显示使用*/
        public var hasDisplayedWindow:Boolean = false;
        
        
		public function WorldInfo()
		{
//			loadGridData(0,0,9,9);
			//loadMapData(9,9);
			//加载数据
//			dispatcher.dispatchEvent(new LoadMapDataEvent(0,0,9,9));
//			loadMapData(9,9);
		}
		
		//加载地图数据
		public function loadMapData(mapX:int,mapY:int):void{
			//确保在合法范围内
			if(mapX < MIN_RANGE_X){
				mapX = MIN_RANGE_X;
			}else if( mapX > MAX_RANGE_X){
				mapX = MAX_RANGE_X;
			}
				 
            this.mapX = mapX;
            
            if(mapY < MIN_RANGE_Y){
				mapY = MIN_RANGE_Y;
			}else if( mapY > MAX_RANGE_Y){
				mapY = MAX_RANGE_Y;
			}
			
            this.mapY = mapY;
            
			//计算加载的地图范围
            minMapX = Math.max(this.mapX - LOADRANGE_X, MIN_RANGE_X);
            maxMapX = Math.min(this.mapX + LOADRANGE_X, MAX_RANGE_X);
            minMapY = Math.max(this.mapY - LOADRANGE_Y, MIN_RANGE_Y);
            maxMapY = Math.min(this.mapY + LOADRANGE_Y, MAX_RANGE_Y);
            
            LoadingUtil.showLoadingScreen();
            CairngormEventDispatcher.getInstance().dispatchEvent(new LoadMapDataEvent(minMapX,minMapY,maxMapX,maxMapY));
//            loadData(minMapX,minMapY,maxMapX,maxMapY);
		}
		
			/**
			 * 向服务器请求指定地图点列表数据
			 */
			public function requestMapXY(arr:Array,showLoader:Boolean):void{
				if(arr==null || arr.length==0) return;
				var ac:ArrayCollection=new ArrayCollection();
				for each(var p:Point in arr){
					var map:MapVO=new MapVO();
					map.posX=p.x;
					map.posY=p.y;
					ac.addItem(map);
				}
				if(showLoader)	LoadingUtil.showLoadingScreen();
				CairngormEventDispatcher.getInstance().dispatchEvent(new GetMapListByMapPosXYListEvent(ac));
			}
 }
        
}