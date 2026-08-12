/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MapDelegate;
	import com.hifong.war.events.world.CreateMapFavouriteEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 添加地图收藏信息
	 * @param favourite
	 */
	public final class CreateMapFavouriteCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:CreateMapFavouriteEvent = event as CreateMapFavouriteEvent;
			var delegate:MapDelegate=new MapDelegate(this);
			delegate.createMapFavourite(evt.playerID,evt.posX,evt.posY);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功收藏地图");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}