/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.view.assets
{
	import mx.collections.ArrayCollection;
	
	/**
	 * 游戏使用的图片资源
	 * 
	 */ 
	public class WarAssets
	{
		//内嵌城市空地使用的图像
		[Embed(source="images/building/space.png")]
		public static var blankAsset:Class;
		
		//内嵌城市背景图像
		[Embed(source="images/building/bg.jpg")]
		public static var cityBackgroundAsset:Class;
	   
	    //热卖图片
		[Bindable]
		[Embed(source="images/rm.png")]
		public static var hotSale:Class;
		
		//特价图片
		[Bindable]
		[Embed(source="images/tj.png")]
		public static var specailPrice:Class;
		
		//窗口标题下方的横向图片
		[Bindable]
		[Embed(source="images/titleline.png",scaleGridLeft="56",scaleGridTop="11",scaleGridRight="185",scaleGridBottom="24")]
		public static var titleLine:Class;
		//聊天背景图片
		[Bindable]  
		[Embed(source="images/chat_bg.png")]
		public static var chatBG:Class; 
		//场景底部背景图片
		[Bindable]
		[Embed(source="images/bg_bottom.png")]
		public static var bgBottom:Class; 
		/**===============================时间图片数字======================== */
		[Bindable]
		[Embed(source="images/battle/time_0.png")]
		public static var time0:Class; 
		[Bindable]
		[Embed(source="images/battle/time_1.png")]
		public static var time1:Class; 
		[Bindable]
		[Embed(source="images/battle/time_2.png")]
		public static var time2:Class; 
		[Bindable]
		[Embed(source="images/battle/time_3.png")]
		public static var time3:Class; 
		[Bindable]
		[Embed(source="images/battle/time_4.png")]
		public static var time4:Class; 
		[Bindable]
		[Embed(source="images/battle/time_5.png")]
		public static var time5:Class; 
		[Bindable]
		[Embed(source="images/battle/time_6.png")]
		public static var time6:Class; 
		[Bindable]
		[Embed(source="images/battle/time_7.png")]
		public static var time7:Class; 
		[Bindable]
		[Embed(source="images/battle/time_8.png")]
		public static var time8:Class; 
		[Bindable]
		[Embed(source="images/battle/time_9.png")]
		public static var time9:Class; 
		//[Bindable]
		//public static var TIME_NUM:ArrayCollection=new ArrayCollection([time0,time1,time2,time3,time4,time5,time6,time7,time8,time9]);
		public function WarAssets()
		{
		}

	}
}