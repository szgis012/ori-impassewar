package com.hifong.war.util
{
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.vo.ArmyVO;
	
	import mx.collections.ArrayCollection;
	
	
	public class ArmyUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		/**
		 * 判断是否满足给定的军械条件
		 */ 
		public static function meetArmyDepend(army:ArmyVO):Boolean{
			var depend:Object;
			var armyDependList:ArrayCollection = army.armyDependList;
			
			if(army.constraintDepend){
				//检查是否满足前提建筑条件
				if(!BuildingUtil.meetAllConditions(army.constraintDepend)){
					return false;
				}
			}
			
			if(army.population > model.cityInfo.recruitNum){
				return false;
			}
			
			for(var i:int=0; i<armyDependList.length; i++){
				depend = armyDependList.getItemAt(i);
				if(!OrdanceUtil.hasEnoughOrdance(depend.ordnanceID,depend.num)){
					return false;
				}
			}
			
			return true;
		}

	}
}