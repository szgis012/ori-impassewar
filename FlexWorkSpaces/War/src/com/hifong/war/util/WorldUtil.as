package com.hifong.war.util
{
	import com.hifong.war.constant.ArmamentConstant;
	import com.hifong.war.constant.WildLandTypeConstant;
	import com.hifong.war.vo.MapVO;
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 世界地图工具类
	 */ 
	public class WorldUtil
	{
		/**
		 * 获得坐标X,Y的索引值
		 * 注：这里假设x比1000小，否则可能生产重复的索引.
		 */
		public static function getIndexFromXY(x:int,y:int):int{
			return (1000*y + x);
		}

		/**
		 * 来自数据库的野地类型转换成WildLandTypeConstant定义的类型
		 */ 
		public static function getWildLandType(type:int):int{
			//空地
			if(type == 1){
				return WildLandTypeConstant.CITY;
				
			}else if(type == 2){
				return WildLandTypeConstant.STRONGHOLD;
			}else if(type>=11&& type<=19){
				return WildLandTypeConstant.SPACE;
			}else if(type>=51 && type<=53){
				return WildLandTypeConstant.PLAIN;
			}else if(type>=31 && type<=33){
				return WildLandTypeConstant.HILL;
			}else if(type>=21 && type<=23){
				return WildLandTypeConstant.HOLT;
			}else if(type>=41 && type<=43){
				return WildLandTypeConstant.LAKE;
			}else{
				throw new Error("未知类型");
			}
		}
		
		/**
		 * 判断所给类型是否为空地类型
		 * type为数据库的野地类型
		 */ 
		public static function isSpace(type:int):Boolean{
			return getWildLandType(type) ==  WildLandTypeConstant.SPACE;
		}
		
		/**
		 * 获得兵力的难度等级
		 */ 
		public static function getArmamentLevel(monsterList:ArrayCollection):int{
			//总兵力
			var totalNum:int = 0;
			
			for each (var mon:Object in monsterList){
				totalNum += mon.num;
			}
			
			//todo 根据总人数确认难度等级
			if(totalNum<100){
				return ArmamentConstant.LEVEL_1;
			}else if(totalNum<300){
				return ArmamentConstant.LEVEL_2;
			}else if(totalNum<1000){
				return ArmamentConstant.LEVEL_3;
			}else if(totalNum<3000){
				return ArmamentConstant.LEVEL_4;
			}else if(totalNum<5000){
				return ArmamentConstant.LEVEL_5;
			}else if(totalNum<10000){
				return ArmamentConstant.LEVEL_6;
			}else if(totalNum<30000){
				return ArmamentConstant.LEVEL_7;
			}else if(totalNum<50000){
				return ArmamentConstant.LEVEL_8;
			}else{
				return ArmamentConstant.LEVEL_9;
			}
			
		}
		
		/**
		 * 获得地图上从一点到另一点行走所需的时间
		 * startX,startY 起点坐标
		 * endX,endY 终点坐标
		 * speed 速度
		 * 返回需要的秒数
		 */ 
		public static function getDistanceTime(startX:int,startY:int,endX:int,endY:int,speed:int=1):int{
			//单位距离所需的时间,也就是速度为1的单位走完一个距离需要150秒
			var timePerDistance:int = 150;
			var distance:Number = Math.sqrt(Math.pow(endX-startX,2)+Math.pow(endY-startY,2));
			
			return Math.ceil(distance * timePerDistance / speed);
		}
		
		/**
		 * 获得与目标位置间的宣战状态：0 可宣战 1 已宣战 2 可战斗
		 * targetPosX,targetPosY 分别为目标的x，y坐标
		 */ 
		/*public static function getDeclareWarState(targetPosX:int,targetPosY:int):int{
			var dw:DeclareWarVO ;
			var state:int = 0;
			var model:ModelLocator =  ModelLocator.getInstance();
			
			for each(dw in model.declareWarList){
				//如果存在已宣战列表中
				if(dw.toPosX == targetPosX && dw.toPosY == targetPosY){
					if(dw.startTime.getTime() > model.serverTime.getTime()){
						state = 1;
					}else{
						state = 2;
					}
					
					break;
				}
			}
			
			return state;
		}*/
		
		
		/**
		 * 获得野地信息
		 */ 
		public static function getWildLandDescription(map:MapVO):String{
			return WildLandTypeConstant.getTypeDescription(map.category);
		}
		
		
		/**
		 * 获得城市信息
		 */ 
		public static function getCityDescription(map:MapVO):String{
			if(map.player==null) return null;
			var info:String = "市长："+ map.player.name+"\n";
			info += "声望："+ map.player.renown+"\n";
			info += "军团：";
			
			if(map.player.guildName != null){
				info += map.player.guildName;
			}else{
				info += "无";
			}
			
			info += "\n阵营："+ ((map.player.country==1)?"自由联邦":"联合帝国");
			
			return info;
		}
	}
	
}