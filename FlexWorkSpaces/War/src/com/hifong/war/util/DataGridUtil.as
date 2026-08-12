package com.hifong.war.util
{
	import mx.controls.dataGridClasses.DataGridColumn;
	
	public class DataGridUtil
	{
		public function DataGridUtil()
		{
		}

		/**
		 * 获得DataGrid复合对象属性值
		 */
		public static function getColumnProperty(item:Object, column:DataGridColumn):String{
			try{
				var propertyArray:Array = column.dataField.split(".");
				return item[propertyArray[0]][propertyArray[1]];
			}catch(e:Error){
				return "未知";
			}
			return "";
		}

	}
}