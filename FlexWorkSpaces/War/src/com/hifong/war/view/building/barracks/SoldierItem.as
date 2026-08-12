package com.hifong.war.view.building.barracks
{
	/**
	 * 兵营的一种兵种
	 * 
	 */ 
	public class SoldierItem
	{
		public function SoldierItem()
		{
		}
		
		//兵种编号
		public function get id():int{
			return 1001;
		}
		
		//兵种名称
		public function get name():String{
			return "步兵";
		}
		
		//生命值
		public function get life():int{
			return 100;
		}
		
		//攻击
		public function get attack():int{
			return 50;
		}
		
		//攻击范围
		public function get range():int{
			return 3;
		}
		
		//速度
		public function get speed():int{
			return 3;
		}
		
		//防御
		public function get defence():int{
			return 9;
		}
		
		//消耗粮饷
		public function get food():int{
			return 99;
		}
		
		//消耗军费
		public function get cost():int{
			return 30;
		}
		
		//所占人口
		public function get population():int{
			return 90;
		}
		
		//负重
		public function get carry():int{
			return 100;
		}
		
		//士兵数量
		public function get num():int{
			return 123456;
		}
		
		//兵种信息描述
		public function get description():String{
			return "装备长枪的正规军，攻击较强，是步兵的中坚力量。";
		}
		
		//兵种头像
		public function get image():String{
			return "images/abc.png";
		}

		//所需装备
		public function get needEquips():Array{
			return ["头盔","来福枪"];
		}
		
		//攻击类型
		public function attackType():int{
			return 1;
		}
	}
	
}