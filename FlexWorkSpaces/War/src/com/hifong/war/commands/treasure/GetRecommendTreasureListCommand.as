/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.events.treasure.GetRecommendTreasureListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.TreasureItemVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	
    /**
     * 获得推荐宝物列表
     *
     */
	public final class GetRecommendTreasureListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetRecommendTreasureListEvent = event as GetRecommendTreasureListEvent;
			var delegate:TreasureDelegate = new TreasureDelegate( this );
			delegate.getRecommendTreasureList();
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			var arr:ArrayCollection = rs.result as ArrayCollection;
			var list:ArrayCollection = new ArrayCollection();
			
			if(arr){
				var pt:Object;//Treasure
				var ti:TreasureItemVO;
				for(var i:int; i<arr.length; i++){
					pt = arr.getItemAt(i);
					ti = new TreasureItemVO();
					ti.treasureID = pt.treasureID;
					ti.category = pt.category;
					ti.name = pt.name;
					ti.description = pt.description;
					ti.type =  pt.type;
					ti.cost =  pt.cost;
					ti.imgSrc =  pt.imgSrc;
					ti.state = pt.state;
					
					list.addItem(ti);
				}
			}
			model.treasureShopList=list;
//			model.treasureList = list;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
