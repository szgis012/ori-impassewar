package com.hifong.war.view.stronghold
{
	import com.hifong.war.events.building.BuildingStateConstant;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.ImagePathUtil;
	import com.hifong.war.view.assets.WarAssets;
	import com.hifong.war.vo.StrongholdShbuildingVO;
	
	import flash.filters.ColorMatrixFilter;
	
	import mx.binding.utils.ChangeWatcher;
	import mx.controls.Image;
	import mx.events.PropertyChangeEvent;

	[Bindable]
	/**
	 *要塞建筑，地图上的一个建筑单元
	 * 
	 */ 
	public class StrongholdBuildingGrid extends Image
	{
		//建筑被移除时发送的事件
		public static const EVENT_STRONGHOLDBUILDING_REMOVED:String = "STRONGHOLDBUILDING_REMOVED";
		
		//灰色滤镜
		private static const FILTER_GREY:ColorMatrixFilter = new ColorMatrixFilter([0.3086, 0.6094, 0.082, 0, 0, 0.3086, 0.6094, 0.082, 0, 0, 0.3086, 0.6094, 0.082, 0, 0, 0.3086, 0.6094, 0.082, 1, 0]);
		//和当前网格绑定的StrongholdShbuildingVO
		private var _strongholdBuilding:StrongholdShbuildingVO;
		//网格在地图上的位置
		private var _position:int ;
		//状态变化监视
		private var stateWatcher:ChangeWatcher;
		
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function get strongholdBuilding():StrongholdShbuildingVO{
			return this._strongholdBuilding;
		}
		
		public function set strongholdBuilding(shb:StrongholdShbuildingVO):void{
			//如果已经有绑定的_strongholdBuilding，先取消该对象上的watcher
			if(_strongholdBuilding){
				stateWatcher.unwatch();
			}
			
			this._strongholdBuilding = shb;
			
			if(shb){
				initBuildingGrid();
			}
		}
		
		public function get position():int{
			return this._position;
		}
		
		public function set position(pos:int):void{
			this._position = pos;
		}
		
		/** 检查该Grid是否为要塞空地 */
		public function isBlankGrid():Boolean{
			return strongholdBuilding == null;
		}
		
		/** 
		 * 重置为要塞空地
		 * 
		 * 此方法只在拆除为空地或者取消新建的建筑时才会使用的方法
		 */
		public function resetGrid():void{
			this._strongholdBuilding = null;
			this.filters = null;
			this.source = new  WarAssets.blankAsset();
//			//发布事件
			if(this.willTrigger(EVENT_STRONGHOLDBUILDING_REMOVED))
				this.dispatchEvent(new Event(EVENT_STRONGHOLDBUILDING_REMOVED));
		}
		
		/** 返回表示要塞空地的BuildingGrid */
		public static function getBlankBuildingGrid():StrongholdBuildingGrid{
			var bg:StrongholdBuildingGrid = new StrongholdBuildingGrid();
			bg.source = new WarAssets.blankAsset();
			
			return bg;
		}
		
		//初始化要塞建筑数据
		private function initBuildingGrid():void{
			this.source = ImagePathUtil.getStrongholdBuildingPath(strongholdBuilding.building.image);
			
			changeViewState(strongholdBuilding.state);
			
			//初始化state改变监视
			stateWatcher = ChangeWatcher.watch(strongholdBuilding,"state",onStateChanged);
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
				return strongholdBuilding.building.name + " " + strongholdBuilding.level + "级";
			}
		}
		
	}
}