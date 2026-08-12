// ActionScript file
import com.hifong.war.constant.CityDefenseConstant;

import flash.events.MouseEvent;

/**
 * 城防鼠标移上
 */
private function defenceMouseOver(event:MouseEvent):void{
	if(event.currentTarget.data.haveAttacked==1){
		return;
	}
	event.currentTarget.filters = [hilightFilter];
	if(!isCastingSkill && isAttacking && playerForce==1){
		cursorID = CursorManager.setCursor(attackCursor,2,-20,-20);
	}
}

/**
 * 城防鼠标移开
 */
private function defenceMouseOut(event:MouseEvent):void{
	if(event.currentTarget.data.haveAttacked==1){
		return;
	}
	event.currentTarget.filters = null;
	if(!isCastingSkill){
		CursorManager.removeCursor(cursorID);
	}
}

/**
 * 显示城防ToolTip
 */
private function showCityDefenseTooltip(event:ToolTipEvent):void{
	currentCityDefense = event.currentTarget.data;
	event.toolTip = new CityDefenseTooltip(currentCityDefense);
}

/**
 * 点击城市防御
 */
private function clickCityDefense(event:MouseEvent):void{
	
	//如果当前行动方与玩家势力不同，则终止操作
	if(currentOperator!=playerForce){
		return;
	}
	
	if(event.currentTarget.data.haveAttacked==1){
		return;
	}
	
	removeCityDefenseRangeGrid();
	
	isAttacking = true;
	
	currentCityDefenseCanvas = event.currentTarget;
	selectedCityDefense = event.currentTarget.data;
	
	//清空选中军队
	selectedArmyInfo = null;
	currentArmyCanvas = null;
	
	var posX:int = event.currentTarget.x;
	var posY:int = event.currentTarget.y;
	
	var cityDefenseAttribute:Object = CityDefenseConstant.CITY_DEFENSE_ATTRIBUTE_LIST[selectedCityDefense.type];
	
	var i:int,j:int;
	
	//绘制攻击范围
	for(i=1;i<=cityDefenseAttribute.range;i++){
		
		//上
		if(isPositionWithin(posX,posY-i*GRID_SIZE))
			drawCityDefenseRangeGrid(posX,posY-i*GRID_SIZE);
		
		//下
		if(isPositionWithin(posX,posY+i*GRID_SIZE))
			drawCityDefenseRangeGrid(posX,posY+i*GRID_SIZE);
		
		//左
		if(isPositionWithin(posX-i*GRID_SIZE,posY))
			drawCityDefenseRangeGrid(posX-i*GRID_SIZE,posY);
		
		//右
		if(isPositionWithin(posX+i*GRID_SIZE,posY))
			drawCityDefenseRangeGrid(posX+i*GRID_SIZE,posY);
		
	}
	
	for(i=1;i<=cityDefenseAttribute.range-1;i++){
		
		//左上
		for(j=1;j<=cityDefenseAttribute.range-i;j++){
			if(isPositionWithin(posX-i*GRID_SIZE,posY+j*GRID_SIZE)){
				drawCityDefenseRangeGrid(posX-i*GRID_SIZE,posY+j*GRID_SIZE);
			}
		}
		
		//左下
		for(j=1;j<=cityDefenseAttribute.range-i;j++){
			if(isPositionWithin(posX-i*GRID_SIZE,posY-j*GRID_SIZE)){
				drawCityDefenseRangeGrid(posX-i*GRID_SIZE,posY-j*GRID_SIZE);
			}
		}
		
		//右上
		for(j=1;j<=cityDefenseAttribute.range-i;j++){
			if(isPositionWithin(posX+i*GRID_SIZE,posY+j*GRID_SIZE)){
				drawCityDefenseRangeGrid(posX+i*GRID_SIZE,posY+j*GRID_SIZE);
			}
		}
		
		//右下
		for(j=1;j<=cityDefenseAttribute.range-i;j++){
			if(isPositionWithin(posX+i*GRID_SIZE,posY-j*GRID_SIZE)){
				drawCityDefenseRangeGrid(posX+i*GRID_SIZE,posY-j*GRID_SIZE);
			}
		}
		
	}
	
}

/**
 * 绘制城市防御攻击范围网格
 */
private function drawCityDefenseRangeGrid(x:int,y:int):void{
	
	var image:Image = new Image();
	image.source = new RANGE_GRID_IMAGE();
	image.x = x;
	image.y = y;
	image.width = GRID_SIZE;
	image.height = GRID_SIZE;
	
	image.addEventListener(MouseEvent.MOUSE_OVER,cityDefenseRangeMouseOver);
	image.addEventListener(MouseEvent.MOUSE_OUT,cityDefenseRangeMouseOut);
	
	currentCityDefenseGridList.addItem(image);
	
	canvasRange.addChild(image);
}

/**
 * 移除城市防御攻击范围网格
 */
private function removeCityDefenseRangeGrid():void{
	
	isAttacking = false;
	
	for(var i:int=0;i<currentCityDefenseGridList.length;i++){
		canvasRange.removeChild(currentCityDefenseGridList.getItemAt(i) as Image);
	}
	currentCityDefenseGridList.removeAll();
}

/**
 * 城市防御攻击范围鼠标移上
 */
private function cityDefenseRangeMouseOver(event:MouseEvent):void{
	event.currentTarget.filters = [hilightFilter];
}

/**
 * 城市防御攻击范围鼠标移开
 */
private function cityDefenseRangeMouseOut(event:MouseEvent):void{
	event.currentTarget.filters = null;
}

/**
 * 攻击士兵
 */
private function defenseAttackArmy():void{
	
}

