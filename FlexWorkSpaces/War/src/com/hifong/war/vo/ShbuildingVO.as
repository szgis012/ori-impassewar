/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.Shbuilding")]
    /**
     * 要塞建筑信息
     *
     */	
	public class ShbuildingVO implements IValueObject {
		/** 要塞建筑编号 */
	    public var shbuildingID:int;
	    /** 建筑名称 */
	    public var name:String;
	    /** 图片 */
	    public var image:String;
	    /** 最高等级 */
	    public var maxLevel:int;
	    /** 描述 */
	    public var  description:String;
	    /** 是否唯一(1.是 2.否) */
	    public var isonlyone:int;
	}
}