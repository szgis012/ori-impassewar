/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 建筑状态改变事件
     *
     */
	public final class BuildingStateChangeEvent extends CairngormEvent
	{
		public static const BUILDINGSTATECHANGE_EVENT:String = "com.hifong.war.events.BuildingStateChangeEvent";
		
		public function BuildingStateChangeEvent(kind:int,source:Object=null,prop:Object=null,newValue:Object=null,oldValue:Object=null) 
		{
			super( BUILDINGSTATECHANGE_EVENT );
			this.kind = kind;
			this.source = source;
			this.property = prop;
			this.newValue = newValue;
			this.oldValue = oldValue;
		}
		
		/** 类别,BuildingStateChangeEvent类中定义 */
		public var kind:int;
		/** 改变后的值 */
		public var newValue:Object;
		/** 改变前的值 */
   		public var oldValue:Object;
		/** 改变的属性 */
   		public var property:Object;
		/** 改变的对象 */
	    public var source:Object;
    
    
	}
}
