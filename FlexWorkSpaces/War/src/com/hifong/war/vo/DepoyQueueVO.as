/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.DepoyQueue")]
    [Bindable]
	public class DepoyQueueVO implements IValueObject {
		/** 队列编号 */
	    public var depoyQueueID:int;
	    /** 出征部队编号 */
	    public var cityMilitaryID:int;
	    /** 目标地点 */
	    public var mapID:int;
	    /** 出征策略 */
	    public var policy:int;
	    /** 类型(1.侦察 2.攻击 3.派遣.) */
	    public var type:int;
	    /** 携带的食物数量 */
	    public var carryFood:int;
	    /** 携带的木头数量 */
	    public var carryWood:int;
	    /** 携带的石油数量 */
	    public var carryOil:int;
	    /** 携带的钢铁数量 */
	    public var carrySteel:int;
	    /** 携带的金钱数量 */
	    public var carryMoney:int;
	    /** 到达目的地的时间 */
	    public var  finishTime:Date;
	}
}