/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MapDelegate;
	import com.hifong.war.events.world.DeleteMapFavouriteEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 根据编号删除地图收藏信息
	 * @param mapFavouriteID
	 */
	public final class DeleteMapFavouriteCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DeleteMapFavouriteEvent = event as DeleteMapFavouriteEvent;
			var delegate:MapDelegate=new MapDelegate(this);
			delegate.deleteMapFavourite(evt.mapFavouriteID);
		}
		
		public function result(data:Object) : void
		{
				MsgBox.showMessage("成功删除收藏坐标");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}