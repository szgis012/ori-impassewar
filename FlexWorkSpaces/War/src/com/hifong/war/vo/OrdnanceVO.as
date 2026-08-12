/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.Ordnance")]
	[Bindable]
	public class OrdnanceVO implements IValueObject {
		/** 军械编号 */
	    public var ordnanceID:int;
	    /** 约束依赖编号 */
	    public var constraintDependID:int;
	    /** 军械名称 */
	    public var name:String;
	    /** 军械图片 */
	    public var image:String;
	    /** 军械描述信息 */
	    public var description:String;
	    /** 军械类型: 1.枪械 2.弹药 3.车体 4.机身 */
	    public var type:int;
	    /** 对应的约束依赖对象,对应服务端的ConstraintDepend*/
	    public var constraintDepend:Object;
	     
	}
}