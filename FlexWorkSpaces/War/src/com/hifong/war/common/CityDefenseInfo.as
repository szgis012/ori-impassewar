package com.hifong.war.common
{
	import com.hifong.war.vo.CityDefenseVO;
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 城防信息
	 */ 
	 [Bindable]
	public class CityDefenseInfo
	{
		/** 城防信息列表，列表中的元素为CityDefenseVO*/
		public var cityDefenseList:ArrayCollection;
		/** 城防信息Map，其中key为防御类型(CityDefenseTypeConstant中定义)，value为CityDefenseVO*/
		public var cityDefenseMap:Object ;
		/** 军械生产进程列表,其中的元素为ProductionProcessVO */
		public var defenseProcessList:ArrayCollection;
		/** 最大等待队列的长度(这里限制为6个队列)*/
		public var maxWaitQueueNum:int = 6;
		
		public function CityDefenseInfo()
		{
		}
		
		//获得指定编号的城防信息
		public function getCityDefense(cityDefenseID:int):CityDefenseVO{
			var cd:CityDefenseVO;
			
			for each(cd in cityDefenseList){
				if(cd.cityDefenseID == cityDefenseID){
					return cd;
				}
			}
			
			return null;
		}

	}
}