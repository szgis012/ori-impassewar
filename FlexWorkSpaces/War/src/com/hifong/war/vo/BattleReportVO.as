package com.hifong.war.vo
{
	/**
	 * 战斗报告
	 */
	import com.adobe.cairngorm.vo.IValueObject;
	[Bindable]
	public class BattleReportVO implements IValueObject
	{
		/** 战斗ID*/
		public var battleId:int;
		/** 战斗类型*/
		public var battleType:String;
		/** 战斗概述*/
		public var battleDetail:String;
		/** 胜利者 0为进攻方 1为防守方*/
		public var winner:int;
		/** 进攻方ID */
		public var attackId:int;
		/** 进攻方昵称 */
		public var attackName:String;		
		/** 进攻方头像 */
		public var attackImage:String;
		/** 进攻方等级*/
		public var attackLevel:int;
		/** 防守方ID */
		public var defendId:int;
		/** 防守方昵称 */
		public var defendName:String;
		/** 防守方头像*/
		public var defendImage:String;
		/** 防守方等级 */
		public var defendLevel:int;
		/** 获得木头 */
		public var winWood:int;
		/** 获得石油 */
		public var winOil:int;
		/** 获得钢铁*/
		public var winSteel:int;
		/** 获得食物 */
		public var winFood:int;
		/** 获得金钱 */
		public var winMoney:int;
		/** 获得声望*/
		public var winRepute:int;
		
		/** 获得宝物 */
		public var winTreasure:int;
		/** 获得道具*/
		public var winProp:int;
		
		/** 进攻参战部队 */
		public var attackArmy:String;
		/** 防守方参战部队*/
		public var defendArmy:String;
		/** 进攻方丢失部队情况 */
		public var attackLoseArmy:String;
		/** 防守方丢失部队情况*/
		public var defendLoseArmy:String;
		 
		public function BattleReportVO()
		{
		}

	}
}