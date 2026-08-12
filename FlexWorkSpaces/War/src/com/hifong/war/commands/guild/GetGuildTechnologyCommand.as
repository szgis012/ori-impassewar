/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetGuildTechnologyEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;
	/**
	 * 取得军团可研究科技列表
	 * @param guildID
	 * @return list
	 */
	public final class GetGuildTechnologyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetGuildTechnologyEvent = event as GetGuildTechnologyEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.getGuildTechnology(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var ac:ArrayCollection=data.result as ArrayCollection;
			var arr:Array=ac.toArray();
			for(var i:String in arr){
				arr[i]=new ObjectProxy(arr[i]);
			}
			ModelLocator.getInstance().guildTechnologyList=new ArrayCollection(arr);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}