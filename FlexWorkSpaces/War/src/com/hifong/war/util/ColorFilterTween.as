package  com.hifong.war.util
{
	/**
	 *  @author 
	 *  高亮颜色过度缓动
	 */
	import com.greensock.TweenMax;
	import com.greensock.data.ColorMatrixFilterVars;
	
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	import flash.filters.ColorMatrixFilter;
	import flash.geom.ColorTransform;

	public class ColorFilterTween
	{
		private static  var colorFilterContainer:Object=new Object();
		
		/** 灰色滤镜 */
		public static var greyFilter:ColorMatrixFilter = new ColorMatrixFilter([0.3086, 0.6094, 0.082, 0 ,  0, 0.3086, 0.6094, 0.082,   0,   0, 0.3086, 0.6094, 0.082,   0,   0, 0.3086, 0.6094, 0.082, 1, 0]);
		/** 高亮参数 */
		public static var hilightNum:Number=1.5;
		public static function addRollEffect(mc:DisplayObject):void{
			mc.addEventListener(MouseEvent.ROLL_OVER,onRollOver,false,0,true);
		}
		public static function onRollOver(e:MouseEvent):void{
			var target:DisplayObject=e.currentTarget as DisplayObject;
			if( !target.hasEventListener(MouseEvent.ROLL_OUT)){
				target.addEventListener(MouseEvent.ROLL_OUT,onRollOut);
			}
			displayHilight(target);
		}
		public static function onRollOut(e:MouseEvent):void{
			var target:DisplayObject=e.currentTarget as DisplayObject;
			unDisplayHilight(target);
			target.removeEventListener(MouseEvent.ROLL_OUT,onRollOut);
		}
		public static function displayHilight(target:DisplayObject,time:Number=0.4,delayTime:Number=0):void{
			TweenMax.killTweensOf(target);
			var source:ColorMatrixFilterVars=new ColorMatrixFilterVars();
			var vars:ColorMatrixFilterVars=new ColorMatrixFilterVars();
			var matrix:Array=getColorMatrix(target);
			var objName:String=target.toString().split(".").join("_");
			if(matrix!=null  &&  matrix[0]<1  ){
				colorFilterContainer.objName=new Object();
				colorFilterContainer.objName.matrix=matrix;
				colorFilterContainer.objName.time=time;
				colorFilterContainer.objName.delay=delayTime;
				//灰度高亮
				if(matrix[4]>1){
					colorFilterContainer.objName.matrix=greyFilter.matrix;
				}
				//是灰度
				source.matrix=greyFilter.matrix;
				vars.matrix=greyFilter.matrix;
			}
			vars.setBrightness(hilightNum);
			TweenMax.fromTo(target,time,{colorMatrixFilter:source},{colorMatrixFilter :vars,delay:delayTime });
		}
		public static function unDisplayHilight(target:DisplayObject,time:Number=0.3,delayTime:Number=0.3):void{
			var vars:ColorMatrixFilterVars=new ColorMatrixFilterVars();
			var objName:String=target.toString().split(".").join("_");
			if(colorFilterContainer.objName !=null){
				vars.matrix =colorFilterContainer.objName.matrix;
				colorFilterContainer.objName.matrix=null;
				colorFilterContainer.objName.delay=null;
				colorFilterContainer.objName.time=null;
				colorFilterContainer.objName=null;
				delete colorFilterContainer.objName;
			}
			vars.setBrightness(1); 
			TweenMax.to(target,time,{colorMatrixFilter:vars ,delay: delayTime });
			target.removeEventListener(MouseEvent.ROLL_OUT,onRollOut);
		}
		public static function getColorMatrix(mc:DisplayObject):Array {
			var f:Array = mc.filters;
			for (var i:String in f) {
				if (f[i] is ColorMatrixFilter) {
					return f[i].matrix; 
				}
			}
			return null;
		} 
	}
}