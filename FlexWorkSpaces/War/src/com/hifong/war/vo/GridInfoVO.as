package com.hifong.war.vo
{
	/**
	 * 世界地图上的一个单元格的信息
	 */ 
	[Bindable]
	public class GridInfoVO
	{
		/** 单元格编号 */
		public var gridID:int;
		/** 单元格名称 */
		public var name:String;
		/** 单元格所在的X坐标(Map坐标)*/
		public var mapx:int ;
		/** 单元格所在的Y坐标(Map坐标)*/
		public var mapy:int ;
		/** 单元格类型 */
		public var type:int ;
		/** 单元格状态 */
		public var state:int ;
		
		
		public function GridInfoVO()
		{
		}
	}
}