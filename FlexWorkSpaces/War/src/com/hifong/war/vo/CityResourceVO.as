package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.CityResource")]
	[Bindable]
	public class CityResourceVO implements IValueObject
	{
		/** 城市编号 */
	public var cityID:int;
	/** 资源数量上限 */
	public var  resourceNumMax:Number; 
	/** 木材数量 */
	public var  woodNum:Number;
	/** 木材数量上限 */
	public var  woodNumMax:Number;
	/** 木材产量 */
	public var woodOutput:Number;
	/** 木材工人数量 */
	public var woodWorkerNum:int;
	/** 木材建筑加成 */
	public var woodBuildingAdd:int;
	/** 木材科技加成 */
	public var woodTechAdd:int;
	/** 木材野地加成 */
	public var woodFieldAdd:int;
	/** 木材执行官加成 */
	public var woodOfficerAdd:int;
	/** 木材军团加成 */
	public var woodGuildAdd:int;
	/** 木材宝物加成 */
	public var woodTreasureAdd:int;
	/** 钢铁数量 */
	public var steelNum:Number;
	/** 钢铁数量上限 */
	public var steelNumMax:Number;
	/** 钢铁产量 */
	public var steelOutput :Number;
	/** 钢铁工人数量 */
	public var steelWorkerNum:int;
	/** 钢铁建筑加成 */
	public var steelBuildingAdd:int;
	/** 钢铁科技加成 */
	public var steelTechAdd : int;
	/** 钢铁野地加成 */
	public var steelFieldAdd :int ;
	/** 钢铁执政官加成 */
	public var steelOfficerAdd:int;
	/** 钢铁军团加成 */
	public var steelGuildAdd:int;
	/** 钢铁宝物加成 */
	public var steelTreasureAdd:int;
	/** 石油数量 */
	public var oilNum:Number;
	/** 石油数量上限 */
	public var oilNumMax:Number;
	/** 石油产量 */
	public var oilOutput:Number;
	/** 石油工人数量 */
	public var oilWorkerNum:int;
	/** 石油建筑加成 */
	public var oilBuildingAdd:int ;
	/** 石油科技加成 */
	public var oilTechAdd:int;
	/** 石油野地加成 */
	public var oilFieldAdd:int;
	/** 石油执政官加成 */
	public var oilOfficerAdd:int;
	/** 石油军团加成 */
	public var oilGuildAdd:int;
	/** 石油宝物加成 */
	public var oilTreasureAdd:int;
	/** 石油消耗 */
	public var oilConsume:Number;
	/** 食物数量 */
	public var foodNum:Number;
	/** 食物数量上限 */
	public var foodNumMax:Number;
	/** 食物产量 */
	public var foodOutput:Number;
	/** 食物工人数量 */
	public var foodWorkerNum:int;
	/** 食物建筑加成 */
	public var foodBuildingAdd:int;
	/** 食物科技加成 */
	public var foodTechAdd:int;
	/** 食物野地加成 */
	public var foodFieldAdd:int;
	/** 食物执政官加成 */
	public var foodOfficerAdd:int;
	/** 食物军团加成 */
	public var foodGuildAdd:int;
	/** 食物宝物加成 */
	public var foodTreasureAdd:int;
	/** 食物消耗 */
	public var foodConsume:Number;
	/** 金钱数量 */
	public var moneyNum:Number;
	/** 金钱数量上限 */
	public var moneyNumMax:Number;
	/** 金钱产量 */
	public var moneyOutput:Number;
	/** 资源科技加成 */
	public var moneyTechAdd:int;
	/** 资源野地加成 */
	public var moneyFieldAdd:int;
	/** 金钱执政官加成 */
	public var moneyOfficerAdd:int;
	/** 金钱军团加成 */
	public var moneyGuildAdd:int;
	/** 金钱宝物加成 */
	public var moneyTreasureAdd:int;
	/** 金钱消耗 */
	public var moneyConsume:Number;
	}
}