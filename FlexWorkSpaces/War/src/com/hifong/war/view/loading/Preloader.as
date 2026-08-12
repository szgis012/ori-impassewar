package com.hifong.war.view.loading
{
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.ConfigUtil;
	
	import flash.display.Graphics;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.text.TextField;
	import flash.utils.Timer;
	import flash.utils.clearInterval;
	import flash.utils.setInterval;
	
	import mx.controls.Alert;
	import mx.events.FlexEvent;
	import mx.preloaders.DownloadProgressBar;   
	   
	public class Preloader extends DownloadProgressBar   
	{   
	    //欢迎
	    public var screen:WelcomeScreen;   
	    //logo
	    public var logo:WelcomeLogo;   
	    //显示进度的文字   
	    private var progressText:TextField;
	    //提示信息
	    private var tipText:TextField;
	    //计时器
	    private var _timer:Timer;   
	    //background
	    private var background:ProgressBackground;
	    //scroll
	    private var scroll:ProgressScroll;
	    //mask
	    private var scrollMask:Sprite;
	    
	    //加载界面的尺寸
	    private static const LOADING_WIDTH:Number = 1000;
	    private static const LOADING_HEIGHT:Number = 600;
	    //logo的尺寸
	    private static const LOGO_WIDTH:Number = 220;
	    //欢迎图片的尺寸
	    private static const WELCOMESCREEN_WIDTH:Number = 622;
	    //进度条背景图片宽度
	    private static const PROGRESSBACKGROUND_WIDTH:Number = 614;
	    //进度条滚动图片的尺寸
	    private static const PROGRESSSCROLL_WIDTH:Number = 606;   
	    private static const PROGRESSSCROLL_HEIGHT:Number = 18; 
	    //滚动起始点;   
	    private static const SCROLL_START:Number = (LOADING_WIDTH/2 - PROGRESSSCROLL_WIDTH/2);
	    
	    public function Preloader()   
	    {   
	    	
	    	//初始化ConfigUtil
	    	ConfigUtil.doInit();
	    	
	        super();   
	       	
	       	drawBackground();
	       	
	        //加入logo   
	        logo = new WelcomeLogo();   
	        logo.x = (LOADING_WIDTH/2 - LOGO_WIDTH/2);
	        logo.y = 50;
	        this.addChild(logo);  
	        
	         
	        //加入欢迎图片
	        screen = new WelcomeScreen();   
	        screen.x = (LOADING_WIDTH/2 - WELCOMESCREEN_WIDTH/2);
	        screen.y = 160;
	        this.addChild(screen);   
	        
//	        progressBar = new LoadingProgressBar();
//	        progressBar.x = (LOADING_WIDTH/2 - WELCOMESCREEN_WIDTH/2);
//	        progressBar.y = 420;
//	        this.addChild(progressBar);
	        
	        //显示进度文字
	        progressText = new TextField();
	        progressText.textColor=0xffffff;
	        progressText.width = 100;
	        progressText.x = (LOADING_WIDTH/2 - progressText.width/2);
	        progressText.y = 400;
	        this.addChild(progressText);
	        
	        tipText = new TextField();
	        tipText.textColor=0xffffff;
	        tipText.width = 300;
	        tipText.x = (LOADING_WIDTH/2 - tipText.width/2);
	        tipText.y = 450;
	        tipText.text = "如果您是第一次进入游戏，需要加载部分资源，请稍候。。。";
	        this.addChild(tipText);
	        
	        //进度条
	        background = new ProgressBackground();
	        background.x = (LOADING_WIDTH/2 - PROGRESSBACKGROUND_WIDTH/2);
	        background.y = 420;
	        this.addChild(background);
	        
	        scroll = new ProgressScroll();
	        scroll.x = SCROLL_START;
	        scroll.y = 420+5;
	        this.addChild(scroll);
	        
	        //mask
	        scrollMask = new Sprite();
	        var g:Graphics = scrollMask.graphics;
            g.beginFill(0x000000);
            g.drawRect(0,0,PROGRESSSCROLL_WIDTH,PROGRESSSCROLL_HEIGHT);
            g.endFill();
            scrollMask.x = SCROLL_START - PROGRESSSCROLL_WIDTH;
            scrollMask.y = scroll.y;
            
	        this.addChild(scrollMask);
	        
	        scroll.mask = scrollMask;
	        
	  
	    }   
	    
	    //绘制背景色
	    private function drawBackground():void{
	    	var bgcolor:Number = 0x000000;//black
	    	
	    	graphics.beginFill(bgcolor);
	    	graphics.lineStyle(0,bgcolor);
	    	graphics.drawRect(0,0,LOADING_WIDTH,LOADING_HEIGHT);
	    }
	    
	    
	    /**  
	     * override这个函数，来实现自己Preloader的设置，而不是用其默认的设置  
	     */  
	    override public function set preloader(value:Sprite):void  
	    {   
	        value.addEventListener(ProgressEvent.PROGRESS, progHandler);   
	        value.addEventListener(FlexEvent.INIT_COMPLETE, initCompleteHandler);   
	        //在这里设置预载界面居中   
	        //如果在初始化函数中设置，会有stageWidth和最终界面大小不一致的错误，而导致不能居中   
	        
	        this.backgroundColor=0xff0000;;
	        x = 0;   
	        y = 0;   
	    }   
	       
	    private function progHandler(e:ProgressEvent):void
	    {
	        //计算进度，并且设置文字进度和进度条的进度。   
	        var prog:Number = e.bytesLoaded/e.bytesTotal;
	        progressText.text = "已加载"+int(prog * 100)+"%";
	        
	        scrollMask.x = (SCROLL_START - PROGRESSSCROLL_WIDTH) + PROGRESSSCROLL_WIDTH * prog
	    }
	       
	    private function initCompleteHandler(e:FlexEvent):void
	    {
	    	dispatchEvent(new Event(Event.COMPLETE));
	    }   
	       
	
	} 
	
}