/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.ProductionQueue")]
    [Bindable]
	public class ProductionQueueVO implements IValueObject {
		/** 队列编号 */
		public var productionQueueID:int;
	    /** 城市编号 */
		public var cityID:int;
	    /** 目标编号 */
		public var targetID:int;
	    /** 类型 */
		public var type:int;
	    /** 数量 */
		public var amount:int;
		/** 开始时间 */
		public var startTime:Date;
	    /** 结束时间 */
		public var finishTime:Date;
	}
}