/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.DeclareWarDelegate;
	import com.hifong.war.events.world.GetDeclareWarListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     *
     * 获得城市宣战信息列表
     */
	public final class GetDeclareWarListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetDeclareWarListEvent = event as GetDeclareWarListEvent;
			var delegate:DeclareWarDelegate = new DeclareWarDelegate(this);
			//delegate.getCityDeclareWarList(model.cityInfo.posX,model.cityInfo.posY);
		}
		
		public function result(data:Object) : void
		{
			//var rs:ResultEvent = data as ResultEvent;
			//添加宣战信息
			//model.declareWarList = rs.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
