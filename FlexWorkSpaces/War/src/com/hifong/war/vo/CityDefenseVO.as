/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	
	/**
	 * 
	 * 城市防御信息
	 */ 
    [RemoteClass(alias="com.war.domain.CityDefense")]
    [Bindable]
	public class CityDefenseVO implements IValueObject
	{
		
		/** 城市防御编号 */
		public var cityDefenseID:int;
		
	    /** 城市编号 */
	    public var cityID:int;
	    /** 防御类型：CityDefenseTypeConstant中定义 */
	    public var defenseID:int;
	    /** 数量 */
	    public var num:int;
	    
	    /** 城市防御编号 */
	    public var cityDefenseNO:int;
		/** X坐标 */
	    public var posX:int;
	    /** Y坐标 */
	    public var posY:int;
	    /** 是否已经攻击(0.否 1.否) */
	    public var haveAttacked:int;
	    
	}
}