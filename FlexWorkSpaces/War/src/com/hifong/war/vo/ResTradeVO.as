/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.ResTrade")]
	[Bindable]
	public class ResTradeVO implements IValueObject {
		
		/** 资源交易编号 */
	    public var resTradeID:Number;
	    /** 卖家城市编号 */
	    public var cityID:Number;
	    /** 卖家城市X坐标 */
	    public var cityPosX:Number;
	    /** 卖家城市Y坐标 */
	    public var cityPosY:Number;
	    /** 资源类型(1.木材 2.钢铁 3.石油 4.食物) */
	    public var resourceType:Number;
	    /** 数量 */
	    public var amount:Number;
	    /** 价格 */
	    public var price:Number;
	    /** 最长交易时间 */
	    public var maxTime:Number;
	    /** 是否只允许盟友交易 */
	    public var isAllyOnly:Number;
	    /** 状态(1.正常 2.交易中) */
	    public var state:Number;
	    /** 城市信息 */
	    public var cityInfo:CityInfoVO;
	    /** 目标城市信息 */
	    public var targetCityInfo:CityInfoVO;

	}
}