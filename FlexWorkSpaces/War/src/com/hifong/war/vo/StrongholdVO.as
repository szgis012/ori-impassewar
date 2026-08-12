/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.Stronghold")]
    /**
     * 要塞信息
     *
     */	
	public class StrongholdVO implements IValueObject {
		/** 要塞编号 */
	    public var strongholdID:int;
	    /** 要塞所属玩家编号 */
	    public var playerID:int;
	    /** X坐标 */
	    public var posX:int;
	    /** Y坐标 */
	    public var posY:int;
	    /** 要塞名称 */
	    public var name:String;
	    /** 创建时间 */
	    public var createtime:Date;
	}
}