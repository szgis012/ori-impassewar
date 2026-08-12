package com.hifong.war.constant
{
	import mx.collections.ArrayCollection;
	
	/**
	 * 城防常量
	 */
	public class CityDefenseConstant
	{
		public function CityDefenseConstant()
		{
		}
		
		/**
		 * 城市防御类型列表
		 */
		public static var CITY_DEFENSE_TYPE:ArrayCollection = new ArrayCollection(new Array("","围墙","碉堡","火炮","防空炮"));
		
		/**
		 * 城市防御属性列表
		 */
		//public static var CITY_DEFENSE_LIST:ArrayCollection = new ArrayCollection(new Array(null));
		public static var CITY_DEFENSE_ATTRIBUTE_LIST:Object = 
		{
			1:{life:150,attack:0,defense:7,range:0},
			2:{life:120,attack:10,defense:4,range:3},
			3:{life:90,attack:40,defense:4,range:5},
			4:{life:90,attack:70,defense:3,range:4}
		};

		/**
		 * 城市防御名称列表
		 */
		public static var CITY_DEFENSE_NAME_LIST:ArrayCollection =  new ArrayCollection(new Array("围墙","碉堡","碉堡","火炮","火炮","防空炮","防空炮"));

	}
}