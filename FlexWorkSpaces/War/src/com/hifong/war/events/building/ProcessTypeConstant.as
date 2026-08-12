package com.hifong.war.events.building
{
	/**
	 * 进程类型常量
	 * 
	 */ 
	public class ProcessTypeConstant
	{
		/** 建筑物建造或升级 */
		public static const PROCESS_BUILD_UPGRADE:int = 1;
		
		/** 科技升级 */
		public static const PROCESS_TECH_UPGRADE:int = 2;
		
		/** 训练士兵 */
		public static const PROCESS_TRAIN_SOLDIER:int = 3;
		
		/** 拆除建筑 */
		public static const PROCESS_BACKOUT_BUILDING:int = 4;
	
		public function ProcessTypeConstant()
		{
		}

	}
}