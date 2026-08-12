/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.CityInfo")]
	[Bindable]
	public class CityInfoVO implements IValueObject {

	    /** 城市编号 */
	    public var cityID:Number;
	    /** 玩家编号 */
	    public var playerID:Number;
	    /** 玩家名称 */
	    public var playerName:String;
	    /** 国家 */
	    public var country:Number;
	    /** 城市名称 */
	    public var name:String;
	    /** X坐标 */
	    public var posX:Number;
	    /** Y坐标 */
	    public var posY:Number;
	    /** 建筑点数 */
	    public var constructionPoint:Number;
	    /** 科技点数 */
	    public var technologyPoint:Number;
	    /** 人口 */
	    public var population:Number;
	    /** 执政官 */
	    public var officer:Number;
	    /** 留守军队 */
	    public var defensiveMilitary:Number;
	    
	}
}