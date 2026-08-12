package com.hifong.war.view.loading
{
	import flash.display.Loader;
	import flash.utils.ByteArray;
	
	/**
	 * logo图
	 * 
	 */ 
	public class ProgressBackground extends Loader   
	{   
	        [Embed(source="images/loading/bg.png", mimeType="application/octet-stream")]   
	        public var progressBackgroundGraphic:Class;   
	        
	        public function ProgressBackground()   
	        {   
	            this.loadBytes(new progressBackgroundGraphic() as ByteArray);   
	        }   
	  
	    }   
}  