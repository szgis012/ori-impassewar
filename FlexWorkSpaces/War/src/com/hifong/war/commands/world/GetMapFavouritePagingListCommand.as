/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MapDelegate;
	import com.hifong.war.events.world.GetMapFavouritePagingListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;

	public final class GetMapFavouritePagingListCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetMapFavouritePagingListEvent = event as GetMapFavouritePagingListEvent;
			var delegate:MapDelegate=new MapDelegate(this);
			delegate.getMapFavouritePagingList(evt.playerID,evt.start,evt.offset);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().mapCollectionList=data.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}