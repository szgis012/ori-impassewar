package com.hifong.war.util.tooltip
{
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class HeroSkillTooltip extends CommonTooltip
	{

		public function HeroSkillTooltip(heroSkill:Object)
		{
			txtMsg.width = 206;
			var tooltip:String = "";
			
			tooltip += "<font size=\"14\"><b>" + heroSkill.skill.name + "(等级" + heroSkill.level + ")</b></font>" + "\n";
			tooltip += "────────────────" + "\n";
			tooltip += "效果：" + heroSkill.skill.description + "\n";
			tooltip += "消耗体力：" + heroSkill.skill.costStamina + "\n";
			tooltip += "熟练度：" + heroSkill.proficiency + "\n";
			tooltip += "\n";
			
			if(heroSkill.nextLevelSkill!=null){
				tooltip += "<font size=\"14\"><b>升级下一级条件：</b></font>" + "\n";
				tooltip += "─────────────" + "\n";
				tooltip += "效果：" + heroSkill.nextLevelSkill.description + "\n";
				tooltip += "消耗体力：" + heroSkill.nextLevelSkill.costStamina + "\n";
				tooltip += "消耗熟练度：" + heroSkill.nextLevelSkill.studyProficiency + "\n";
				tooltip += "学习等级：" + heroSkill.nextLevelSkill.studyLevel + "\n";
				tooltip += "学习费用：" + heroSkill.nextLevelSkill.studyMoney + "\n";
			}
			
			txtMsg.htmlText = tooltip;
		}
		
	}
}