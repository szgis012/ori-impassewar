package com.hifong.war.util.tooltip
{
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class CastSkillTooltip extends CommonTooltip
	{

		public function CastSkillTooltip(heroSkill:Object)
		{

			var tooltip:String = "";
			
			tooltip += "<font size=\"14\"><b>" + heroSkill.skill.name + "</b></font>" + "\n";
			tooltip += "─────────────" + "\n";
			tooltip += "效果：" + heroSkill.skill.description + "\n";
			tooltip += "消耗体力：" + heroSkill.skill.costStamina + "\n";
			
			txtMsg.htmlText = tooltip;
		}
		
	}
}