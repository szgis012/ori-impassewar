/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.City")]
	[Bindable]
	public class CityVO implements IValueObject {

		/** 城市编号 */
		public var cityID:int;
		/** 玩家编号 */
		public var playerID:int;
		/** 地图编号 */
		public var mapID:int;
		/** X坐标 */
		public var posX:int;
		/** Y坐标 */
		public var posY:int;
		/** 城市名称 */
		public var name:String;
		/** 城市状态(0.新手 1.正常 2.免战 3.封停) */
		public var state:int;
		/** 建筑点数 */
		public var constructionPoint:Number;
		/** 科技点数 */
		public var technologyPoint:Number;
		/** 空闲人口 */
		public var  populationFree:Number;
		/** 当前总人口 */
		public var  populationTotal:Number;
		/** 人口上限 */
		public var  populationMax:Number;
		/** 新兵数量 */
		public var recruitNum:int;
		/** 税收 */
		public var tax:int;
		/** 治安 */
		public var security:int;
		/** 执政官 */
		public var officer:int;
		/** 留守军队 */
		public var defensiveMilitary:int;
		/** 空闲商人数量 */
		public var businessmanFree:int;
		/** 创建时间 */
		public var createTime:Date
	
		/** 城市资源*/
		public var cityResource:CityResourceVO;

	}
}