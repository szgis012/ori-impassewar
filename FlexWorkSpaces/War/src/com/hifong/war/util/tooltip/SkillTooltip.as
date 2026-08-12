package com.hifong.war.util.tooltip
{
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class SkillTooltip extends CommonTooltip
	{

		public function SkillTooltip(skill:Object)
		{
			txtMsg.width = 206;
			
			var tooltip:String = "";
			
			tooltip += "<font size=\"14\"><b>" + skill.name + "</b></font>" + "\n";
			tooltip += "────────────────" + "\n";
			tooltip += "效果：" + skill.description + "\n";
			tooltip += "消耗体力：" + skill.costStamina + "\n";
			tooltip += "最高等级：" + skill.maxLevel + "\n";
			tooltip += "学习等级：" + skill.studyLevel + "\n";
			tooltip += "学习费用：" + skill.studyMoney + "\n";
			
			txtMsg.htmlText = tooltip;
		}
		
	}
}