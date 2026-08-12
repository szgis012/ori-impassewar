package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	[RemoteClass(alias="com.war.domain.Defense")]
	[Bindable]
	public class DefenseVO  implements IValueObject
	{
		public function DefenseVO()
		{
		}
	/** 城防编号 */
	public var defenseID:int;
	/** 建筑名称 */
	public var  name:String;
	/** 图片 */
	public var  image:String;
	/** 描述 */
	public var  description:String;
	/** 当前级别对应的约束依赖 */
	public var  constraintDepend:Object; 
	  /** 下一等级对应的约束依赖  */
	    public var nextConstraintDepend:Object;
	}
}