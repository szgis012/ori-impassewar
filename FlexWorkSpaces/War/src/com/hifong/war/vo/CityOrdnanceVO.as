/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.CityOrdnance")]
    [Bindable]
	public class CityOrdnanceVO implements IValueObject {
		/** 编号 */
	    public var cityOrdnanceID:int;
	    /** 军械编号 */
	    public var ordnanceID:int;
	    /** 城市编号 */
	    public var cityID:int;
	    /** 军械数量 */
	    public var num:int;
	}
}