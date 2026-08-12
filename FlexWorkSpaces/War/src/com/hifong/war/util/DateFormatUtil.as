package com.hifong.war.util
{
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.formatters.DateFormatter;
	
	public class DateFormatUtil
	{
		private static var dateTimeFormatter:DateFormatter;
		
		private static var shortDateFormatter:DateFormatter;
		
		private static var longDateFormatter:DateFormatter;

		public function DateFormatUtil()
		{
		}
		
		/**
		 * 格式化时间
		 */
		public static function formatTime(date:Date):String{
			if(dateTimeFormatter==null){
				dateTimeFormatter = new DateFormatter();
				dateTimeFormatter.formatString = "MM-DD JJ:NN:SS";
				dateTimeFormatter.error = "error";
			}
			return dateTimeFormatter.format(date);
		}
		
		/**
		 * 格式化日期
		 */
		public static function formatDate(date:Date):String{
			if(shortDateFormatter==null){
				shortDateFormatter = new DateFormatter();
				shortDateFormatter.formatString = "MM-DD JJ:NN";
				shortDateFormatter.error = "error";
			}
			return shortDateFormatter.format(date);
		}
		
		/**
		 * 格式化长日期
		 */
		public static function formatLongDate(date:Date):String{
			if(longDateFormatter==null){
				longDateFormatter = new DateFormatter();
				longDateFormatter.formatString = "YYYY-MM-DD JJ:NN";
				longDateFormatter.error = "error";
			}
			return longDateFormatter.format(date);
		}
		
		/**
		 * 格式化数据网格列日期
		 */
		public static function formatColumnDate(item:Object, column:DataGridColumn):String{
			if(shortDateFormatter==null){
				shortDateFormatter = new DateFormatter();
				shortDateFormatter.formatString = "MM-DD JJ:NN";
				shortDateFormatter.error = "error";
			}
			 return shortDateFormatter.format(item[column.dataField]);
		}
		
		/**
		 * 格式化数据网格列长日期
		 */
		public static function formatColumnLongDate(item:Object, column:DataGridColumn):String{
			if(longDateFormatter==null){
				longDateFormatter = new DateFormatter();
				longDateFormatter.formatString = "YYYY-MM-DD JJ:NN";
				longDateFormatter.error = "error";
			}
			return longDateFormatter.format(item[column.dataField]);
		}
		
		/**
		 * 将秒转换为时间(中文格式)
		 */
		public static function convertSecondToTime(second:Number):String{
			
			var time:String = "";
			
			if(second<3600){
				time += "00时";
				if(second/60<10)
					time += "0";
				time += int(second/60) + "分";
				if(second%60<10)
					time += "0";
				time += second%60 + "秒";
			}else{
				if(second/3600<10)
					time += "0";
				time += int(second/3600) + "时";
				if((second-int(second/3600)*3600)/60<10)
					time += "0";
				time += int((second-int(second/3600)*3600)/60) + "分";
				if(second%60<10)
					time += "0";
				time += int(second%60) + "秒";
			}
			
			return time;
		}
		/**
		 * 将秒转换为时间(e文格式)
		 */
		public static function convertSecondToTimeE(second:Number):String{
			
			var time:String = "";
			
			if(second<3600){
				time += "00:";
				if(second/60<10)
					time += "0";
				time += int(second/60) + ":";
				if(second%60<10)
					time += "0";
				time += second%60 ;
			}else{
				if(second/3600<10)
					time += "0";
				time += int(second/3600) + ":";
				if((second-int(second/3600)*3600)/60<10)
					time += "0";
				time += int((second-int(second/3600)*3600)/60) + ":";
				if(second%60<10)
					time += "0";
				time += int(second%60) ;
			}
			
			return time;
		}
		
		/**
		 * 将秒转换为短时间
		 */
		public static function convertSecondToShortTime(second:Number):String{
			
			var time:String = "";

			if(second>=3600){
				time += int(second/3600) + "时";
				time += int((second-int(second/3600)*3600)/60) + "分";
				time += int(second%60) + "秒";
			}else if(second>=60){
				time += int(second/60) + "分";
				time += int(second%60) + "秒";
			}else if(second<60){
				time += int(second%60) + "秒";
			}
			
			return time;
		}

	}
}