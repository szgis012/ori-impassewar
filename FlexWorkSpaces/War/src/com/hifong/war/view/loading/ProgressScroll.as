package com.hifong.war.view.loading
{
	import flash.display.Loader;
	import flash.utils.ByteArray;
	
	/**
	 * logo图
	 * 
	 */ 
	public class ProgressScroll extends Loader   
	{   
	        [Embed(source="images/loading/scroll.png", mimeType="application/octet-stream")]   
	        public var progressScrollGraphic:Class;   
	        
	        public function ProgressScroll()   
	        {   
	            this.loadBytes(new progressScrollGraphic() as ByteArray);   
	        }   
	  
	    }   
}  