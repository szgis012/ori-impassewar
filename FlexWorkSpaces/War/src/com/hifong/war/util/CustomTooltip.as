package com.hifong.war.util
{
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class CustomTooltip extends Canvas implements IToolTip
	{

		public function CustomTooltip(tooltip:String)
		{
			//this.alpha = 0.5;
			this.setStyle("backgroundAlpha",0.8);
			this.setStyle("cornerRadius",3);
			this.setStyle("borderThickness",2);
			this.setStyle("borderColor","black");
			this.setStyle("borderStyle","solid");
			this.setStyle("backgroundColor","#000");
			
			var text:Text = new Text();
			text.setStyle("paddingTop",5);
			text.setStyle("paddingBottom",5);
			text.setStyle("paddingLeft",5);
			text.setStyle("paddingRight",5);

			text.htmlText = tooltip;
			this.addChild(text);
		}
		
		public function get text():String { 
            return ""; 
        }
        
        public function set text(value:String):void {
        } 
        
	}
}