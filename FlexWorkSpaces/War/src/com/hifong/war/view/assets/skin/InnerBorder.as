package com.hifong.war.view.assets.skin
{
	import flash.filters.BevelFilter;
	
	import mx.skins.RectangularBorder;

	/**
	 * 绘制简单窗体内的Canvas边框
	 * 
	 */ 
    public class InnerBorder extends RectangularBorder
    {


        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void 
        {

            
            super.updateDisplayList(unscaledWidth, unscaledHeight);
            var cornerRadius:Number = getStyle("cornerRadius");
            var backgroundColor:int = getStyle("backgroundColor");
            var backgroundAlpha:Number = getStyle("backgroundAlpha");
            graphics.clear();
            graphics.lineStyle(0,0x5A6343);
            graphics.drawRect(0,0,unscaledWidth,unscaledHeight);
            graphics.lineStyle(0,0x898C8E);
            graphics.drawRect(1,1,unscaledWidth-2,unscaledHeight-2);
//            graphics.lineStyle(0,0xcacaca);
	
//            graphics.lineStyle(0,0x102d41);
//            graphics.drawRect(0,0,unscaledWidth,unscaledHeight);
//            graphics.lineStyle(0,0xa5d2ff);
//            graphics.drawRect(1,1,unscaledWidth-2,unscaledHeight-2);
////            graphics.lineStyle(0,0xcacaca);
//            graphics.drawRect(2,2,unscaledWidth-4,unscaledHeight-4);
//			 graphics.lineStyle(1,0x475653,1,true);
//            graphics.drawRect(3,3,unscaledWidth-6,unscaledHeight-6);
            
            
            
//            drawRoundRect(3,3,unscaledWidth-3,unscaledHeight-3,null,0x55616D);
            // Background
//
			graphics.lineStyle(0,0,0.9,true);
			
            drawRoundRect
            (
                2, 2, unscaledWidth-4, unscaledHeight-4, 
                 null, 
                0x25261a, 0.6
            );
          //模糊效果
//          var f:BlurFilter = new BlurFilter();
//	      f.blurX =2;
//	      f.blurY =2;      
//	      filters = [f];
          //
//////          
//          var f:BevelFilter = new BevelFilter();
//          f.blurX = 12;
//          f.blurY = 12;
//          f.distance =  5;
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