// ActionScript file
import com.adobe.serialization.json.JSON;
import com.hifong.war.vo.BattleArmyVO;
import com.hifong.war.vo.CityDefenseVO;

import mx.collections.ArrayCollection;

/**
 * 点击军队
 */
private function armyClick(event:MouseEvent):void{
	
	//如果当前行动方与玩家势力不同，则终止操作
	if(currentOperator!=playerForce){
		return;
	}

	//判断是否为释放技能
	if(isCastingSkill){
		var targetArmyForce:int = playerForce==1?1:2;
					/**
					 * 向服务器发送数据
					 */
					 var message:Object=new Object();
					 message.battleID=battleID;
					 message.operator=playerForce;
					 message.type=8;
					 message.heroSkillID=castingHeroSkillID;
					 message.targetArmyForce=targetArmyForce;
					 message.targetArmyNO=event.currentTarget.name;
					 sendData(JSON.encode(message));	
		isCastingSkill = false;
		haveCastedSkill = true;
		if(playerForce==1){
			battleInfo.militaryAttacker.cityHero.stamina = battleInfo.militaryAttacker.cityHero.stamina-castingSkillCostStamina;
		}else if(playerForce==2){
			battleInfo.militaryDefender.cityHero.stamina = battleInfo.militaryDefender.cityHero.stamina-castingSkillCostStamina;
		}
		btnCastSkill.visible = true;
		btnCancelCastSkill.visible = false;
		CursorManager.removeCursor(cursorID);
		return;
	}

	if(currentArmyCanvas!=null){                                                                                                                                                                                                                                                                                                                                                  
		if(selectedArmyInfo.attackType==0){
			currentArmyCanvas.filters = null;
		}
	}
	
	currentArmyCanvas = event.currentTarget;
	trace(currentArmyCanvas);
	//清空选中城市防御
	selectedCityDefense = null;
	removeCityDefenseRangeGrid();
	
	var army:Object;
	
	if(playerForce==1){
		army = battleArmyList_attacker.getItemAt(event.currentTarget.name);
	}else if(playerForce==2){
		army = battleArmyList_defender.getItemAt(event.currentTarget.name);
	}
	
	selectedArmyInfo = army;
	
	var i:int;
	//移除路线网格
	for(i=0;i<currentRouteGridList.length;i++){
		canvasRoute.removeChild(currentRouteGridList.getItemAt(i) as Image);
	}
	currentRouteGridList.removeAll();
	//移除攻击范围网格
	for(i=0;i<currentRangeGridList.length;i++){
		canvasRange.removeChild(currentRangeGridList.getItemAt(i) as Image);
	}
	currentRangeGridList.removeAll();
	//隐藏弹出层
	hidePopupCanvas();
	
	//军队未移动并且未作任何操作
	if(army.haveMoved==false && army.attackType==0){
		showArmyAvailableRoute();
		showPopupCanvas();
	}
	
	//军队已移动但未选择进攻或防御
	if(army.haveMoved==true && army.attackType==0){
		showArmyAvailableRange(currentArmyCanvas.x,currentArmyCanvas.y);
		showPopupCanvas();
	}
	
}

/**
 * 军队攻击
 */
private function armyAttack(event:MouseEvent):void{
	
	//如果当前行动方与玩家势力不同，则终止操作
	if(currentOperator!=playerForce){
		return;
	}
	
	var message:Object=new Object();
	
	//判断是否为释放技能
	if(isCastingSkill){
		
		if(event.currentTarget.data is CityDefenseVO){
			MsgBox.showMessage("无法对城市防御释放技能。");
			return;
		}
		
		var targetArmyForce:int = playerForce==1?2:1;
					/**
					 * 向服务器发送数据
					 */
					message=new Object();
					 message.battleID=battleID;
					 message.operator=playerForce;
					 message.type=8;
					 message.heroSkillID=castingHeroSkillID;
					 message.targetArmyForce=targetArmyForce;
					 message.targetArmyNO=event.currentTarget.name
					 sendData(JSON.encode(message));	
		isCastingSkill = false;
		haveCastedSkill = true;
		if(playerForce==1){
			battleInfo.militaryAttacker.cityHero.stamina = battleInfo.militaryAttacker.cityHero.stamina-castingSkillCostStamina;
		}else if(playerForce==2){
			battleInfo.militaryDefender.cityHero.stamina = battleInfo.militaryDefender.cityHero.stamina-castingSkillCostStamina;
		}
		btnCastSkill.visible = true;
		btnCancelCastSkill.visible = false;
		CursorManager.removeCursor(cursorID);
		return;
	}
	
	if(isAttacking){
		//如果当前军队未攻击
		if(selectedArmyInfo!=null && selectedArmyInfo.attackType==0 || selectedCityDefense!=null){
			
			if(event.currentTarget.data is CityDefenseVO){
				
				//士兵攻击城市防御
				if(event.currentTarget.data.type==1){
					//围墙，坐标需特殊判断
					if(Math.abs(selectedArmyInfo.posX-event.currentTarget.data.posX)<=selectedArmyInfo.army.range*GRID_SIZE){
						currentArmyCanvas.filters = [greyFilter];
						selectedArmyInfo.attackType = 1;
						removeRouteGrid();
						removeRangeGrid();
						hidePopupCanvas();
						
						/**
						 * 向服务器发送数据
						 * 参数说明
						 * battleID 战斗编号
						 * type 类型(6.士兵攻击城市防御)
						 * operator 操作者(1.进攻方 2.防守方)
						 * armyNO 士兵编号(当前军队士兵索引)
						 * cityDefenseNO 城市防御编号
						 * cityDefenseType 城市防御类型
						 */
					message=new Object();
					 message.battleID=battleID;
					 message.operator=playerForce;
					 message.type=6;
					 message.armyNO=currentArmyCanvas.name;
					 message.cityDefenseNO=event.currentTarget.data.cityDefenseNO;
					 message.cityDefenseType=event.currentTarget.data.type;
					sendData(JSON.encode(message));	
					}
				}else{
					//如果在攻击范围内
					if((Math.abs(selectedArmyInfo.posX-event.currentTarget.data.posX)<=selectedArmyInfo.army.range*GRID_SIZE && selectedArmyInfo.posY==event.currentTarget.data.posY) || (Math.abs(selectedArmyInfo.posY-event.currentTarget.data.posY)<=selectedArmyInfo.army.range*GRID_SIZE && selectedArmyInfo.posX==event.currentTarget.data.posX)){
						currentArmyCanvas.filters = [greyFilter];
						selectedArmyInfo.attackType = 1;
						removeRouteGrid();
						removeRangeGrid();
						hidePopupCanvas();
						
						/**
						 * 向服务器发送数据
						 * 参数说明
						 * battleID 战斗编号
						 * type 类型(6.士兵攻击城市防御)
						 * operator 操作者(1.进攻方 2.防守方)
						 * armyNO 士兵编号(当前军队士兵索引)
						 * cityDefenseNO 城市防御编号
						 * cityDefenseType 城市防御类型
						 */
					  message=new Object();
					 message.battleID=battleID;
					 message.operator=playerForce;
					 message.type=6;
					 message.armyNO=currentArmyCanvas.name;
					 message.cityDefenseNO=event.currentTarget.data.cityDefenseNO;
					 message.cityDefenseType=event.currentTarget.data.type;
					 sendData(JSON.encode(message));	
					}
				}
				
			}else{
				
				var targetArmyPosX:int = event.currentTarget.x;
				var targetArmyPosY:int = event.currentTarget.y;
				var targetBattleArmy:BattleArmyVO = null;
	
				if(selectedCityDefense!=null){
					
					//城市防御攻击士兵
					var cityDefenseAttribute:Object = CityDefenseConstant.CITY_DEFENSE_ATTRIBUTE_LIST[selectedCityDefense.type];
					
					targetBattleArmy = battleArmyList_attacker.getItemAt(event.currentTarget.name) as BattleArmyVO;
					
					if(selectedCityDefense.type!=4 && targetBattleArmy.army.type==3){
						MsgBox.showMessage("当前城市防御无法攻击空中单位。");
						return;
					}
					
					if(selectedCityDefense.type==4 && targetBattleArmy.army.type!=3){
						MsgBox.showMessage("当前城市防御无法攻击地面单位。");
						return;
					}
					
					//如果在攻击范围内
					if((Math.abs(selectedCityDefense.posX-targetArmyPosX)<=cityDefenseAttribute.range*GRID_SIZE) && (Math.abs(selectedCityDefense.posY-targetArmyPosY)<=cityDefenseAttribute.range*GRID_SIZE)){
						currentCityDefenseCanvas.filters = [greyFilter];
						selectedCityDefense.haveAttacked = 1;
						removeCityDefenseRangeGrid();
						
						/**
						 * 向服务器发送数据
						 * 参数说明
						 * battleID 战斗编号
						 * type 类型(7.城市防御攻击士兵)
						 * operator 操作者(1.进攻方 2.防守方)
						 * cityDefenseNO 城市防御编号
						 * cityDefenseType 城市防御类型
						 * targetArmyNO 目标士兵编号(当前军队士兵索引)
						 */
						message=new Object();
						 message.battleID=battleID;
						 message.type=7;
						 message.operator=playerForce;
						 message.cityDefenseNO=selectedCityDefense.cityDefenseNO;
						 message.cityDefenseType=selectedCityDefense.type;
						 message.targetArmyNO=event.currentTarget.name;
						 this.sendData(JSON.encode(message));
					}
					
				}else{
	
					//士兵攻击士兵
					
					//判断当前目标是否可以攻击目标士兵（地面，空中）
					if(playerForce==1){
						//进攻方
						targetBattleArmy = battleArmyList_defender.getItemAt(event.currentTarget.name) as BattleArmyVO;
					}else if(playerForce==2){
						//防守方
						targetBattleArmy = battleArmyList_attacker.getItemAt(event.currentTarget.name) as BattleArmyVO;
					}
					
					if(selectedArmyInfo.army.attackType==1 && targetBattleArmy.army.type==3){
						MsgBox.showMessage("当前部队无法攻击空中单位。");
						return;
					}else if(selectedArmyInfo.army.attackType==2 && (targetBattleArmy.army.type==1 || targetBattleArmy.army.type==2)){
						MsgBox.showMessage("当前部队无法攻击地面单位。");
						return;
					}
					
					//如果在攻击范围内
					if((Math.abs(selectedArmyInfo.posX-targetArmyPosX)<=selectedArmyInfo.army.range*GRID_SIZE && selectedArmyInfo.posY==targetArmyPosY) || (Math.abs(selectedArmyInfo.posY-targetArmyPosY)<=selectedArmyInfo.army.range*GRID_SIZE && selectedArmyInfo.posX==targetArmyPosX)){
	
						removeRangeGrid();
						hidePopupCanvas();
						//计算攻击方向 ==========================
					
					var direction:int=getDirection(currentArmyCanvas.x,currentArmyCanvas.y,event.currentTarget.x,event.currentTarget.y);
						currentArmyCanvas.fight(direction);
						//计算攻击方向end========================
						/**
						 * 向服务器发送数据
						 * 参数说明
						 * battleID 战斗编号
						 * type 类型(2.攻击 )
						 * operator 操作者(1.进攻方 2.防守方)
						 * armyNO 士兵编号(当前军队士兵索引)
						 * targetArmyNO 目标士兵编号(当前军队士兵索引)
						 */
						message=new Object();
						 message.battleID=battleID;
						 message.type=2;
						 message.operator=playerForce;
						 message.armyNO=currentArmyCanvas.name;
						 message.targetArmyNO=event.currentTarget.name;
						 this.sendData(JSON.encode(message));
					}
				}
			}
		}
	}
	
}

/**
 * 军队防御
 */
private function armyDefense():void{
	
	removeRouteGrid();
	removeRangeGrid();
	hidePopupCanvas();
	
	isAttacking = false;
	
	/**
	 * 向服务器发送数据
	 * 参数说明
	 * battleID 战斗编号
	 * type 类型(3.防御)
	 * operator 操作者(1.进攻方 2.防守方)
	 * armyNO 士兵编号(当前军队士兵索引)
	 */
	 var message:Object=new Object();
	 message.battleID=battleID;
	 message.type=3;
	 message.operator=playerForce;
	 message.armyNO=currentArmyCanvas.name;
	 this.sendData(JSON.encode(message));
	
}
////////////////////////////////////////////////////////////////////////////////////////////////军队移动///////////////////////////////////////////////////
/**
 * 军队移动
 */
private function moveArmy(event:MouseEvent):void{
	
	var posX:int = event.currentTarget.x;
	var posY:int = event.currentTarget.y;
	//移除可移动网格
	removeRouteGrid();
	//移除攻击网格
	removeRangeGrid();
	//隐藏操作提示
	hidePopupCanvas();
	
	/**
	 * 向服务器发送数据
	 * 参数说明
	 * battleID 战斗编号
	 * type 类型(1.移动)
	 * operator 操作者(1.进攻方 2.防守方)
	 * armyNO 军队编号(当前军队士兵索引)
	 * posX 移动X坐标
	 * posY 移动Y坐标
	 */
	 var message:Object=new Object();
	 message.battleID=battleID;
	 message.type=1;
	 message.operator=playerForce;
	 message.armyNO=currentArmyCanvas.name;
	 message.posX=posX;
	 message.posY=posY;
	 this.sendData(JSON.encode(message));
}

/**
 * 当前位置是否合法(超出边界、存在进攻方士兵、存在防守方士兵、存在城防)
 */
private function isPositionAvailable(posX:int,posY:int):Boolean{
	
	//判断是否超出边界
	//上
	if(posY<0)
		return false;
	//下
	if(posY>(Y_GRID_AMOUNT+1)*GRID_SIZE)
		return false;
	//左
	if(posX<0)
		return false;
	//右
	if(posX>(X_GRID_AMOUNT+1)*GRID_SIZE)
		return false;
	
	var i:int;
	
	//判断是否有进攻方士兵存在
	for(i=0;i<battleArmyList_attacker.length;i++){
		if(battleArmyList_attacker.getItemAt(i)!=null){
			if(posX==battleArmyList_attacker.getItemAt(i).posX && posY==battleArmyList_attacker.getItemAt(i).posY && battleArmyList_attacker.getItemAt(i).amount>0){
				return false;
			}
		}
	}

	//判断是否有防守方士兵存在
	for(i=0;i<battleArmyList_defender.length;i++){
		if(battleArmyList_defender.getItemAt(i)!=null){
			if(posX==battleArmyList_defender.getItemAt(i).posX && posY==battleArmyList_defender.getItemAt(i).posY && battleArmyList_defender.getItemAt(i).amount>0){
				return false;
			}
		}
	}
	
	//判断是否有城防存在
	for(i=0;i<cityDefenseList.length;i++){
		if(cityDefenseList.getItemAt(i)!=null && posX==cityDefenseList.getItemAt(i).posX && posY==cityDefenseList.getItemAt(i).posY && cityDefenseList.getItemAt(i).num>0){
			return false;
		}
	}
	
	return true;
}

/**
 * 显示军队可用移动路线 
 */
private function showArmyAvailableRoute():void{

	var army:Object;
	
	var battleArmyList:ArrayCollection;

	if(playerForce==1){
		army = battleArmyList_attacker.getItemAt(currentArmyCanvas.name);
		battleArmyList = battleArmyList_attacker;
	}else if(playerForce==2){
		army = battleArmyList_defender.getItemAt(currentArmyCanvas.name);
		battleArmyList = battleArmyList_defender;
	}
	
	//如果移动过则不显示路线
	if(army.haveMoved==true){
		return;
	}
	
	removeRouteGrid();
	
	currentArmyInfo = army;
	selectedArmyInfo = army;
	
	currentArmyCanvas.filters = [hilightFilter];
	
	var posX:int = army.posX;
	var posY:int = army.posY;
	
	var hasArmy:Boolean;
	var i:int,j:int;
	
	//动态创建上下左右4个方向可用移动路线
	for(i=1;i<=army.army.speed;i++){
		
		//上
		if(isPositionAvailable(posX,posY-i*GRID_SIZE))
			drawRouteGrid(posX,posY-i*GRID_SIZE);
				
		//下
		if(isPositionAvailable(posX,posY+i*GRID_SIZE))
			drawRouteGrid(posX,posY+i*GRID_SIZE);
		
		//左
		if(isPositionAvailable(posX-i*GRID_SIZE,posY))
			drawRouteGrid(posX-i*GRID_SIZE,posY);
		
		//右
		if(isPositionAvailable(posX+i*GRID_SIZE,posY)){
			//如果围墙存在并且当前操作者为进攻方，判断是否达到围墙边缘
			if(fence!=null && fence.num>0 && currentOperator==1){
				if(!(posX+(i-1)*GRID_SIZE>=(X_GRID_AMOUNT-3)*GRID_SIZE)){
					drawRouteGrid(posX+i*GRID_SIZE,posY);
				}
			}else{
				drawRouteGrid(posX+i*GRID_SIZE,posY);
			}
		}
		
	}
	
	for(i=1;i<=army.army.speed-1;i++){
		
		//左上
		for(j=1;j<=army.army.speed-i;j++){
			if(isPositionAvailable(posX-i*GRID_SIZE,posY+j*GRID_SIZE)){
				drawRouteGrid(posX-i*GRID_SIZE,posY+j*GRID_SIZE);
			}
		}
		
		//左下
		for(j=1;j<=army.army.speed-i;j++){
			if(isPositionAvailable(posX-i*GRID_SIZE,posY-j*GRID_SIZE)){
				drawRouteGrid(posX-i*GRID_SIZE,posY-j*GRID_SIZE);
			}
		}
		
		//右上
		for(j=1;j<=army.army.speed-i;j++){
			if(isPositionAvailable(posX+i*GRID_SIZE,posY+j*GRID_SIZE)){
				if(fence!=null && fence.num>0 && currentOperator==1){
					if(!(posX+(i-1)*GRID_SIZE>=(X_GRID_AMOUNT-3)*GRID_SIZE)){
						drawRouteGrid(posX+i*GRID_SIZE,posY+j*GRID_SIZE);
					}
				}else{
					drawRouteGrid(posX+i*GRID_SIZE,posY+j*GRID_SIZE);
				}
			}
		}
		
		//右下
		for(j=1;j<=army.army.speed-i;j++){
			if(isPositionAvailable(posX+i*GRID_SIZE,posY-j*GRID_SIZE)){
				if(fence!=null && fence.num>0 && currentOperator==1){
					if(!(posX+(i-1)*GRID_SIZE>=(X_GRID_AMOUNT-3)*GRID_SIZE)){
						drawRouteGrid(posX+i*GRID_SIZE,posY-j*GRID_SIZE);
					}
				}else{
					drawRouteGrid(posX+i*GRID_SIZE,posY-j*GRID_SIZE);
				}
			}
		}
		
	}
}

/**
 * 当前位置是否在战斗范围内
 */
private function isPositionWithin(posX:int,posY:int):Boolean{
	//上
	if(posY<0)
		return false;
	//下
	if(posY>(Y_GRID_AMOUNT+1)*GRID_SIZE)
		return false;
	//左
	if(posX<0)
		return false;
	//右
	if(posX>(X_GRID_AMOUNT+1)*GRID_SIZE)
		return false;
		
	return true;
}

/**
 * 显示军队可用攻击范围
 */
private function showArmyAvailableRange(x:int,y:int):void{
	isAttacking = true;
	for(var i:int=1;i<=selectedArmyInfo.army.range;i++){
		//上
		if(isPositionWithin(x,y-i*GRID_SIZE))
			drawRangeGrid(x,y-i*GRID_SIZE);
		//下
		if(isPositionWithin(x,y+i*GRID_SIZE))
			drawRangeGrid(x,y+i*GRID_SIZE);
		//左
		if(isPositionWithin(x-i*GRID_SIZE,y))
			drawRangeGrid(x-i*GRID_SIZE,y);
		//右
		if(isPositionWithin(x+i*GRID_SIZE,y))
			drawRangeGrid(x+i*GRID_SIZE,y);
	}
}

/**
 * 军队攻击操作
 */
private function doAttack():void{
	//移除路线网格
	removeRouteGrid();
	
	//移除攻击范围网格
	for(var i:int;i<currentRangeGridList.length;i++){
		canvasRange.removeChild(currentRangeGridList.getItemAt(i) as Image);
	}
	currentRangeGridList.removeAll();

	showArmyAvailableRange(selectedArmyInfo.posX,selectedArmyInfo.posY);
	
	hidePopupCanvas();
}

/**
 * 开启自动战斗
 */
private function startAutoBattle():void{
	
	btnAutoBattle.visible = false;
	btnCancelAutoBattle.visible = true;
	
	isAutoBattle = true;
	autoBattleLeftSecond = autoBattleInterval;
	autoBattleHandleID = setInterval(autoBattle,1000);
	
//	autoBattle();
	
	canvasAutoBattleInfo.visible = true;
}

/**
 * 取消自动战斗
 */
private function cancelAutoBattle():void{
	
	btnAutoBattle.visible = true;
	btnCancelAutoBattle.visible = false;
	
	isAutoBattle = false;
	clearInterval(autoBattleHandleID);
	
	canvasAutoBattleInfo.visible = false;
}

/**
 * 自动战斗
 */
private function autoBattle():void{
	
	if(currentOperator!=playerForce){
		autoBattleLeftSecond = autoBattleInterval;
		return;
	}
	
	if(autoBattleLeftSecond>1){
		autoBattleLeftSecond--;
		return;
	}else{
		autoBattleLeftSecond = autoBattleInterval;
	}
	
	var militaryArmyList:ArrayCollection;
	
	if(playerForce==1){
		//进攻方
		militaryArmyList = battleArmyList_attacker;
	}else{
		//防守方
		militaryArmyList = battleArmyList_defender;
	}
	
	for(var i:int;i<militaryArmyList.length;i++){
		if(militaryArmyList.getItemAt(i)!=null && militaryArmyList.getItemAt(i).amount>0){
			if(militaryArmyList.getItemAt(i).haveMoved==0 && militaryArmyList.getItemAt(i).attackType==0){
				//军队未进行操作
				autoBattleArmyInfo = militaryArmyList.getItemAt(i);
				var message:Object=new Object();
				message.battleID=battleID;
				message.type=21;
				message.operator=playerForce;
				message.armyNO=i;
				this.sendData(JSON.encode(message));
				//将攻击类型设置为非0值，防止网络延迟导致士兵多次行动
				militaryArmyList.getItemAt(i).attackType = 9;
				return;
			}
		}
	}
	
	//如果已全部行动则结束回合
	finishRound();
	
}

/**
 * 野怪自动战斗(如果进攻方在战场，则野怪每5秒有一只部队行动，全部行动完则结束回合)
 */
private function mapMonsterAutoBattle():void{
	
	if(currentOperator==playerForce){
		return;
	}
	
	for(var i:int;i<battleArmyList_defender.length;i++){
		if(battleArmyList_defender.getItemAt(i)!=null && battleArmyList_defender.getItemAt(i).amount>0){
			if(battleArmyList_defender.getItemAt(i).attackType==0){
				//军队未进行操作
				var message:Object=new Object();
				message.battleID=battleID;
				message.type=21;
				message.operator=2;
				message.armyNO=i;
				this.sendData(JSON.encode(message));
				//将攻击类型设置为非0值，防止网络延迟导致士兵多次行动
				battleArmyList_defender.getItemAt(i).attackType = 9;
				return;
			}
		}
	}
	
	//如果已全部行动则结束回合
	mapMonsterFinishRound();
}