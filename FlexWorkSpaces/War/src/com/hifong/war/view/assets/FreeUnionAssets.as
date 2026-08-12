package com.hifong.war.view.assets
{
	import mx.modules.ModuleBase;

	/**
	 * 自由联邦相关的图形
	 * 
	 */ 
	public class FreeUnionAssets extends ModuleBase implements ICountryRelatedAssets
	{
		//自由联邦建筑图片
		[Embed(source="images/building/1/1.png")]
		private var building1:Class; 
		[Embed(source="images/building/1/2.png")]
		private var building2:Class; 
		[Embed(source="images/building/1/3.png")]
		private var building3:Class; 
		[Embed(source="images/building/1/4.png")]
		private var building4:Class; 
		[Embed(source="images/building/1/5.png")]
		private var building5:Class; 
		[Embed(source="images/building/1/6.png")]
		private var building6:Class; 
		[Embed(source="images/building/1/7.png")]
		private var building7:Class; 
		[Embed(source="images/building/1/8.png")]
		private var building8:Class; 
		[Embed(source="images/building/1/9.png")]
		private var building9:Class; 
		[Embed(source="images/building/1/10.png")]
		private var building10:Class; 
		[Embed(source="images/building/1/11.png")]
		private var building11:Class; 
		[Embed(source="images/building/1/12.png")]
		private var building12:Class; 
		[Embed(source="images/building/1/13.png")]
		private var building13:Class; 
		[Embed(source="images/building/1/14.png")]
		private var building14:Class; 
		[Embed(source="images/building/1/15.png")]
		private var building15:Class; 
		[Embed(source="images/building/1/16.png")]
		private var building16:Class; 
		[Embed(source="images/building/1/17.png")]
		private var building17:Class; 
		[Embed(source="images/building/1/18.png")]
		private var building18:Class; 
		[Embed(source="images/building/1/19.png")]
		private var building19:Class; 
		[Embed(source="images/building/1/20.png")]
		private var building20:Class;
		//所有建筑的列表
		private var buildingImages:Array = [null,building1,building2,building3,building4,building5
					,building6,building7,building8,building9,building10
					,building11,building12,building13,building14,building15
					,building16,building17,building18,building19,building20];
			
		/**
		 * 得到指定编号建筑的图片类
		 */ 
		public function getBuildingImageClass(buildingID:int):Class{
			return buildingImages[buildingID] as Class;
		}
		
		/**
		 * 得到指定编号建筑的图片资源
		 */ 
		public function getBuildingImageSource(buildingID:int):Object{
			var cls:Class = getBuildingImageClass(buildingID);
			
			if(cls){
				return new cls();
			}else{
				return null;
			}	
		}
	}
}