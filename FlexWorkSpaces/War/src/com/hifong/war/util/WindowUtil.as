package com.hifong.war.util
{
	import com.hifong.war.model.ModelLocator;
	
	import flash.events.EventDispatcher;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	import flash.geom.Rectangle;
	import flash.ui.Keyboard;
	
	import mx.containers.Canvas;
	import mx.core.IFlexDisplayObject;
	import mx.core.UIComponent;
	import mx.managers.PopUpManager;
	
	public class WindowUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		/*
		 * 模态遮罩堆栈（为了实现多层的模态窗口效果）
		 * showModelWindow每调用一次就会往该数组中添加一个遮罩层的应用
		 * 同时closeModelWindow会把该应用删除掉。所以这两个函数必须配合使用
		 */
		private static var modelMaskStack:Array = new Array();
		
		//保持对打开的非模态窗口的引用
		private static var openedModelessWindow:IFlexDisplayObject;
		
		
		public static function openBroswerWindow(url:String):void{
			
		}
		
		
		public static function closeWindowPressEsc(event:KeyboardEvent):void{
			if(event.charCode==Keyboard.ESCAPE){
				PopUpManager.removePopUp(event.currentTarget as IFlexDisplayObject);
			}
		}
		/***
		 * 显示带有遮罩效果的模态窗口(支持多层模态窗口嵌套调用)
		 * 关闭该窗口调用closeModelWindow方法
		 */ 
		public static function showModelWindow(win:UIComponent):void{
			var canvas:Canvas = new Canvas();
			canvas.width = 1000;
			canvas.height = 600;
			canvas.x = 0;
			canvas.y = 0;
			canvas.horizontalScrollPolicy = "off";
			canvas.verticalScrollPolicy = "off";
			canvas.setStyle("backgroundColor","#000000");
			canvas.setStyle("backgroundAlpha",0.3);
			
			//保存遮罩层的引用
			modelMaskStack.push(canvas);
			PopUpManager.addPopUp(canvas,model.app);
			
			//让窗口可以拖曳
			addEventHandler(win);
			var pt:Point = new Point();
			pt.x = (model.app.bodyCanvas.width - win.width)/2;
			pt.y = (model.app.bodyCanvas.height - win.height)/2;
			//将相对于bodycanvas坐标转化为全局坐标
			pt = model.app.bodyCanvas.localToGlobal(pt);
			win.x = pt.x;
			win.y = pt.y;
			canvas.addChild(win);
		}
		/**
		 * 关闭由showModelWindow函数打开的窗口
		 * 
		 */ 
		public static function closeModelWindow(win:UIComponent):void{
			//移除事件监听
			removeEventHandler(win);
			
			//将遮罩层的应用弹出
			var canvas:Canvas = modelMaskStack.pop();
			//移除掉其上的窗口
			canvas.removeChild(win);
			
			PopUpManager.removePopUp(canvas);
			
		}
		public static function addEventHandler(win:EventDispatcher):void{
			//让窗口可以拖曳
			win.addEventListener(MouseEvent.MOUSE_UP,onMouseUp);
			win.addEventListener(MouseEvent.MOUSE_MOVE,onMouseMove);
		}
		public static function removeEventHandler(win:EventDispatcher):void{
			win.removeEventListener(MouseEvent.MOUSE_UP,onMouseUp);
			win.removeEventListener(MouseEvent.MOUSE_MOVE,onMouseMove);
		}
		private static function onMouseUp(event:MouseEvent):void{
			var win:UIComponent = event.currentTarget as UIComponent;
			win.alpha = 1;
			win.stopDrag();
		}
		
		private static function onMouseMove(event:MouseEvent):void{
			var win:UIComponent = event.currentTarget as UIComponent;
			
			//如果处在鼠标按下或者拖动状态
			if(event.buttonDown){
				var pt:Point = win.globalToLocal(new Point(event.stageX,event.stageY));
				//限定可以拖动的范围，窗口的上半部分
				if(pt.y > 30){
					return;
				}
				
				win.alpha = 0.8;
				win.startDrag(false,new Rectangle(0,0,1000-win.width,600-win.height));
			}
		}
		/**
		 * 居中显示窗口
		 * 显示非模态窗口,并且保证只存在一个该类型的弹出窗口
		 * modal=true为模态窗口，modal=false为非模态窗口
		 */ 
		public static function showWindow(win:UIComponent,isModal:Boolean=false):void{
			//如果窗口未关闭(这里通过root属性来判断窗口是否关闭)
			if(openedModelessWindow && openedModelessWindow.root){
				//todo 如果显示同一个窗口就会导致闪烁
				PopUpManager.removePopUp(openedModelessWindow);
			}
			
			//弹出窗口并且居中显示
			openedModelessWindow = win;
			PopUpManager.addPopUp(win,model.app.bodyCanvas,isModal);
			PopUpManager.centerPopUp(win);
			addEventHandler(win);
		}
		public static function closeWindow(obj:UIComponent):void{
			removeEventHandler(obj);
			PopUpManager.removePopUp(obj);
		}
	}
}