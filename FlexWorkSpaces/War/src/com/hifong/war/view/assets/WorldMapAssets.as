package com.hifong.war.view.assets
{
	import mx.charts.chartClasses.InstanceCache;
	
	/**
	 *  世界地图使用的内嵌图像
	 */
	[Bindable]
	public class WorldMapAssets
	{
		//世界地图使用的内嵌图像
		[Embed(source="images/map/city.png")]
		public var cityAsset:Class;
		
		[Embed(source="images/map/stronghold.png")]
		public var strongholdAsset:Class;
		//================空地（0）=====================
		[Embed(source="images/map/land.png")]
		public var landAsset:Class;
		 
		[Embed(source="images/map/land_1.png")]
		public var land1Asset:Class;
		
		[Embed(source="images/map/land_2.png")]
		public var land2Asset:Class;
		
		[Embed(source="images/map/land_3.png")]
		public var land3Asset:Class;
		
		[Embed(source="images/map/land_4.png")]
		public var land4Asset:Class;
		
		[Embed(source="images/map/land_5.png")]
		public var land5Asset:Class;
        //==============林地（3）==========================
        [Embed(source="images/map/holt_1.png")]
		public var holt1Asset:Class;
		
		[Embed(source="images/map/holt_2.png")]
		public var holt2Asset:Class;
		
		[Embed(source="images/map/holt_3.png")]
		public var holt3Asset:Class;
		
		[Embed(source="images/map/holt_4.png")]
		public var holt4Asset:Class;
		
		[Embed(source="images/map/holt_5.png")]
		public var holt5Asset:Class;
		
		//=============铁矿（4）==========================
		[Embed(source="images/map/hill_1.png")]
		public var hill1Asset:Class;
		
		[Embed(source="images/map/hill_2.png")]
		public var hill2Asset:Class;
		
		[Embed(source="images/map/hill_3.png")]
 		public var hill3Asset:Class;
		[Embed(source="images/map/hill_4.png")]
 		public var hill4Asset:Class;
		[Embed(source="images/map/hill_5.png")]
 		public var hill5Asset:Class;

		//=============油井（5）==========================
		[Embed(source="images/map/oil_1.png")]
		public var oilWell1Asset:Class;
		[Embed(source="images/map/oil_2.png")]
		public var oilWell2Asset:Class;
		[Embed(source="images/map/oil_3.png")]
		public var oilWell3Asset:Class;
		//=============麦田（6）==========================
		[Embed(source="images/map/wheat_1.png")]
		public var wheat1Asset:Class;
		[Embed(source="images/map/wheat_2.png")]
		public var wheat2Asset:Class;
		[Embed(source="images/map/wheat_3.png")]
		public var wheat3Asset:Class;
		[Embed(source="images/map/wheat_4.png")]
		public var wheat4Asset:Class;
		[Embed(source="images/map/wheat_5.png")]
		public var wheat5Asset:Class;
		//=============海洋（7）==========================
		[Embed(source="images/map/sea.png")]
		public var seaAsset:Class;
		
//		[Embed(source="images/map/lake_2.png")]
//		public var lake2Asset:Class;
//		
//		[Embed(source="images/map/lake_3.png")]
//		public var lake3Asset:Class;
		//=============海岸（8）==========================
		[Embed(source="images/map/sea_land_1.png")]
		public var seaLand1Asset:Class;
		
		[Embed(source="images/map/sea_land_2.png")]
		public var seaLand2Asset:Class;
		
		[Embed(source="images/map/sea_land_3_1.png")]
		public var seaLand3_1Asset:Class;
		[Embed(source="images/map/sea_land_3_2.png")]
		public var seaLand3_2Asset:Class;
		[Embed(source="images/map/sea_land_3_3.png")]
		public var seaLand3_3Asset:Class;
//		[Embed(source="images/map/sea_land_3_4.png")]
//		public var seaLand3_4Asset:Class;
//		[Embed(source="images/map/sea_land_3_5.png")]
//		public var seaLand3_5Asset:Class;
		
		[Embed(source="images/map/sea_land_4_1.png")]
		public var seaLand4_1Asset:Class;
		[Embed(source="images/map/sea_land_4_2.png")]
		public var seaLand4_2Asset:Class;
		[Embed(source="images/map/sea_land_4_3.png")]
		public var seaLand4_3Asset:Class;
//		[Embed(source="images/map/sea_land_4_4.png")]
//		public var seaLand4_4Asset:Class;
//		[Embed(source="images/map/sea_land_4_5.png")]
//		public var seaLand4_4Asset:Class; 
		
		//=============野怪（11）==========================
		[Embed(source="images/map/monster/1.png")]
		public var monster1Asset:Class;
		
		[Embed(source="images/map/monster/2.png")]
		public var monster2Asset:Class;
		
		[Embed(source="images/map/monster/3.png")]
		public var monster3Asset:Class;
		
		[Embed(source="images/map/monster/4.png")]
		public var monster4Asset:Class;
		
		[Embed(source="images/map/monster/5.png")]
		public var monster5Asset:Class;
		
		[Embed(source="images/map/monster/6.png")]
		public var monster6Asset:Class;
		
		[Embed(source="images/map/monster/7.png")]
		public var monster7Asset:Class;
		
		[Embed(source="images/map/monster/8.png")]
		public var monster8Asset:Class;
		
		[Embed(source="images/map/monster/9.png")]
		public var monster9Asset:Class;
		
		//野地背景图片
		[Embed(source="images/map/bg.png")]
		public var backgroundImage:Class;	 
		 
		private static var instance:WorldMapAssets;
		public static function getInstance():WorldMapAssets{
			if(instance==null) instance=new WorldMapAssets;
			return instance;
		}	 
		//地图上的贴图元素
		public var terrainImages:Object =  {
						"0_0":land1Asset,"0_1":land1Asset,"0_2":land2Asset,"0_3":land3Asset,"0_4":land4Asset,"0_5":land5Asset,
						"1_1":cityAsset, 
						"2_2":strongholdAsset,
						"3_31":holt1Asset,"3_32":holt2Asset,"3_33":holt3Asset,"3_34":holt4Asset,"3_35":holt5Asset,
						"4_41":hill1Asset,"4_42":hill2Asset,"4_43":hill3Asset,"4_44":hill4Asset,"4_45":hill5Asset,
						"5_51":oilWell1Asset,"5_52":oilWell2Asset,"5_53":oilWell3Asset,"5_54":oilWell3Asset,"5_55":oilWell3Asset, 
						"6_61":wheat1Asset,"6_62":wheat2Asset,"6_63":wheat3Asset,"6_64":wheat4Asset,"6_65":wheat5Asset,
						"7_101":seaAsset,"7_102":seaAsset,"7_103":seaAsset,"7_104":seaAsset,"7_105":seaAsset,
						"8_111":seaLand1Asset,"8_112":seaLand1Asset,"8_113":seaLand1Asset,"8_114":seaLand1Asset,"8_115":seaLand1Asset, 
						"8_121":seaLand2Asset,"8_122":seaLand2Asset,"8_123":seaLand2Asset,"8_124":seaLand2Asset,"8_125":seaLand2Asset,
						"8_131":seaLand3_1Asset,"8_132":seaLand3_2Asset,"8_133":seaLand3_3Asset,"8_134":seaLand3_1Asset,"8_135":seaLand3_1Asset,
						"8_141":seaLand4_1Asset,"8_142":seaLand4_2Asset,"8_143":seaLand4_3Asset,"8_144":seaLand4_1Asset,"8_145":seaLand4_1Asset,
						"11_1":monster1Asset,"11_2":monster2Asset,"11_3":monster3Asset,"11_4":monster4Asset,"11_5":monster5Asset,"11_6":monster6Asset,"11_7":monster7Asset,"11_8":monster8Asset,"11_9":monster9Asset
						 }; 
		//地图上的野怪图片 
		public var monsterImages:Array= [monster1Asset,monster2Asset,monster3Asset,monster4Asset,monster5Asset,monster6Asset,monster7Asset,monster8Asset,monster9Asset] ;
			 

	}
}