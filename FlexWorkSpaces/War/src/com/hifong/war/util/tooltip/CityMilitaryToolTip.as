package com.hifong.war.util.tooltip
{
	import com.hifong.war.util.ConvertUtil;
	
	/**
	 * 城市部队使用的tooltip
	 * 
	 */ 
	public class CityMilitaryToolTip extends CommonTooltip
	{
		public function CityMilitaryToolTip(cityMilitary:Object)
		{
			this.width = 140;
			var tooltip:String = "";
			
			tooltip += getArmyInfo(cityMilitary.army1);		
			tooltip += getArmyInfo(cityMilitary.army2);
			tooltip += getArmyInfo(cityMilitary.army3);
			tooltip += getArmyInfo(cityMilitary.army4);
			tooltip += getArmyInfo(cityMilitary.army5);
			tooltip += getArmyInfo(cityMilitary.army6);
			tooltip += getArmyInfo(cityMilitary.army7);
			tooltip += getArmyInfo(cityMilitary.army8);	
			
			if(tooltip == ""){
				tooltip = "还未编制部队！";
			}
			
			txtMsg.htmlText = tooltip;
		}
		
		//解析一支军队的信息，返回tooltip使用的形式
		private function getArmyInfo(army:String):String{
			if(army == null || army.length == 0){
				return "";
			}
			
			var arr:Array = army.split(":");
			
			if(arr.length != 2){
				return "";
			}
			
			return ConvertUtil.getArmyName(arr[0]) + "：" + arr[1] + "\n";
		}
	}
}