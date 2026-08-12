/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetAllGuildIncomeInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;
		/**
		 * 获取工会所有收入明细列表
		 */
	public final class GetAllGuildIncomeInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetAllGuildIncomeInfoEvent = event as GetAllGuildIncomeInfoEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.getAllGuildIncomeInfo(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var ac:ArrayCollection=data.result as ArrayCollection;
			var arr:Array=ac.toArray();
			for(var i:String in arr){
				arr[i]=new ObjectProxy(arr[i]);
			}
			//ModelLocator.getInstance().guildMenberContributeList=new ArrayCollection(arr);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}