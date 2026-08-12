package com.hifong.war.commands.building.commandcenter
{
	/**
	 * 获取伤兵信息
	 */
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ArmyDelegate;
	import com.hifong.war.events.building.commandcenter.GetWoundArmyEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	public class GetWoundArmyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent):void
		{
			var getWoundArmyEvent:GetWoundArmyEvent=event as GetWoundArmyEvent;
			var delegate:ArmyDelegate=new ArmyDelegate(this);
			delegate.getWoundArmyList(getWoundArmyEvent.cityID);
		}
		
		public function result(data:Object):void
		{
			var ac:ArrayCollection=data.result as ArrayCollection;
			var arr:Array=ac.toArray();
			for(var i:String in arr){
				arr[i]=new ObjectProxy(arr[i]);
			}
			ModelLocator.getInstance().woundArmyList=new ArrayCollection(arr);
		}
		 
		public function fault(info:Object):void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}