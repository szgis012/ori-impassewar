package com.hifong.war.util.tooltip
{
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.ConvertUtil;
	import com.hifong.war.util.OrdanceUtil;
	import com.hifong.war.vo.ArmyVO;
	import com.hifong.war.vo.CityOrdnanceVO;
	
	import mx.collections.ArrayCollection;
	
	public class BuildArmyTooltip extends CommonTooltip
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		/**
		 * 武装士兵，组装车辆，组装飞机使用的tooltip
		 * operate 操作名称： 武装,组装
		 * armyDependList 兵种招募所需条件列表(ArmyDepend)
		 */ 
		public function BuildArmyTooltip(operate:String,army:ArmyVO)
		{
			var tooltip:String = "<font size=\"14\"><b>" + operate + "：" + army.name + "</b></font>" + "\n";
			tooltip += "─────────────" + "\n";
			
			var depend:Object;
			var cityOrdance:CityOrdnanceVO;
			var armyDependList:ArrayCollection = army.armyDependList;
			var constraintDepend:Object = army.constraintDepend;
			
			if(constraintDepend && constraintDepend.preBuildingList){
				for(var i:int=0;i<constraintDepend.preBuildingList.length;i++){
					var isAvailiable:Boolean = false;
					for(var j:int=0;j<model.cityBuildingList.length;j++){
						if(model.cityBuildingList.getItemAt(j).buildingID==constraintDepend.preBuildingList.getItemAt(i).buildingID && model.cityBuildingList.getItemAt(j).level>=constraintDepend.preBuildingList.getItemAt(i).level){
							isAvailiable = true;
							break;
						}
					}
					if(!isAvailiable){
						tooltip += "前提建筑：<font color=\"#FF0000\">" + constraintDepend.preBuildingList.getItemAt(i).buildingName + "(等级" + constraintDepend.preBuildingList.getItemAt(i).level + ")</font>\n";
					}else{
						tooltip += "前提建筑：" + constraintDepend.preBuildingList.getItemAt(i).buildingName + "(等级" + constraintDepend.preBuildingList.getItemAt(i).level + ")\n";
					}
				}
				
			}
			
//			if(constraintDepend.preTechList!=null){
//				for(i=0;i<constraintDepend.preTechList.length;i++){
//					preTech += "前提科技：" + constraintDepend.preTechList.getItemAt(i).techName + "(等级" + constraintDepend.preTechList.getItemAt(i).level + ")\n";
//				}
//			}
			
			if(army.population > model.cityInfo.recruitNum){
				tooltip += "需要新兵：<font color=\"#FF0000\">" + army.population + "</font>\n";
			}else{
				tooltip += "需要新兵：" + army.population + "\n";
			}
			
			//depend为ArmyDepend对象(server端)
			for(var k:int=0; k<armyDependList.length; k++){
				depend = armyDependList.getItemAt(k);
				tooltip += ConvertUtil.getOrdnanceNameByID(depend.ordnanceID)+"：" ;
				//如果有足够的军械
				if(OrdanceUtil.hasEnoughOrdance(depend.ordnanceID,depend.num)){
					tooltip += depend.num;
				}else{
					tooltip += "<font color=\"#FF0000\">"+depend.num+"</font>";
				}
				
				tooltip += "\n";
			}
			
			txtMsg.htmlText = tooltip;
		}
        
	}
}