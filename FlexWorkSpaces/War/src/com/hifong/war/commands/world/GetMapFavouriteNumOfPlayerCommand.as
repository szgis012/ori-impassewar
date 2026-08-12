/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MapDelegate;
	import com.hifong.war.events.world.GetMapFavouriteNumOfPlayerEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 根据玩家编号获得其地图收藏条数
	 * @param playerID
	 * @return
	 */
	public final class GetMapFavouriteNumOfPlayerCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetMapFavouriteNumOfPlayerEvent= event as GetMapFavouriteNumOfPlayerEvent;
			var delegate:MapDelegate=new MapDelegate(this);
			delegate.getMapFavouriteNumOfPlayer(evt.playerID);
		}
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().mapCollectionNum=data.result; 
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}