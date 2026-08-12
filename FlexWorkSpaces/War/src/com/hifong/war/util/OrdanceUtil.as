package com.hifong.war.util
{
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.vo.CityOrdnanceVO;
	
	public class OrdanceUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		
		/**
		 * 判断城内是否有足够的军械
		 * 如果城内的军械数量>=num返回true，否则返回false
		 */ 
		public static function hasEnoughOrdance(ordnanceID:int,num:int):Boolean
		{
			var co:CityOrdnanceVO = model.ordnanceInfo.cityOrdnanceMap[ordnanceID] as CityOrdnanceVO;
			
			if(co){
				return (co.num >= num);
			}
			
			return false;
		}

	}
}