package com.hifong.war.util.tooltip
{
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class BattleArmyTooltip extends CommonTooltip
	{

		public function BattleArmyTooltip(battleArmy:Object)
		{
			txtMsg.width = 130;

			var tooltip:String = "<b>" + battleArmy.army.name + "(" + battleArmy.amount + ")" + "</b>" + "\n";
			tooltip += "生命：" + battleArmy.army.life + "\n";
			tooltip += "攻击力：" + battleArmy.army.attack + "\n";
			tooltip += "防御力：" + battleArmy.army.defense + "\n";
			tooltip += "行动力：" + battleArmy.army.speed + "\n";
			tooltip += "攻击范围：" + battleArmy.army.range + "\n";
			
			txtMsg.htmlText = tooltip;
		}
        
	}
}