package com.hifong.war.util
{
	import com.hifong.war.constant.CityBuildingStateConstant;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.view.building.BuildingGrid;
	import com.hifong.war.vo.BuildingVO;
	import com.hifong.war.vo.CityBuildingVO;
	import com.hifong.war.vo.DefenseVO;
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 建筑相关工具类
	 */ 
	public class BuildingUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		/**
		 * 获得城市已有建筑的最高等级，0表示没有该建筑
		 * buildingID 建筑编号
		 */ 
		public static function getMaxLevel(buildingID:int):int{
			var list:ArrayCollection = model.cityBuildingList ;
			var level:int = 0;
			
			if(list && list.length>0){
				var cb:CityBuildingVO ;
				
				for each (cb in list){
					//
					if(cb.buildingID == buildingID){
						level = Math.max(level,cb.level);
					}
				}
			}
			
			return level;
		}
		
		/**
		 * 获得城市建筑所属的BuildingGrid对象
		 * cityBuildingID 城市建筑编号
		 */ 
		public static function getBuildingGridByCBID(cityBuildingID:int):BuildingGrid{
			return model.app.cityCanvas.getBuildingGridByCBID(cityBuildingID);
		}

		/**
		 * 获得城市建筑所属的BuildingGrid对象
		 * 注：对于有多个建筑的，返回最高等级的对象
		 * buildingID 建筑编号
		 */ 
		public static function getBuildingGridByBID(buildingID:int):BuildingGrid{
			return model.app.cityCanvas.getBuildingGridByBID(buildingID);
		}


		/**
		 * 将建筑信息列表转换成map形式，其中key为建筑编号，value为编号对应的BuildingVO对象信息
		 * buildingList 为BuildingVO对象列表
		 */ 
		public static function getBuildingListMap(buildingList:ArrayCollection):Object{
			var map:Object = {};
			
			if(buildingList && buildingList.length>0){
				var building:BuildingVO;
				
				for(var i:int=0; i<buildingList.length; i++){
					building = buildingList.getItemAt(i) as  BuildingVO;
					map[building.buildingID] = building;
				}
			}
			
			return map;
			
		}
		/**
		 * 将城防建筑 信息同上转化
		 */
		 public static function getDefenseListMap(defenseLise:ArrayCollection):Object{
			var map:Object = {};
			
			if(defenseLise && defenseLise.length>0){
				var building:DefenseVO;
				
				for(var i:int=0; i<defenseLise.length; i++){
					building = defenseLise.getItemAt(i) as  DefenseVO;
					map[building.defenseID] = building;
				}
			}  
			
			return map;
			
		}
		
		/**
		 * 获得一个空地的BuildingGrid对象
		 * 
		 */ 
		public static function findBlankBuildingGrid():BuildingGrid{
			return model.app.cityCanvas.findBlankBuildingGrid();
		}
		
		/**
		 * 是否满足建筑建造或者升级的所有条件
		 * constraintDepend 建造或者升级的依赖条件
		 */ 
		public static function  meetAllConditions(constraintDepend:Object):Boolean{
			var preBuilding:String = "";
			var preTech:String = "";
			var isAvailiable:Boolean = false;
			
			if(constraintDepend.preBuildingList!=null){
				outer: for(var i:int=0;i<constraintDepend.preBuildingList.length;i++){
					for(var j:int=0;j<model.cityBuildingList.length;j++){
						if(model.cityBuildingList.getItemAt(j).buildingID==constraintDepend.preBuildingList.getItemAt(i).buildingID && model.cityBuildingList.getItemAt(j).level>=constraintDepend.preBuildingList.getItemAt(i).level){
							isAvailiable = true;
							break outer;
						}
					}
				}
				//如果不满足建筑依赖就返回
				if(!isAvailiable)
					return false;
			}
			
			//判断资源是否足够
			if(constraintDepend.costWood!=0 && model.cityInfo.cityResource.woodNum<constraintDepend.costWood){
				return false
			}
			
			if(constraintDepend.costSteel!=0 && model.cityInfo.cityResource.steelNum<constraintDepend.costSteel){
				return false;
			}
			
			if(constraintDepend.costOil!=0 && model.cityInfo.cityResource.oilNum<constraintDepend.costOil){
				return false;
			}
			
			if(constraintDepend.costFood!=0 && model.cityInfo.cityResource.foodNum<constraintDepend.costFood){
				return false;
			}
			
			if(constraintDepend.costMoney!=0 && model.cityInfo.cityResource.moneyNum<constraintDepend.costMoney){
				return false;
			}
			
			if(constraintDepend.costPopulation>0 && model.cityInfo.populationFree<constraintDepend.costPopulation){
				return false;
			}

			return true;
		}
		
		/**
		 * 获得指定城市建筑的等级信息
		 * （给显示建筑窗口的标题使用）
		 */ 
		public static function getLevelInfo(cb:CityBuildingVO):String{
			if(!cb)
				return "";
				
			var str:String;
				
			switch(cb.state){
				case CityBuildingStateConstant.NORMAL:
					str =  "(等级" + cb.level + ")";
					break;
				case CityBuildingStateConstant.BUILDING:
					if(cb.level == 0){
						str =  "(正在建造)";
					}else{
						str =  "(从"+cb.level+"级升到"+(cb.level+1)+"级)";
					}
					break;
				case CityBuildingStateConstant.DESTROYING:
					str =  "(从"+cb.level+"级拆到"+(cb.level-1)+"级)";
					break;
				default:
					str = "";
					break;	
			}
			
			return str;
		}
		
		/**
		 * 判断所给的citybuilding是否已经在该建筑允许的最高级别了，如果是返回true，否则返回false
		 * 
		 */ 
		public static function onBuildingMaxLevel(cityBuilding:Object):Boolean{
			return (cityBuilding.building.maxLevel == cityBuilding.level); 
		}
	}
	
}