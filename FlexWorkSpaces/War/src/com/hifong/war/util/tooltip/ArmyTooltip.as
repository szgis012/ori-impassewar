package com.hifong.war.util.tooltip
{
	import com.hifong.war.vo.ArmyVO;
	
	public class ArmyTooltip extends CommonTooltip
	{

		public function ArmyTooltip(army:ArmyVO)
		{

			var tooltip:String = "<font size=\"14\"><b>" + army.name + "</b></font>" + "\n";
			tooltip += "─────────────" + "\n";
			tooltip += "生命：" + army.life + "\n";
			tooltip += "攻击力：" + army.attack + "\n";
			tooltip += "防御力：" + army.defense + "\n";
			tooltip += "行动力：" + army.speed + "\n";
			tooltip += "攻击范围：" + army.range + "\n";
			tooltip += "负重：" + army.carry + "\n";
			tooltip += "消耗石油：" + army.costOil + "\n";
			tooltip += "消耗食物：" + army.costFood + "\n";
			tooltip += "消耗金钱：" + army.costMoney + "\n";
			switch(army.attackType){
				case 1:
					tooltip += "攻击类型：" + "仅对地" + "\n";
					break;
				case 2:
					tooltip += "攻击类型：" + "仅对空" + "\n";
					break;
				case 3:
					tooltip += "攻击类型：" + "对地且对空" + "\n";
					break;
			}
			
			txtMsg.htmlText = tooltip;
		}
        
	}
}