package com.hifong.war.util.tooltip
{
	import com.hifong.war.model.ModelLocator;
	
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class EquipmentTooltip extends CommonTooltip
	{

		public function EquipmentTooltip(equipment:Object)
		{
			var tooltip:String = "";
			
			tooltip += "<font size=\"14\"><b>" + equipment.name + "</b></font>\n";
			tooltip += "─────────────" + "\n";
			
			tooltip += "增加指挥：+" + equipment.command + "\n";
			tooltip += "增加防护：+" + equipment.defense + "\n";
			tooltip += "增加思维：+" + equipment.mind + "\n";
			tooltip += "增加行政：+" + equipment.executivepower + "\n";
			if(ModelLocator.getInstance().currentCityHero.level<equipment.requiredLevel){
				tooltip += "需要等级：<font color=\"#FF0000\">" + equipment.requiredLevel + "</font>";
			}else{
				tooltip += "需要等级：" + equipment.requiredLevel;
			}

			txtMsg.htmlText = tooltip;
		}
        
	}
}