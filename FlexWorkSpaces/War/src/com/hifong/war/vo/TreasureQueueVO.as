package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.TreasureQueue")]
	[Bindable]
	public class TreasureQueueVO implements IValueObject
	{
	    /** 编号 */
	    public var treasureQueueID:int;
	    /** 城市编号 */
	    public var cityID:int;
	    /** 宝物类别，同宝物表定义 */
	    public var category:int;
	    /** 宝物类型，同宝物表定义 */
	    public var type:int;
	    /** 宝物效果结束时间 */
	    public var finishTime:Date;
	}
}