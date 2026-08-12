package com.hifong.war.util
{
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.view.common.AlertWindow;
	import com.hifong.war.view.common.ConfirmWindow;
	import com.hifong.war.view.common.YesNoWindow;
	
	import mx.containers.Canvas;
	import mx.managers.PopUpManager;
	
	public class MsgBox
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		//最后一个错误
		private static var lastError:Object = null;
		
		/**
		 * 弹出信息
		 */
		public static function showMessage(msg:String,modal:Boolean=false,callback:Function=null):void{

			var canvas:Canvas;
			if(model.maskCanvas==null){
				canvas = new Canvas();
				canvas.width = 1000;
				canvas.height = 600;
				canvas.x = 0;
				canvas.y = 0;
				canvas.setStyle("backgroundColor","#000000");
				canvas.setStyle("backgroundAlpha",0.3);
				
				model.maskCanvas = canvas;
				PopUpManager.addPopUp(canvas,model.app,modal);
			}else{
				canvas = model.maskCanvas;
			}
			
			var alert:AlertWindow = new AlertWindow();
			alert.text = msg;
			alert.callback = callback;
			alert.x = (canvas.width+(canvas.width-model.app.bodyCanvas.width)-alert.width)/2;
			alert.y = (canvas.height+(canvas.height-model.app.bodyCanvas.height)-alert.height)/2-40;
			canvas.addChild(alert);
			
		}

		/**
		 * 弹出默认错误信息
		 */
		public static function showDefaultError(info:Object):void{
			
			//如果有已经有错误信息显示，就忽略当前的错误
			if(lastError)
				return;
				
			lastError = info;
				
			var msg:String;
			
			try{
				msg = info.fault.rootCause.message;
			}catch(error:Error){
				msg = "操作失败，如果多次出现该问题，请刷新浏览器或咨询游戏客服。\n" ;
			}
			
			showMessage(msg,false,processError);
		}
		
		//处理出错信息
		private static function processError():void{
			//这样其他错误消息就可以显示了。
			lastError = null;
		}

		/**
		 * 弹出询问对话框(确定|取消)
		 */
		public static function showConfirm(msg:String,callback:Function):void{
			
			var canvas:Canvas;
			if(model.maskCanvas==null){
				canvas = new Canvas();
				canvas.width = 1000;
				canvas.height = 600;
				canvas.x = 0;
				canvas.y = 0;
				canvas.setStyle("backgroundColor","#000000");
				canvas.setStyle("backgroundAlpha",0.3);
				
				model.maskCanvas = canvas;
				PopUpManager.addPopUp(canvas,model.app);
			}else{
				canvas = model.maskCanvas;
			}
			
			var confirm:ConfirmWindow = new ConfirmWindow();
			confirm.text = msg;
			confirm.callback = callback;
			confirm.x = (canvas.width+(canvas.width-model.app.bodyCanvas.width)-confirm.width)/2;
			confirm.y = (canvas.height+(canvas.height-model.app.bodyCanvas.height)-confirm.height)/2-40;
			canvas.addChild(confirm);
			
		}
		
		/**
		 *  弹出询问对话框(是|否)
		 */ 
		public static function showYesNo(msg:String,callback:Function):void{
			var canvas:Canvas;
			if(model.maskCanvas==null){
				canvas = new Canvas();
				canvas.width = 1000;
				canvas.height = 600;
				canvas.x = 0;
				canvas.y = 0;
				canvas.setStyle("backgroundColor","#000000");
				canvas.setStyle("backgroundAlpha",0.3);
				
				model.maskCanvas = canvas;
				PopUpManager.addPopUp(canvas,model.app);
			}else{
				canvas = model.maskCanvas;
			}
			
			var confirm:YesNoWindow = new YesNoWindow();
			confirm.text = msg;
			confirm.callback = callback;
			confirm.x = (canvas.width+(canvas.width-model.app.bodyCanvas.width)-confirm.width)/2;
			confirm.y = (canvas.height+(canvas.height-model.app.bodyCanvas.height)-confirm.height)/2-40;
			canvas.addChild(confirm);
		}

	}
}