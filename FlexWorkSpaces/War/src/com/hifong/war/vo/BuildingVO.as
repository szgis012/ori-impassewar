/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.Building")]
    [Bindable]
	public class BuildingVO implements IValueObject {
		/** 建筑编号 */
	    public var buildingID:int;
	    /** 约束依赖编号 */
	    public var constraintDependID:int;
	    /** 建筑名称 */
	    public var name:String;
	    /** 图片 */
	    public var image:String;
	    /** 最高等级 */
	    public var maxLevel:int;
	    /** 描述 */
	    public var description:String;
	    /** 是否唯一(1.是 2.否) */
	    public var isOnlyone:int;
	    /** 当前级别对应的约束依赖 ,ConstraintDepend*/
	    public var constraintDepend:Object;
	    /** 下一等级对应的约束依赖  */
	    public var nextConstraintDepend:Object;
	}
}