/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.ResTransportation")]
	[Bindable]
	public class ResTransportationVO implements IValueObject {
		
		/** 资源运输编号 */
	    public var resTransportationID:Number;
	    /** 木材数量 */
	    public var woodAmount:Number;
	    /** 钢铁数量 */
	    public var steelAmount:Number;
	    /** 石油数量 */
	    public var oilAmount:Number;
	    /** 食物数量 */
	    public var foodAmount:Number;
	    /** 金钱数量 */
	    public var moneyAmount:Number;

	}
}