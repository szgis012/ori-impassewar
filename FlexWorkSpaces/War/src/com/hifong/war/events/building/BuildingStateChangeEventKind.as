package com.hifong.war.events.building
{
	/**
	 * 建筑状态改变事件类别
	 * 
	 */ 
	public class BuildingStateChangeEventKind
	{
		//建筑状态改变时触发的事件
		public static const STATE_CHANGED:int = 1;
		
		//取消建造时发生的事件
		public static const CANCEL_BUILD:int = 2;
		
		//升级或者拆除结束时发生的事件
		public static const PROCESS_END:int = 3;
		
		//当数据更新时发生的事件
		public static const UPDATE_DATA:int = 4;
		
		//数据已被删除
		public static const DELETED_DATA:int = 5;
	}
}