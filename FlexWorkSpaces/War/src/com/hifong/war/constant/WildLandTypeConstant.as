package com.hifong.war.constant
{
	/**
	 * 野地类型
	 */ 
	public class WildLandTypeConstant
	{
		//城池
		public static const CITY:int = 1;
		//要塞
		public static const STRONGHOLD:int = 2;
		//空地
		public static const SPACE:int = 0;
		//铁矿
		public static const PLAIN:int = 4;
		//油井
		public static const HILL:int = 5; 
		//麦田
		public static const WHEAT:int=6;
		//林场
		public static const HOLT:int = 3;
		//海洋
		public static const LAKE:int = 7;
		
		
		
		//得到类型的名称
		public static function getTypeName(type:int):String{
			var name:String = "未知";
			
			switch(type){
				case SPACE:
					name = "空地";
					break;
				case CITY:
					name = "城市";
					break;
				case STRONGHOLD:
					name = "要塞";
					break;
				case PLAIN:
					name = "铁矿";
					break;
				case HILL:
					name = "油井";
					break;
				case HOLT:
					name = "林场";
					break;
				case WHEAT:
					name = "麦田";
					break;
				case LAKE:
					name = "海洋";
					break;						
				case 8:
					name="海岸";
				break;
			}
			
			return name;
		}
		
		
	//得到类型的描述
	public static function getTypeDescription(type:int):String{
		var desc:String = "未知";
			
			switch(type){
				case CITY:
					desc = "城市";
					break;
				case STRONGHOLD:
					desc = "要塞";
					break;
				case SPACE:
					desc = "空地可以建造要塞。";
					break;
				case PLAIN:
					desc = "钢材、石油、木材建筑物每级产量加成2%，食物建筑物每级产量加成4%。";
					break;
				case HILL:
					desc = "食物、石油、木材建筑物每级产量加成2%，钢材建筑物每级产量加成4%。";
					break;
				case HOLT:
					desc = "钢材、石油、食物建筑物每级产量加成2%，木材建筑物每级产量加成4%。";
					break;
				case LAKE:
					desc = "钢材、食物、木材建筑物每级产量加成2%，石油建筑物每级产量加成4%。";
					break;						
			}
			
			return desc;
		}
	
	}
	
	
	
}