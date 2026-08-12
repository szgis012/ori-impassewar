package  com.hifong.war.component
{
	import  com.hifong.war.component.BaseDisplayObject;
	
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.Loader;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.net.URLRequest;
	import flash.system.LoaderContext;
	import flash.utils.ByteArray;
	
	import mx.controls.SWFLoader;
        
    /**
    * 专门读取图片的Loader
    * 
    * */
    public class ImageLoader extends BaseDisplayObject{
                
       public var _data:BitmapData;
       private var loader:Loader;         
        public function ImageLoader(obj:Object = null,lc:LoaderContext = null) {
        }
        private function initLoader():void{
            loader=new Loader();
            while(this.numChildren) this.removeChildAt(0);
            this.addChild(loader);
        }
        private function load(source:String,lc:LoaderContext=null):void{
        	loader.load(new URLRequest(source),lc);
        	addEvent();
        }
        private function loadBytes(source:ByteArray,lc:LoaderContext=null):void{
        	loader.loadBytes(source,lc);
        	addEvent();
        }
        private function addEvent():void{
            loader.contentLoaderInfo.addEventListener(ProgressEvent.PROGRESS,onProgress);
            loader.contentLoaderInfo.addEventListener(Event.COMPLETE,onComplete);
            loader.contentLoaderInfo.addEventListener(IOErrorEvent.IO_ERROR,onIOError);
        }
       	private function	onProgress(event:ProgressEvent):void{
       		
       	}
       	private function onComplete(event:Event):void{
       		delEvent(); 
       	}
       	private function onIOError(event:IOErrorEvent):void{
       		
       	}
        private  function delEvent():void{
            loader.contentLoaderInfo.removeEventListener(ProgressEvent.PROGRESS,onProgress);
            loader.contentLoaderInfo.removeEventListener(Event.COMPLETE,onComplete);
            loader.contentLoaderInfo.removeEventListener(IOErrorEvent.IO_ERROR,onIOError);
        }
        public function set source(source:*):void{
        	if(source != null){
                if(source is ByteArray){
                    loadBytes(source as ByteArray);
                }else if(source is String){
                    load(source as String);
                }else if(source is Bitmap){
                	while(this.numChildren) this.removeChildAt(0);
                	this.addChild(source);
                }
                else{
                    throw new Error("参数错误，构造函数第一参数只接受ByteArray或String或Bitmap");
                }
            }
        }
    }
}