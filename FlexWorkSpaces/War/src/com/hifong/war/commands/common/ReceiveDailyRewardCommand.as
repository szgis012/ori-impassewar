/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.ReceiveDailyRewardEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 获取每日奖励
	 */
	public final class ReceiveDailyRewardCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ReceiveDailyRewardEvent = event as ReceiveDailyRewardEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this);
			delegate.receiveDailyReward(evt.playerID);
		}
		/**
		 * 刷新资源
		 */
		public function result(data:Object) : void
		{
			//城市资源
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
			//军队情况
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(ModelLocator.getInstance().cityInfo.cityID)); 
			MsgBox.showMessage("成功领取奖励"); 
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}