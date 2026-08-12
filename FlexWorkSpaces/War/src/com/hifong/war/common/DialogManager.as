package com.hifong.war.common
{
	import com.hifong.war.constant.BuildingConstant;
	import com.hifong.war.constant.DefenseConstant;
	import com.hifong.war.util.WindowUtil;
	import com.hifong.war.view.building.BuildingGrid;
	import com.hifong.war.view.building.CreateBuildingWindow;
	import com.hifong.war.view.building.SimpleUpdateWindow;
	import com.hifong.war.view.building.airport.AirportWindow;
	import com.hifong.war.view.building.armory.ArmoryWindow;
	import com.hifong.war.view.building.barracks.BarracksWindow;
	import com.hifong.war.view.building.citycenter.CityCenterWindow;
	import com.hifong.war.view.building.commandcenter.CommandCenterWindow;
	import com.hifong.war.view.building.defense.CityDefenseWindow;
	import com.hifong.war.view.building.heavyfactory.HeavyFactoryWindow;
	import com.hifong.war.view.building.lumber.LumberWindow;
	import com.hifong.war.view.building.market.MarketWindow;
	import com.hifong.war.view.building.militarycollege.MilitaryCollegeWindow;
	import com.hifong.war.view.building.mill.MillWindow;
	import com.hifong.war.view.building.oilfield.OilFieldWindow;
	import com.hifong.war.view.building.steel.SteelWindow;
	import com.hifong.war.view.building.techcenter.TechCenterWindow;
	
	/**
	 * 对话框管理
	 * 
	 */ 
	public class DialogManager
	{
		/**
		 * 显示建筑升级对话框 
		 */
		public static function showUpgradeWindow(bg:BuildingGrid):void{
			//如果建筑等级小于1就只显示简单的升级窗口
			if(bg.cityBuilding.level < 1){
				showUpdateWindow(bg);
				return;
			}
			
			switch(bg.cityBuilding.buildingID){
				//城镇中心		
				case BuildingConstant.CITY_CENTER:
					showCityCenterWindow(bg);
					break;
				//住宅
				case BuildingConstant.HOURSE:
					showUpdateWindow(bg);
					break;
				//仓库
				case BuildingConstant.STORAGE:
					showUpdateWindow(bg);
					break;
				//农场
				case BuildingConstant.FARM:
					showMillWindow(bg);
					break;
				//木材厂	
				case BuildingConstant.LUMBER_MILL:
					showLumberWindow(bg);
					break;
				//钢铁厂
				case BuildingConstant.STEEL_PLANT:
					showSteelWindow(bg);
					break;
				//油田
				case BuildingConstant.OIL_WELL:
					showOilFieldWindow(bg);
					break;
				//市场
				case BuildingConstant.MARKET:
					showMarketWindow(bg);
					break;
				//科技中心
				case BuildingConstant.TECHNOLOGY_CENTER:
					showTechCenterWindow(bg);
					break;
				//军事学院
				case BuildingConstant.MILITARY_COLLEGE:
					showMailitaryCollegeWindow(bg);
					break;
				//指挥中心
				case BuildingConstant.COMMOND_CENTER:
					showCommandCenterWindow(bg);
					break;
				//雷达
				case BuildingConstant.RADAR:
					showUpdateWindow(bg);
					break;
				//兵工厂
				case BuildingConstant.ARSENAL:
					showArmoryWindow(bg);
					break;	
				//兵营
				case BuildingConstant.BARRACKS:
					showBarracksWindow(bg);
					break;
				//重型工厂
				case BuildingConstant.HEAVY_FACTORY:
					showHeavyFactoryWindow(bg);
					break;
				//飞机场	
				case BuildingConstant.AIRPORT:
					showAirportWindow(bg);
					break;
					
				//围墙
				case DefenseConstant.FENCE:
				//碉堡
				case DefenseConstant.BUNKER:
				//火炮
				case DefenseConstant.GUN:
				//防空炮
				case DefenseConstant.ANTIGUN:
					showCityDefenseWindow(bg);
					break;				
			}
		}
		
		//显示建造列表
		public static function showBuildWindow(bg:BuildingGrid):void{
			var win:CreateBuildingWindow = new CreateBuildingWindow();
			win.buildingGrid = bg;
			WindowUtil.showWindow(win);
		}
			
		//显示防御工事操作窗口
		private static function showCityDefenseWindow(bg:BuildingGrid):void{
			var win:CityDefenseWindow = new CityDefenseWindow();
			WindowUtil.showWindow(win);
//			win.buildingGrid = bg;
		}
		
		//显示飞机场操作窗口
		private static function showAirportWindow(bg:BuildingGrid):void{
			var win:AirportWindow = new AirportWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示重型工厂操作窗口
		private static function showHeavyFactoryWindow(bg:BuildingGrid):void{
			var win:HeavyFactoryWindow = new HeavyFactoryWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示指挥中心操作窗口
		private static function showCommandCenterWindow(bg:BuildingGrid):void{
			var win:CommandCenterWindow = new CommandCenterWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示兵工厂操作窗口
		private static function showArmoryWindow(bg:BuildingGrid):void{
			var win:ArmoryWindow = new ArmoryWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示城镇中心操作窗口
		private static function showCityCenterWindow(bg:BuildingGrid):void{
			var win:CityCenterWindow = new CityCenterWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示木材厂操作窗口
		private static function showLumberWindow(bg:BuildingGrid):void{
			var win:LumberWindow = new LumberWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示磨坊厂操作窗口
		private static function showMillWindow(bg:BuildingGrid):void{
			var win:MillWindow = new MillWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示钢铁厂操作窗口
		private static function showSteelWindow(bg:BuildingGrid):void{
			var win:SteelWindow = new SteelWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示油田操作窗口
		private static function showOilFieldWindow(bg:BuildingGrid):void{
			var win:OilFieldWindow = new OilFieldWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示更新对话框
		private static function showUpdateWindow(bg:BuildingGrid):void{
			var win:SimpleUpdateWindow = new SimpleUpdateWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示市场窗口
		private static function showMarketWindow(bg:BuildingGrid):void{
			var win:MarketWindow = new MarketWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示学院窗口
		private static function showTechCenterWindow(bg:BuildingGrid):void{
			var win:TechCenterWindow = new TechCenterWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
		
		//显示学院窗口
		private static function showMailitaryCollegeWindow(bg:BuildingGrid):void{
			var win:MilitaryCollegeWindow = new MilitaryCollegeWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}

		//显示兵营对话框
		private static function showBarracksWindow(bg:BuildingGrid):void{
			var win:BarracksWindow = new BarracksWindow();
			WindowUtil.showWindow(win);
			win.buildingGrid = bg;
		}
	}
}