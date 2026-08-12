package com.hifong.war.util
{
	import com.hifong.war.model.ModelLocator;
	
	import flash.display.DisplayObject;
	
	import mx.containers.Canvas;
	import mx.controls.SWFLoader;
	
	public class LoadingUtil
	{
		
		private var model:ModelLocator = ModelLocator.getInstance();
		
		private static var currentDisplayObject:DisplayObject;
		
		public function LoadingUtil()
		{
		}
		
		public static function showLoadingScreen():void
		{
			var canvas:Canvas = new Canvas();
			canvas.width = 1000;
			canvas.height = 600;
			canvas.x = 0;
			canvas.y = 0;
			canvas.setStyle("backgroundColor","#000000");
			canvas.setStyle("backgroundAlpha",0.3);
			
			var swfLoader:SWFLoader = new SWFLoader();
			swfLoader.width = 205;
			swfLoader.height = 154;
			swfLoader.x = (canvas.width-swfLoader.width)/2;
			swfLoader.y = (canvas.height-swfLoader.height)/2;
			swfLoader.source = "images/loading.swf";
			
			canvas.addChild(swfLoader);
			
			currentDisplayObject = canvas;
			
			ModelLocator.getInstance().app.addChild(canvas);
		}
		
		public static function hideLoadingScreen():void{
			if(ModelLocator.getInstance().app.contains(currentDisplayObject))
				ModelLocator.getInstance().app.removeChild(currentDisplayObject);
		}

	}
}