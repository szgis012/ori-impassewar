/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.events.treasure.BuyTreasureEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.TreasureItemVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理购买宝物事件
     *
     */
	public final class BuyTreasureCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var treasureItem:TreasureItemVO;
		private var num:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:BuyTreasureEvent = event as BuyTreasureEvent;
			var delegate:TreasureDelegate = new TreasureDelegate( this );
			this.treasureItem = evt.treasureItem;
			this.num = evt.num;
			delegate.buyTreasure(model.playerInfo.playerID,treasureItem.treasureID,num);
		}
		
		public function result(data:Object) : void
		{
			model.playerInfo.money -= treasureItem.cost * num;
			MsgBox.showMessage("成功购买 " + num + " 个 " + treasureItem.name + " 道具。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
