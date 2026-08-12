package com.hifong.war.view.building.commandcenter
{
	/**
	 * 携带的资源
	 */ 
	 [Bindable]
	public class CarryResource
	{
		/** 携带的食物数量 */
	    public var carryFood:int = 0;
	    /** 携带的木头数量 */
	    public var carryWood:int = 0;
	    /** 携带的石油数量 */
	    public var carryOil:int = 0;
	    /** 携带的钢铁数量 */
	    public var carrySteel:int = 0;
	    /** 携带的金钱数量 */
	    public var carryMoney:int = 0;
	    
		public function CarryResource()
		{
		}

	}
}