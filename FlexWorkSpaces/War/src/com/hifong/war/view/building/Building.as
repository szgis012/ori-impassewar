package com.hifong.war.view.building
{
	import com.hifong.war.constant.GlobalConstant;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.DateFormatUtil;
	
	//建筑
	public class Building
	{
    
    	//对应服务器端的Building类
    	private var _building:Object;
    	
    	//重写后的图片地址
    	private var imgSrc:String;
    	
		public function Building(b:Object)
		{
			building = b;
		}
		
		public function set building(b:Object):void{
			this._building = b;
			var prefix:String = GlobalConstant.BUILDING_DIR_PREFIX + ModelLocator.getInstance().playerInfo.country + "/";
			//根据阵营不同重写图片地址(TODO BUG临时解决方案)
			if(String(b.image).indexOf(prefix,0) >=0){
				imgSrc = b.image;
			}else{
				imgSrc = prefix + b.image;
			}
		}
		
		public function get building():Object{
			return this._building;
		}
		
		/** 建筑编号 */
		public function get buildingID():int{
			return building.buildingID;
		}
		
		 /** 建筑名称 */
		public function get name():String{
			return building.name;
		}
		
		/** 图片 */
		public function get image():String{
			return imgSrc;
		}
		
		 /** 描述 */
		public function get description():String{
			return building.description;
		}
		
		/** 最高等级 */
		public function get maxLevel():int{
			return building.maxLevel;
		}
		
		/** 是否唯一(1.是 2.否) */
		public function get isOnlyone():int{
			return building.isOnlyone;
		}
		
		/** 当前级别对应的约束依赖(对应服务端的ConstraintDepend) */
		public function get constraintDepend():Object{
			return building.constraintDepend;
		}
		
		/** 下一等级对应的约束依赖(对应服务端的ConstraintDepend)*/
		public function get nextConstraintDepend():Object{
			return building.nextConstraintDepend;
		}
		
		//是否满足升级条件
		public function get canUpgrade():Boolean{
			return true;
		}
		
		
		//获得创建信息
		public function get createInfo():String{
			return getDependInfo(false);
		}
		
		
		//获得升级信息
		public function get upgradeInfo():String{
			return getDependInfo(true);
		}
		
		/*
		 * 获得建造依赖信息
		 * isUpgrade=true表示是更新信息，false为创建信息
		 */
		private function getDependInfo(isUpgrade:Boolean):String{
			var preBuilding:String = "";
			var title:String;
			var depend:Object = building.nextConstraintDepend;
			
			//升级
			if(isUpgrade){
				title = "升级条件";
			}else{
				title = "建造条件";
			}
			
			if(depend.preBuildingList!=null){
				for(var i:int=0;i<depend.preBuildingList.length;i++){
					preBuilding += "\n" + depend.preBuildingList.getItemAt(i).buildingName + "：" + depend.preBuildingList.getItemAt(i).level + "级";
				}
			}
			
			return "<font size=\"14\">"+ title+"</font>" + 
					"\n─────────────" + 
					preBuilding + 
					"\n木材：" + depend.costWood + 
					"\n钢铁：" + depend.costSteel + 
					"\n石油：" + depend.costOil +
					"\n食物：" + depend.costFood +
					"\n金钱：" + depend.costMoney +
					"\n─────────────" + 
					"\n占用人口：" + depend.costPopulation + 
					"\n花费时间：" + DateFormatUtil.convertSecondToTime(depend.costTime);
		}
		
		//获得拆除信息
		public function get destroyInfo():String{
			return "拆除可以得到:\n 金钱500";
		}

	}
}