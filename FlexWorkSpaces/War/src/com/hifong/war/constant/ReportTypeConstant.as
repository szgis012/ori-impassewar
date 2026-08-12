package com.hifong.war.constant
{
	/**
	 * 报告类型常量
	 */ 
	public class ReportTypeConstant
	{
		/** 军事行动 */
		public static const MILITARY_ACTION:int = 1;

		/** 其他报告 */
		public static const OTHER:int = 2;
		
		
		/**
		 * 得到报告类型对应的文字
		 * type 为以上定义的报告类型常量
		 */ 
		public static function getTypeName(type:int):String{
			switch(type){
				case MILITARY_ACTION:
					return "军事行动";
				case OTHER:
					return "其他报告";
				default:
					return "未知报告";		
					
			}
		}

	}
}