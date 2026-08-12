package com.hifong.war.view.assets.skin
{
	import flash.filters.BevelFilter;
	
	import mx.skins.RectangularBorder;

	/**
	 * 绘制简单双线边框
	 * titlewindow,Canvas Window 等最外层的边框。
	 * 
	 */ 
    public class SimpleBorder extends RectangularBorder
    {


        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void 
        {

            super.updateDisplayList(unscaledWidth, unscaledHeight);
            var cornerRadius:Number = getStyle("cornerRadius");
            var backgroundColor:int = getStyle("backgroundColor");
            var backgroundAlpha:Number = getStyle("backgroundAlpha");
            graphics.clear();
//            graphics.lineStyle(0,0x222e3c,0.9,true);
//            graphics.drawRect(0,0,unscaledWidth,unscaledHeight);
//            graphics.drawRect(1,1,unscaledWidth-2,unscaledHeight-2);
//            graphics.lineStyle(0,0xe7e7e7,0.9,true);
//            graphics.drawRect(2,2,unscaledWidth-4,unscaledHeight-4);
//            graphics.drawRect(3,3,unscaledWidth-6,unscaledHeight-6);

			graphics.lineStyle(0,0x3f6041);
            graphics.drawRect(0,0,unscaledWidth,unscaledHeight);
            graphics.lineStyle(0,0x2c482e);
            graphics.drawRect(1,1,unscaledWidth-2,unscaledHeight-2);
            graphics.drawRect(2,2,unscaledWidth-4,unscaledHeight-4);
            graphics.lineStyle(0,0x3f6041);
            graphics.drawRect(3,3,unscaledWidth-6,unscaledHeight-6);
            
            
//            drawRoundRect(3,3,unscaledWidth-3,unscaledHeight-3,null,0x55616D);
            // Background
//
			graphics.lineStyle(0,0,0.9,true);
			  
            drawRoundRect
            (
                4, 4, unscaledWidth-8, unscaledHeight-8, 
                 null, 
                0x000000, 0.8
            );
          //模糊效果
//          var f:BlurFilter = new BlurFilter();
//	      f.blurX =2;
//	      f.blurY =2;      
//	      filters = [f];
          //
////          
//          var f:BevelFilter = new BevelFilter();
//          f.blurX = 10;
//          f.blurY = 10;
//          f.distance =  2;
//          f.angle = 45;
//          f.highlightAlpha = 0.5;
//          f.highlightColor = 0;
//          f.shadowColor = 0;
//          f.shadowAlpha = 0.3;
//          f.quality = 1;
//          f.knockout = false;
//          
//          this.filters = [f];
        }

    }

}