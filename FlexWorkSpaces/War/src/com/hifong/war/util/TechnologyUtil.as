package com.hifong.war.util
{
	/**
	 * 科技工具类 
	 * 
	 */ 
	public class TechnologyUtil
	{
		/**
		 * 判断所给的cityTechnology是否已经在该科技允许的最高级别了，如果是返回true，否则返回false
		 * 
		 */ 
		public static function onTechnologyMaxLevel(cityTechnology:Object):Boolean{
			return (cityTechnology.technology.maxLevel == cityTechnology.level); 
		}

	}
}