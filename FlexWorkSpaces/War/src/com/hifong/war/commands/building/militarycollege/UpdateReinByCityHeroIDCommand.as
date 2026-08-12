/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.UpdateReinByCityHeroIDEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 根据城市英雄编号更改统御
	 * @param cityHeroID
	 * @param rein
	 */
	public final class UpdateReinByCityHeroIDCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:UpdateReinByCityHeroIDEvent = event as UpdateReinByCityHeroIDEvent;
			var delegate:HeroDelegate=new HeroDelegate(this);
			delegate.updateReinByCityHeroID(evt.cityHeroID,evt.rein);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功更改指挥官统御。"); 	
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}