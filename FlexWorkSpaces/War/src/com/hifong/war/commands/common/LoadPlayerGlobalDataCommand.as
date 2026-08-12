/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.common.GlobalTimer;
	import com.hifong.war.constant.ContryTypeConstant;
	import com.hifong.war.events.common.LoadPlayerCacheDataEvent;
	import com.hifong.war.events.common.LoadPlayerGlobalDataEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.BuildingUtil;
	import com.hifong.war.util.CityDefenseUtil;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WindowUtil;
	import com.hifong.war.view.assets.ICountryRelatedAssets;
	import com.hifong.war.vo.PlayerVO;
	
	import mx.collections.ArrayCollection;
	import mx.events.ModuleEvent;
	import mx.managers.PopUpManager;
	import mx.modules.IModuleInfo;
	import mx.modules.ModuleManager;
	import mx.rpc.IResponder;
	
    /**
     * 处理登录时加载用户基础数据的事件
     *
     */
	public final class LoadPlayerGlobalDataCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		private var userID:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:LoadPlayerGlobalDataEvent = event as LoadPlayerGlobalDataEvent;
			var delegate:PlayerDelegate = new PlayerDelegate( this );
			userID = evt.userID;
			delegate.loadPlayerGlobalData(evt.userID);
		}
		
		public function result(data:Object) : void
		{
			//rs具体包含的值参看服务器端loadPlayerGlobalData实现
			var rs:Object = data.result;
			
			//如果用户还未创建角色
			if (rs==null) {
				WindowUtil.closeWindow(model.loginWindow);
				showCreatePlayerWindow();
				return;
			}
			
			//用户所属阵营资源的加载地址
			var url:String = "com/hifong/war/view/assets/";
			
			if(rs["player"].country == ContryTypeConstant.FREE_UNION){
				url += "FreeUnionAssets.swf";
			}else{
				url += "UnionEmpireAssets.swf";			
			}
			var minfo:IModuleInfo = ModuleManager.getModule(url);
			minfo.addEventListener(ModuleEvent.READY,function(event:ModuleEvent):void{
				//这里进行加载资源完成后的处理
				var m:ICountryRelatedAssets = event.module.factory.create() as ICountryRelatedAssets;
				model.countryRelatedAssets = m;
				//后面的处理需要用到前面加载的资源，所以只能推后处理
				handleResult(rs);
			});
			minfo.addEventListener(ModuleEvent.ERROR,doError);
			minfo.load();
			
		}
		
		//显示创建角色窗口
		private function showCreatePlayerWindow():void{
			var win:CreatePlayerWindow = new CreatePlayerWindow();
			win.userID = userID;
			model.createPlayerWindow = win;
			PopUpManager.addPopUp(win,model.app);
		}
		
		//处理返回的数据
		private function handleResult(rs:Object) : void{
			
			//服务器时间
			model.serverTime = rs["serverTime"] as Date;
			model.globalTimer = new GlobalTimer();
			
			var player:PlayerVO = rs["player"]  as PlayerVO;
			var buildingList:ArrayCollection = rs["buildingList"] as ArrayCollection;
			var defenseList:ArrayCollection=rs["defenseList"] as ArrayCollection;
			var cityBuildingList:ArrayCollection = rs["cityBuildingList"] as ArrayCollection;
			var cityDefenseList:ArrayCollection = rs["cityDefenseList"] as ArrayCollection;
			
			//玩家，城市信息
			model.playerInfo = player;
			model.cityInfo = player.city;
			
			//建筑信息
			model.buildingInfo.buildingList = buildingList
			model.buildingInfo.buildingMap = BuildingUtil.getBuildingListMap(buildingList);
			//城防信息
			model.buildingInfo.defenseList=defenseList;
			model.buildingInfo.defenseMap=BuildingUtil.getDefenseListMap(defenseList);
			//城市建筑信息
			for(var i:int=0; i<cityBuildingList.length; i++){
				model.cityBuildingList.addItem(cityBuildingList.getItemAt(i));
			}
			
			//初始化城防信息
			model.cityDefenseInfo.cityDefenseMap = CityDefenseUtil.getCityDefenseMap(cityDefenseList);
			model.cityDefenseInfo.cityDefenseList = cityDefenseList;
			
			//初始化聊天Socket  
			model.app.initGameSocket();
			
			//登录提交信息 动画关闭
			model.loginWindow.canvasLoading.visible = false;
			//关闭登陆窗口
			WindowUtil.closeWindow(model.loginWindow);
			//显示主窗口
			model.app.bodyCanvas.visible = true;
			
			//是否有新报告
			model.haveNewReport = rs["haveNewReport"];
			//是否有新消息
			model.haveNewMessage = rs["haveNewMessage"];
			//是否有已完成任务
			model.haveCompletedTask = rs["haveCompletedTask"];
			
			//加载其他缓存信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new LoadPlayerCacheDataEvent());
		}
		
		private function doError(event:Object):void{
			MsgBox.showMessage("加载数据时出错，请刷新浏览器重试。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
