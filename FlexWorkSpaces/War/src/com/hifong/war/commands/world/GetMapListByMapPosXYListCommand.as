/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MapDelegate;
	import com.hifong.war.events.world.GetMapListByMapPosXYListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.LoadingUtil;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;

	public final class GetMapListByMapPosXYListCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetMapListByMapPosXYListEvent = event as GetMapListByMapPosXYListEvent;
			var delegate:MapDelegate=new MapDelegate(this);
			delegate.getMapListByMapPosXYList(evt.mapXY);
		}
		
		public function result(data:Object) : void
		{
			var ac:ArrayCollection=data.result as ArrayCollection;	
			LoadingUtil.hideLoadingScreen();
			if(ac && ac.length){
				ModelLocator.getInstance().worldPanel.addGrid(ac.toArray());
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}