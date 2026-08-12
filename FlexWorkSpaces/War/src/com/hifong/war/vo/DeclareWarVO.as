/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.DeclareWar")]
	[Bindable]
	public class DeclareWarVO implements IValueObject {
		
		/** 宣战编号 */
	    public var declareWarID:int;
	    /** 玩家编号 */
	    public var playerID:int;
	    /** 目标玩家编号 */
	    public var targetPlayerID:int;
	    /** 战争开始时间 */
	    public var  startTime:Date;
	    /** 战争结束时间 */
	    public var  finishTime:Date;
	    
	}
}