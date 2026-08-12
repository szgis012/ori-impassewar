package com.hifong.war.util
{
	
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	
	import mx.containers.Canvas;
	
	public class BusyCursor extends Sprite
	{
		
		[Embed(source='/images/busyCursor.swf')]
		private var _asset:Class;
		
		public function BusyCursor()
		{
			super();
			_drawCursor();
		}

		private function _drawCursor():void
		{
			var cursor:DisplayObject = new _asset() as DisplayObject;
			addChild(cursor);
		}

	}
}