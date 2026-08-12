/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.task
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TaskDelegate;
	import com.hifong.war.events.building.defense.GetCityDefenseListEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.common.GetPlayerInfoEvent;
	import com.hifong.war.events.task.GetTaskListEvent;
	import com.hifong.war.events.task.ReceiveRewardEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 领取任务奖励
     *
     */
	public final class ReceiveRewardCommand extends SequenceCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		/** 任务类型*/
		private var taskType:int;
		
		public override function execute(event:CairngormEvent) : void
		{
			var evt:ReceiveRewardEvent = event as ReceiveRewardEvent;
			taskType = evt.taskType;
			//得到奖励后自动更新任务列表信息
			this.nextEvent = new GetTaskListEvent(evt.taskType);
			var delegate:TaskDelegate = new TaskDelegate( this );
			delegate.getReward(evt.playerTaskID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			if(taskType == 1){
				//更新军械信息
				dispatcher.dispatchEvent(new GetCityOrdnanceListEvent());
				//更新城市人口
				dispatcher.dispatchEvent(new GetCityPopulationEvent(model.cityInfo.cityID));
				//更新资源产量 
				dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
				//更新城防信息 
				dispatcher.dispatchEvent(new GetCityDefenseListEvent());
			}else if(taskType == 2){
				dispatcher.dispatchEvent(new GetPlayerInfoEvent(model.playerInfo.playerID));
			}
			
			//更新城市资源
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			
			this.executeNextCommand();
			
			MsgBox.showMessage("成功领取任务奖励");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage("领取奖励时出错，请稍候再试！");
		}
	}
}
