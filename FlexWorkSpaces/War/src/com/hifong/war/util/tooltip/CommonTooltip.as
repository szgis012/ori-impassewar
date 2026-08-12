package com.hifong.war.util.tooltip
{
	import mx.containers.Canvas;
	import mx.controls.Text;
	import mx.core.IToolTip;
	
	public class CommonTooltip extends Canvas implements IToolTip
	{
		[Embed(source="images/tooltip.png",scaleGridLeft="10",scaleGridTop="10",scaleGridRight="20",scaleGridBottom="20")]
		public var tooltip:Class;
		
		protected var txtMsg:Text = new Text();
		
		/**
		 * 构造函数
		 * 
		 */ 
		public function CommonTooltip(value:String=null)
		{
			this.setStyle("borderSkin",tooltip);
			
			txtMsg.setStyle("paddingTop",5);
			txtMsg.setStyle("paddingBottom",5);
			txtMsg.setStyle("paddingLeft",5);
			txtMsg.setStyle("paddingRight",5);
			
			if(value){
				this.txtMsg.htmlText = value;
			}
			this.addChild(txtMsg);
		}
		
		//直接设置tooltip的内容
		public function set htmlTooltip(value:String):void{
			txtMsg.htmlText = value;
		}
		
		public function get text():String { 
            return ""; 
        }
        
        public function set text(value:String):void {
        } 
        
	}
}