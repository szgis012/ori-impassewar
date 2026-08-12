/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.ProcessQueue")]
    [Bindable]
	public class ProcessQueueVO implements IValueObject {
		/** 队列编号 */
	    public var processQueueID:int;
	    /** 城市编号 */
	    public var cityID:int;
	    /** 目标编号 */
	    public var targetID:int;
	    /** 类型(1.建筑物建造或升级 2. 科技升级 3.生产士兵,4拆除建筑) */
	    public var type:int;
	    /** 开始时间 */
	    public var startTime:Date;
	    /** 结束时间 */
	    public var finishTime:Date;
	}
}