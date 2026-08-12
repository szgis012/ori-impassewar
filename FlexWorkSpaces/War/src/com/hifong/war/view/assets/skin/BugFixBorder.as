package com.hifong.war.view.assets.skin
{
	import flash.filters.BevelFilter;
	
	import mx.skins.RectangularBorder;

	/**
	 * 代替CanvasBorder的边框
	 * 如果需要在二级控件中应用类似CanvasBorder的样式，可以使用该类。
	 * 与CanvasBorder不同，该类绘制的边框和显示区域为不透明的。
	 * 
	 */ 
    public class BugFixBorder extends RectangularBorder
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
			graphics.lineStyle(0,0,1,true);
			
            drawRoundRect
            (
                2, 2, unscaledWidth-4, unscaledHeight-4, 
                 null, 
                0x2F3E30, 1
            );
        }

    }

}