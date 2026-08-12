/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetTotalAlmsOfGuildMemberInGuildEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;
	/**
	 * 取得军团收入总和或支出总和或玩家捐献总和
	 * 1：若playerID为null表示取得军团收入总和或支出总和（type不为null）
	 * 2：若playerID不为Null表示取得军团玩家捐献总和（type应为null）
	 * type:1收入 2支出
	 * @param guildID
	 * @param guildPlayerID
	 * @param type
	 */
	public final class GetTotalAlmsOfGuildMemberInGuildCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetTotalAlmsOfGuildMemberInGuildEvent = event as GetTotalAlmsOfGuildMemberInGuildEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.getTotalAlmsOfGuildMemberInGuild(evt.guildID,evt.guildPlayerID,evt.types);
		}
		
		public function result(data:Object) : void
		{
			var ac:ArrayCollection=data.result as ArrayCollection;
			var arr:Array=ac.toArray();
			for(var i:String in arr){
				arr[i]=new ObjectProxy(arr[i]);
			}
			ModelLocator.getInstance().guildMenberContributeList=new ArrayCollection(arr);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info); 
		}
		
	}
}