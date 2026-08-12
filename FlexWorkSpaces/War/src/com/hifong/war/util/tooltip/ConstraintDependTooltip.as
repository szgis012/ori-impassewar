package com.hifong.war.util.tooltip
{
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.DateFormatUtil;
	
	import mx.controls.Text;
	
	public class ConstraintDependTooltip extends CommonTooltip
	{
		
		private var model:ModelLocator = ModelLocator.getInstance();
		
		/**
		 * 构造函数
		 * operate 操作名称：建造，升级，制造
		 * 
		 */ 
		public function ConstraintDependTooltip(operate:String,name:String,constraintDepend:Object)
		{
			txtMsg.width = 194;
			var tooltip:String = "";
			var preBuilding:String = "";
			var preTech:String = "";
			
			var i:int;
			if(constraintDepend.preBuildingList!=null){
				for(i=0;i<constraintDepend.preBuildingList.length;i++){
					var isAvailiable:Boolean = false;
					for(var j:int=0;j<model.cityBuildingList.length;j++){
						if(model.cityBuildingList.getItemAt(j).buildingID==constraintDepend.preBuildingList.getItemAt(i).buildingID && model.cityBuildingList.getItemAt(j).level>=constraintDepend.preBuildingList.getItemAt(i).level){
							isAvailiable = true;
							break;
						}
					}
					if(!isAvailiable){
						preBuilding += "前提建筑：<font color=\"#FF0000\">" + constraintDepend.preBuildingList.getItemAt(i).buildingName + "(等级" + constraintDepend.preBuildingList.getItemAt(i).level + ")</font>\n";
					}else{
						preBuilding += "前提建筑：" + constraintDepend.preBuildingList.getItemAt(i).buildingName + "(等级" + constraintDepend.preBuildingList.getItemAt(i).level + ")\n";
					}
				}
			}
			
			if(constraintDepend.preTechList!=null){
				for(i=0;i<constraintDepend.preTechList.length;i++){
					preTech += "前提科技：" + constraintDepend.preTechList.getItemAt(i).techName + "(等级" + constraintDepend.preTechList.getItemAt(i).level + ")\n";
				}
			}
			
			tooltip += "<font size=\"14\"><b>" + operate + "：" + name;
			
			if(operate == "升级"){
				tooltip += "(等级" + constraintDepend.level + ")";
			}
			tooltip += "</b></font>" + "\n\n";
			tooltip += constraintDepend.description + "\n";
			tooltip += "───────────────" + "\n";
			
			tooltip += preBuilding;
			tooltip += preTech;
			
			if(constraintDepend.costWood!=0){
				if(model.cityInfo.cityResource.woodNum<constraintDepend.costWood){
					tooltip += "消耗木材：<font color=\"#FF0000\">" + constraintDepend.costWood + "</font>" + "\n";
				}else{
					tooltip += "消耗木材：" + constraintDepend.costWood + "\n";
				}
			}
			
			if(constraintDepend.costSteel!=0){
				if(model.cityInfo.cityResource.steelNum<constraintDepend.costSteel){
					tooltip += "消耗钢铁：<font color=\"#FF0000\">" + constraintDepend.costSteel + "</font>" + "\n";
				}else{
					tooltip += "消耗钢铁：" + constraintDepend.costSteel + "\n";
				}
			}
			
			if(constraintDepend.costOil!=0){
				if(model.cityInfo.cityResource.oilNum<constraintDepend.costOil){
					tooltip += "消耗石油：<font color=\"#FF0000\">" + constraintDepend.costOil + "</font>" + "\n";
				}else{
					tooltip += "消耗石油：" + constraintDepend.costOil + "\n";
				}
			}
			
			if(constraintDepend.costFood!=0){
				if(model.cityInfo.cityResource.foodNum<constraintDepend.costFood){
					tooltip += "消耗食物：<font color=\"#FF0000\">" + constraintDepend.costFood + "</font>" + "\n";
				}else{
					tooltip += "消耗食物：" + constraintDepend.costFood + "\n";
				}
			}
			
			if(constraintDepend.costMoney!=0){
				if(model.cityInfo.cityResource.moneyNum<constraintDepend.costMoney){
					tooltip += "消耗金钱：<font color=\"#FF0000\">" + constraintDepend.costMoney + "</font>" + "\n";
				}else{
					tooltip += "消耗金钱：" + constraintDepend.costMoney + "\n";
				}
			}
			
			tooltip += "───────────────" + "\n";
			
			if(constraintDepend.costPopulation>0){
				if(model.cityInfo.populationFree<constraintDepend.costPopulation){
					tooltip += "花费人口：<font color=\"#FF0000\">" + constraintDepend.costPopulation + "</font>" + "\n";
				}else{
					tooltip += "花费人口：" + constraintDepend.costPopulation + "\n";
				}
			}

			tooltip += "花费时间：" + DateFormatUtil.convertSecondToTime(constraintDepend.costTime);
			
			txtMsg.htmlText = tooltip;
		}
	}
}