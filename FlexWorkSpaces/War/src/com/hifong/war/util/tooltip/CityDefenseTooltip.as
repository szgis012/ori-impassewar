package com.hifong.war.util.tooltip
{
	import com.hifong.war.constant.CityDefenseConstant;
	
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class CityDefenseTooltip extends CommonTooltip
	{

		public function CityDefenseTooltip(cityDefense:Object)
		{
			
			this.width = 100;

			var tooltip:String = "<b>" + CityDefenseConstant.CITY_DEFENSE_TYPE.getItemAt(cityDefense.type) + "</b>" + "\n";
			tooltip += "数量：" + cityDefense.num + "\n";
			tooltip += "生命：" + CityDefenseConstant.CITY_DEFENSE_ATTRIBUTE_LIST[cityDefense.type].life + "\n";
			tooltip += "攻击：" + CityDefenseConstant.CITY_DEFENSE_ATTRIBUTE_LIST[cityDefense.type].attack + "\n";
			tooltip += "防御：" + CityDefenseConstant.CITY_DEFENSE_ATTRIBUTE_LIST[cityDefense.type].defense + "\n";
			tooltip += "攻击范围：" + CityDefenseConstant.CITY_DEFENSE_ATTRIBUTE_LIST[cityDefense.type].range+ "\n";
			
			txtMsg.htmlText = tooltip;
		}
		
	}
}