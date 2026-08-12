/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.task
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TaskDelegate;
	import com.hifong.war.events.task.GetTaskListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 获得任务列表信息
     *
     */
	public final class GetTaskListCommand implements ICommand, IResponder
	{
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetTaskListEvent = event as GetTaskListEvent;
			var delegate:TaskDelegate = new TaskDelegate( this );
			delegate.getPlayerTaskList(ModelLocator.getInstance().playerInfo.playerID,evt.taskType);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			
			if(rs){
				ModelLocator.getInstance().taskList = rs.result as ArrayCollection;
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage("获取任务列表时出错，请稍候再试！");
		}
	}
}
