/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.TradeQueue")]
	[Bindable]
	public class TradeQueueVO implements IValueObject {
		
		/** 交易队列编号 */
	    public var tradeQueueID:Number;
	    /** 城市编号 */
	    public var cityID:Number;
	    /** 目标城市编号 */
	    public var targetCityID:Number;
	    /** 目标编号 */
	    public var targetID:Number;
	    /** 交易类型(1.资源 2.军械 3.宝物 4.运输) */
	    public var type:Number;
	    /** 商人数量 */
	    public var businessmanNum:Number;
	    /** 到达时间 */
	    public var arriveTime:Date;
		
	}
}