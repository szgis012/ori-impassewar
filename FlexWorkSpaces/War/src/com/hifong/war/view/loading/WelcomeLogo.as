package com.hifong.war.view.loading
{
	import flash.display.Loader;
	import flash.utils.ByteArray;
	
	/**
	 * logo图
	 * 
	 */ 
	public class WelcomeLogo extends Loader   
	{   
	        [Embed(source="images/loading/logo.png", mimeType="application/octet-stream")]   
	        public var WelcomeLogoGraphic:Class;   
	        
	        public function WelcomeLogo()   
	        {   
	            this.loadBytes(new WelcomeLogoGraphic() as ByteArray);   
	        }   
	  
	    }   
}  