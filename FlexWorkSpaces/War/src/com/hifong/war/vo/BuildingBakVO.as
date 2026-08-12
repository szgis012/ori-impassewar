/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	import flash.net.registerClassAlias;
	
	[RemoteClass(alias="com.hifong.war.vo.BuildingVO")]
    /**
     *
     * Defines the <code>BuildingVO<code> Value Object implementation
     *
     * @see com.adobe.cairngorm.vo.IValueObject
     *
     */	
	public class BuildingBakVO implements IValueObject {
		//对应服务器端的Building类
    	private var _building:Object;
    	
		public function Building(b:Object)
		{
			building = b;
		}
		
		public function set building(b:Object):void{
			this._building = b;
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
			return building.image;
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
		
		/** 上一个等级升到当前等级对应的约束依赖,对应服务器端的ConstraintDepend */
		public function get currentConstraintDepend():Object{
			return building.currentCconstraintDepend;
		}
		
		/** 升级到下一级的约束依赖 ,对应服务器端的ConstraintDepend*/
		public function get constraintDepend():Object{
			return building.currentCconstraintDepend;
		}
		
		//是否满足升级条件
		public function get canUpgrade():Boolean{
			return true;
		}
		
		//获得升级信息
		public function get upgradeInfo():String{
			return "升级条件如下:\n 金钱1000";
		}
		
		//获得拆除信息
		public function get destroyInfo():String{
			return "拆除可以得到:\n 金钱500";
		}
	}
}