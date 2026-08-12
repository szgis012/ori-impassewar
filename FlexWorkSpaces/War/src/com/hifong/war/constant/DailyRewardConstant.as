package com.hifong.war.constant
{
	/**
	 * 每日奖励
	 */
	public class DailyRewardConstant
	{
		public function DailyRewardConstant()
		{
		}
		public static function getReward(index:int,type:int):String{
			var s:String; //资源
			var army:String;// 部队
			var money:Number; //礼金
			switch(index){
				case 1:
				case 2:
				case 3:
				case 4:
				s="金，石油，木材，食物，钢铁3000。";
				army="装甲车2";
				money=10;
				break;
				case 5:
				case 6:
				case 7:
				case 8:
				s="金，石油，木材，食物，钢铁 1万。";;
				army="装甲车5";
				money=15;
				
				break;
				case 9:			
				case 10:			
				case 11:			
				s="金，石油，木材，食物，钢铁 3万。";
				army="装甲车20";
				money=20;
				break;
				
				case 12:			
				case 13:			
				case 14:			
				s="金，石油，木材，食物，钢铁 5万。";
				army="装甲车40";
				money=25;
				break;
				case 15:			
				case 16:			
				case 17:			
				case 18:		
				s="金，石油，木材，食物，钢铁 10万。";
				army="装甲车70";
				money=30;
				break;	
				case 19:			
				s="金，石油，木材，食物，钢铁 30万。";
				army="装甲车100";
				money=35;
				break;
				case 20:			
				s="金，石油，木材，食物，钢铁 50万。";
				army="装甲车200";
				money=40;
				break;
			}
			switch(type){
				case 1:
				return s;
				break;
				case 2:
				return army;
				break;
				case 3:
				return String(money);
				break;
			}
			return "";
		}
	}
}