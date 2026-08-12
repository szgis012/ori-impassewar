package com.hifong.war.events.building
{
	/**
	 * 建筑建造状态
	 * 
	 */ 
	public class BuildingStateConstant
	{
		//建筑正常状态
		public static const STATE_NORMAL:int = 1;
		//建筑升级中
		public static const STATE_UPDATING:int = 2;
		//建筑拆除中
		public static const STATE_DESTROYING:int = 3;
		
		
		public function BuildingStateConstant()
		{
		}

	}
}