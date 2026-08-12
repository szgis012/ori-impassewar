/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
    /**
     * 宝物信息
     *
     */
    [Bindable]
	public class TreasureItemVO implements IValueObject {
		
		/** 宝物编号 */
	    public var treasureID:int;
	    /** 宝物名称 */
	    public var name:String;
	    /** 宝物描述信息 */
	    public var description:String;
	    /** 宝物列表*/
	    public var category:int;
	    /** 宝物类型 */
	    public var type:int;
	    /** 宝物价格 */
	    public var cost:int;
	    /** 宝物图片地址 */
	    public var imgSrc:String;
    	/** 宝物的数量*/
    	public var num:int;
    	/** 是否可以购买(0.不可购买 1.可购买) */
    	public var canBuy:int;
    	/** 直接使用提示(若宝物可直接使用则为空，否则该字段为直接使用时提示信息) */
    	public var directUseTooltip:String;
    	/** 宝物状态。TreasureStateConstant中定义*/
    	public var state:int;
    
	}
}