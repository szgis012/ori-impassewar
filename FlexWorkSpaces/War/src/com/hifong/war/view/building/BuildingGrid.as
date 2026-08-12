package com.hifong.war.view.building
{
	import com.hifong.war.events.building.BuildingStateConstant;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.CityDefenseUtil;
	import com.hifong.war.view.assets.WarAssets;
	import com.hifong.war.vo.CityBuildingVO;
	
	import flash.filters.ColorMatrixFilter;
	
	import mx.binding.utils.ChangeWatcher;
	import mx.controls.Image;
	import mx.events.PropertyChangeEvent;

	[Bindable]
	/**
	 * 城市建筑，地图上的一个建筑单元
	 * 
	 */ 
	public class BuildingGrid extends Image
	{
		//todo 建筑被移除时发送的事件
		public static const EVENT_CITYBUILDING_REMOVED:String = "CITYBUILDING_REMOVED";
		
		//灰色滤镜
		private static const FILTER_GREY:ColorMatrixFilter = new ColorMatrixFilter([0.3086, 0.6094, 0.082, 0, 0, 0.3086, 0.6094, 0.082, 0, 0, 0.3086, 0.6094, 0.082, 0, 0, 0.3086, 0.6094, 0.082, 1, 0]);
		//和当前网格绑定的CityBuilding 
		private var _cityBuilding:CityBuildingVO;
		//网格在地图上的位置
		private var _position:int ;
		//状态变化监视
		private var stateWatcher:ChangeWatcher;
		
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function get cityBuilding():CityBuildingVO{
			return this._cityBuilding;
		}
		
		public function BuildingGrid(){ 
			super();
//			ColorFilterTween.addRollEffect(this); 
		}
		public function set cityBuilding(cb:CityBuildingVO):void{
			//如果已经有绑定的citybuilding，先取消该对象上的watcher
			if(_cityBuilding){
				stateWatcher.unwatch();
			}
			
			this._cityBuilding = cb;
			
			if(cb){
				initBuildingGrid();
			}
		}
		
		public function get position():int{
			return this._position;
		}
		
		public function set position(pos:int):void{
			this._position = pos;
		}
		
		/** 检查该Grid是否为城市空地 */
		public function isBlankGrid():Boolean{
			return cityBuilding == null;
		}
		
		/** 
		 * 重置为城市空地
		 * 
		 * 此方法只在拆除为空地或者取消新建的建筑时才会使用的方法
		 */
		public function resetGrid():void{
			this._cityBuilding = null;
			this.filters = null;
			this.source = new WarAssets.blankAsset();
//			//发布事件
			if(this.willTrigger(EVENT_CITYBUILDING_REMOVED))
				this.dispatchEvent(new Event(EVENT_CITYBUILDING_REMOVED));
		}
		
		/** 返回表示城市空地的BuildingGrid */
		public static function getBlankBuildingGrid(type:int=0):BuildingGrid{
			var bg:BuildingGrid = new BuildingGrid();
			bg.source = new WarAssets.blankAsset();
			return bg;
		}
		
		//初始化城市建筑数据
		private function initBuildingGrid():void{
			this.source = model.countryRelatedAssets.getBuildingImageSource(cityBuilding.buildingID);

			changeViewState(cityBuilding.state);
			
			//初始化state改变监视
			stateWatcher = ChangeWatcher.watch(cityBuilding, "state", onStateChanged);
		}
		
		//根据citybuilding状态的不同改变Grid显示的状态
		private function changeViewState(state:int):void{
			switch(state){
				case BuildingStateConstant.STATE_NORMAL:
					this.filters = null;
					break;
				case BuildingStateConstant.STATE_UPDATING:
				case BuildingStateConstant.STATE_DESTROYING:
					this.filters = [FILTER_GREY];
					break;
			}
		}
		
		//建筑状态改变时的处理方法
		private function onStateChanged(event:PropertyChangeEvent):void{
			changeViewState(int(event.newValue));
		}
		
		//提示
		public function get tipInfo():String{
			if(isBlankGrid()){
				return "空地"; 
			}else{
				//是否为城防
//				if(CityDefenseUtil.isCityDefense(cityBuilding.buildingID)){
//					return cityBuilding.building.name + "(" + CityDefenseUtil.getDefenseCount(cityBuilding.buildingID) + "个)";
//				}else{
					return cityBuilding.building.name + "(" + cityBuilding.level + "级)";
//				}
			}
		}
		
	}
}