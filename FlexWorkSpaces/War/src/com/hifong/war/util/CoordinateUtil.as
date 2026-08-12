package com.hifong.war.util
{
	import flash.geom.Point;
	
	//坐标变换
	public class CoordinateUtil
	{
		/**
		 * 将新坐标系的点换算成原坐标系的点坐标
		 * 
		 * p 新坐标系的点坐标
		 * angle 新坐标相对原坐标旋转的角度
		 * g,h 新坐标系的原点在原坐标系的坐标x,y
		 *
		 */  
		public static function getOldPoint(p:Point,angle:Number=0,g:Number=0,h:Number=0):Point{
			var pt:Point = new Point();
			pt.x  = g + p.x * Math.cos(angle) - p.y * Math.sin(angle);
			pt.y = h + p.x * Math.sin(angle) + p.y * Math.cos(angle);
			
			return pt;
		}
		
		/**
		 * 将原坐标系的点换算成新坐标系的点坐标
		 * 
		 * angle 新坐标相对原坐标旋转的角度
		 * p 原坐标系的点坐标
		 * g,h 新坐标系的原点在原坐标系的坐标x,y
		 *
		 */ 
		public static function getNewPoint(p:Point,angle:Number=0,g:Number=0,h:Number=0):Point{
			var pt:Point = new Point();
			pt.x  = p.x * Math.cos(angle) + p.y * Math.sin(angle) - g*Math.cos(angle) - h*Math.sin(angle);
			pt.y = p.y * Math.cos(angle) - p.x * Math.sin(angle) - h*Math.cos(angle) + g*Math.sin(angle);
			
			return pt;
		}

	}
}