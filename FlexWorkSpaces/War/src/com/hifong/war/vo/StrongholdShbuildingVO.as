/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.StrongholdShbuilding")]
    /**
     * 要塞中的建筑信息
     *
     */	
	public class StrongholdShbuildingVO implements IValueObject {
		/** 编号 */
	    public var shShbuildingID:int;
	    /** 要塞编号 */
	    public var strongholdID:int;
	    /** 要塞建筑编号 */
	    public var shbuildingID:int;
	    /** 地图位置 */
	    public var position:int;
	    /** 建筑等级 */
	    public var level:int;
	    /** 状态(1.正常 2.建造中 3.拆除中) */
	    public var state:int;
	    /** 建筑*/
	    public var building:ShbuildingVO;
	}
}