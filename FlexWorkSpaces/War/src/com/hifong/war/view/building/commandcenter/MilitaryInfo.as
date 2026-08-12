package com.hifong.war.view.building.commandcenter
{
	/**
	 * 兵力信息
	 */
	[Bindable]
	public class MilitaryInfo
	{
		/** 兵种编号*/
		public var armyID:int;
		/** 兵种名称 */
		public var armyName:String;
		/** 兵种数量 */
		public var num:int;
		/** 图片 */
		public var imgsrc:String;
		
		/** 类型：0表示空格子 1表示未编制军队，2表示已编制军队 */
		public var type:int = 0;
			
		public function MilitaryInfo()
		{
		}
	
		public function clone():MilitaryInfo{
			var mi:MilitaryInfo = new MilitaryInfo();
			mi.armyID = this.armyID;
			mi.armyName = this.armyName;
			mi.num = this.num;
			mi.imgsrc = this.imgsrc;
			mi.type = this.type;
			
			return mi;
		}
	}
}