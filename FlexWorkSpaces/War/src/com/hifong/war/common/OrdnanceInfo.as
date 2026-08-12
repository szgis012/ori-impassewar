package com.hifong.war.common
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.events.building.armory.FinishProduceOrdnanceEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetOrdnanceListEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.vo.CityOrdnanceVO;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import flash.events.TimerEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.CollectionEvent;
	import mx.events.CollectionEventKind;
	
	/**
	 * 军械相关信息
	 */ 
	[Bindable]
	public class OrdnanceInfo
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		/** 所有军械信息列表 ,列表中的元素为OrdnanceVO */
		public var ordnanceList:ArrayCollection;
		/** 方便通过军械编号找到对应的军械信息。key=ordnanceID,value=OrdnanceVO */
		public var ordnanceMap:Object;
		/** 枪械 */
		public var firearmsList:ArrayCollection;
		/** 弹药 */
		public var ammoList:ArrayCollection;
		/** 车体 */
		public var truckbodyList:ArrayCollection;
		/** 机身 */
		public var airframeList:ArrayCollection;
		
		/** 城市现有的军械信息列表,列表中的元素为CityOrdnanceVO*/
		public var cityOrdnanceList:ArrayCollection;
		/** 方便通过军械编号找到对应的城市现有的军械信息。key=ordnanceID,value=CityOrdnanceVO */
		public var cityOrdnanceMap:Object;
		/** 方便通过军械编号找到对应的城市现有的军械信息。key=cityOrdnanceID,value=CityOrdnanceVO */
		public var cityOrdnanceMap2:Object;
		
		/** 军械生产进程列表,其中的元素为ProductionProcessVO */
		public var ordnanceProcessList:ArrayCollection;
		/** 最大军械生产等待队列值*/
		public var maxWaitQueueNum:int = 6;
		
		public function OrdnanceInfo(){
		}
		
	}
}