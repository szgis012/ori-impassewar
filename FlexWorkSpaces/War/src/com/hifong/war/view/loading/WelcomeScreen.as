package com.hifong.war.view.loading
{
	import flash.display.BitmapData;   
	import flash.display.Graphics;   
	import flash.display.Loader;   
	import flash.display.Sprite;   
	import flash.utils.ByteArray;   
	   
	import mx.graphics.codec.PNGEncoder;   
	
	/**
	 *  欢迎图
	 * 
	 */    
	public class WelcomeScreen extends Loader   
	{   
		[Embed(source="images/loading/bg.jpg", mimeType="application/octet-stream")]   
	    public var WelcomeScreenGraphic:Class;  
	     
	    public function WelcomeScreen()   
        {   
            this.loadBytes(new WelcomeScreenGraphic() as ByteArray);   
        }   
        
	}
}
