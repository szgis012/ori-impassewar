package com.hifong.war.common
{
	import com.hifong.war.vo.CityVO;
	
	[Bindable]
	public class CityInfo
	{
		public var city:CityVO;
		
		
		public function CityInfo()
		{
		}
		
		/** 城市编号 */
		public function get cityID():int{
			return city.cityID;
		}
		
		/** X坐标 */
		public function get posX():int{
			return city.posX;
		}
		
		/** Y坐标 */
		public function get posY():int{
			return city.posY;
		}
		
		/** 城市名称 */
		public function get cityName():String{
			return city.name;
		}
		
		public function set cityName(value:String):void{
			city.name= value;
		}

		/** 闲人数量 */
		public var idlerNum:int = 200;
//		public function get idlerNum():int{
//			return city.idlerNum;
//		}
		
		
		/** 空余人口 (空余人口可用空间)*/
		public var freePopulation:int = 10000;
//		public function get freePopulation():int{
//			return city.freePopulation;
//		}
		
		/** 税收(百分比)*/
		public function get tax():int{
			return city.tax;
		}
		
		public function set tax(value:int):void{
			city.tax = value;
		}
		
//		public var tax:int = 10;
		
		/** 治安状况*/
		public function get security():int{
			return city.security;
		}
		
		public function set security(value:int):void{
			 city.security = value;
		}
		
//		public var security:int = 90;
		
		/** 城市的金钱数量*/
		public function get moneyNum():int{
			return city.moneyNum;
		}
		
		public function set moneyNum(value:int):void{
			city.moneyNum = value;
		}
		
		/** 城市的金钱每小时收益*/
		public function get moneyOutput():int{
			return city.moneyOutput;
		}
		
		public function set moneyOutput(value:int):void{
			city.moneyOutput = value;
		}
		
		 /** 木材数量 */
		 public function get woodNum():int{
			return city.woodNum;
		}
		
		public function set woodNum(value:int):void{
			city.woodNum= value;
		}
		
	   
	    /** 木材厂当前工作人数 */
	    public var woodWorkerNum:int = 100;
//	     public function get woodWorkerNum():int{
//			return city.woodWorkerNum;
//		}
	    
	    /** 木材产量 */
	    public function get woodOutput():int{
			return city.woodOutput;
		}
		
		public function set woodOutput(value:int):void{
			city.woodOutput = value;
		}
	    
	    
	    /** 食物数量 */
	    public function get foodNum():int{
			return city.foodNum;
		}
		
		public function set foodNum(value:int):void{
			city.foodNum = value;
		}
	    
	    /** 磨坊当前工作人数 */
	    public var foodWorkerNum:int = 200;
//	    public function get foodWorkerNum():int{
//			return city.foodWorkerNum;
//		}
	    
	    /** 食物产量 */
	    public function get foodOutput():int{
			return city.foodOutput;
		}
		
		public function set foodOutput(value:int):void{
			city.foodOutput = value;
		}

		/** 钢铁数量 */
		public function get steelNum():int{
			return city.steelNum;
		}
		
		public function set steelNum(value:int):void{
			city.steelNum = value;
		}
	    
	    /** 钢铁厂当前工作人数 */
	    public var steelWorkerNum:int = 200;
//	     public function get steelWorkerNum():int{
//			return city.steelWorkerNum;
//		}
	    
	    /** 钢铁产量 */
	    public function get steelOutput():int{
			return city.steelOutput;
		}
		
		public function set steelOutput(value:int):void{
			city.steelOutput = value;
		}
	    
	    /** 石油数量 */
	    public function get oilNum():int{
			return city.oilNum;
		}
		
		public function set oilNum(value:int):void{
			city.oilNum = value;
		}
	    
	    /** 油田当前工作人数 */
	    public var oilWorkerNum:int = 100;
//	     public function get oilWorkerNum():int{
//			return city.oilWorkerNum;
//		}
	    
	    /** 石油产量 */
	    public function get oilOutput():int{
			return city.oilOutput;
		}
		
		public function set oilOutput(value:int):void{
			city.oilOutput = value;
		}
		
	    
	    /** 新兵数量 */
	    public var recruitNum:int = 200;
	    
	}
}