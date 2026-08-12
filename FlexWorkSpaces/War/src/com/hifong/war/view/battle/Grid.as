// ActionScript file
import flash.events.MouseEvent;

import mx.collections.ArrayCollection;

/**
 * 绘制路线网格
 */
private function drawRouteGrid(x:int,y:int):void{
	
}

/**
 * 移除路线网格
 */
private function removeRouteGrid():void{
	for(var i:int;i<currentRouteGridList.length;i++){
		canvasRoute.removeChild(currentRouteGridList.getItemAt(i) as Image);
	}
	currentRouteGridList.removeAll();
}

/**
 * 路线鼠标移上
 */
private function routeMouseOver(event:MouseEvent):void{
	showArmyAvailableRange(event.currentTarget.x,event.currentTarget.y);
	event.currentTarget.filters = [hilightFilter];
}

/**
 * 路线鼠标移开
 */
private function routeMouseOut(event:MouseEvent):void{
	removeRangeGrid();
	event.currentTarget.filters = null;
}


/**
 * 绘制攻击范围网格
 */
private function drawRangeGrid(x:int,y:int):void{
	
	var image:Image = new Image();
	image.source = new RANGE_GRID_IMAGE();
	image.x = x;
	image.y = y;
	image.width = GRID_SIZE;
	image.height = GRID_SIZE;
	
	image.addEventListener(MouseEvent.MOUSE_OVER,rangeMouseOver);
	image.addEventListener(MouseEvent.MOUSE_OUT,rangeMouseOut);
	
	currentRangeGridList.addItem(image);
	
	canvasRange.addChild(image);
}

/**
 * 移除攻击范围网格
 */
private function removeRangeGrid():void{
	isAttacking = false;
	for(var i:int=0;i<currentRangeGridList.length;i++){
		canvasRange.removeChild(currentRangeGridList.getItemAt(i) as Image);
	}
	currentRangeGridList.removeAll();
}

/**
 * 攻击范围鼠标移上
 */
private function rangeMouseOver(event:MouseEvent):void{
	event.currentTarget.filters = [hilightFilter];
}

/**
 * 攻击范围鼠标移开
 */
private function rangeMouseOut(event:MouseEvent):void{
	event.currentTarget.filters = null;
}

