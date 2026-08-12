// ActionScript file
import com.hifong.war.view.battle.ArmyElement;
import com.hifong.war.vo.BattleArmyVO;
import com.hifong.war.vo.CityDefenseVO;

import flash.events.Event;


private function initBattleInfo(event:ObjectProxy):void{
	
	if(model.battleInfo==null){
		return;
	}else{
		battleWatcher.unwatch();
	}
	
	battleInfo = model.battleInfo;
	
	military_attacker = battleInfo.militaryAttacker;
	military_defender = battleInfo.militaryDefender;
	
	//初始化进攻方军队信息
	initMilitaryAttackerInfo();
	//初始化防守方军队信息
	initMilitaryDefenderInfo();
	
	if(playerForce==1){
		haveCastedSkill = battleInfo.militaryAttacker.haveCastedSkill;
	}else if(playerForce==2){
		haveCastedSkill = battleInfo.militaryDefender.haveCastedSkill;
	}
	
	if(battleInfo.type==2){
		//如果为攻城战则初始化城防信息
		initCityDefense();
	}
	
	//初始化战场坐标
	stagePosX = model.battleInfo.stagePosX;
	stagePosY = model.battleInfo.stagePosY;
	
	//初始化回合剩余时间
	roundTime = ROUND_TIME - (model.serverTime.time - model.battleInfo.preRoundFinishTime.time)/1000;
	
	//初始化当前操作玩家
	currentOperator = model.battleInfo.round%2==1?1:2;
	
	//初始化回合
	round = (model.battleInfo.round+1)/2;
	
	var i:int;
	if(currentOperator==1)
	{
		imageStarAttacker.visible = true;
		imageLightAttacker.visible = true;
		
		imageAttackersTrun.visible = true;
		flash.utils.setTimeout(function ():void{
			imageAttackersTrun.visible = false;
		},3000);
	}else if(currentOperator==2){
		imageStarDefender.visible = true;
		imageLightDefender.visible = true;
		
		imageDefendersTrun.visible = true;
		flash.utils.setTimeout(function ():void{
			imageDefendersTrun.visible = false;
		},3000);
	}
	
	//初始化事件文本区域
	addTextareaRound(round);
	
	//初始化倒计时
	timer = new Timer(1000);
	timer.addEventListener(TimerEvent.TIMER,handleRoundTime);
	timer.start();
	
	//添加SWFLoader播放完成后隐藏，防止覆盖士兵事件响应
	effectSWFLoader.addEventListener(Event.COMPLETE,hideSWFLoader);
	
	//如果是掠夺战并且当前操作者为防守方(野怪)，则开启自动战斗
	if(battleInfo.type==1 && currentOperator==2){
		mapMonsterAutoBattleHandleID = setInterval(mapMonsterAutoBattle,MAP_MONSTER_INTERVAL);
	}
	
}

public function hideSWFLoader(event:Event):void{
	setTimeout(function ():void{
		effectSWFLoader.visible = false;
	},actionDelay+500);
}

/**
 * 初始化进攻方信息
 */
private function initMilitaryAttackerInfo():void{
	if(military_attacker!=null){
		battleArmyList_attacker = military_attacker.battleArmyList;
		for(var i:int=0;i<battleArmyList_attacker.length;i++){
			
			var battleArmy:BattleArmyVO = battleArmyList_attacker.getItemAt(i) as BattleArmyVO;
			
			if(battleArmy!=null){
				var url:String=ARMY_IMAGE_PATH_PREFIX + battleInfo.militaryAttacker.cityInfo.country + "/L/" + battleArmy.army.armyID + ".swf";
				var canvas:ArmyElement=new ArmyElement(canvasArmy,url,GRID_SIZE,battleArmy.posX/GRID_SIZE,battleArmy.posY/GRID_SIZE);
				canvas.addEventListener(ArmyElement.WALK_ARRIVE,onArmyWalkArrive);
				canvas.addEventListener(ArmyElement.FIGHT_OVER,onArmyFightOver);
				canvas.name = i.toString();
				//data属性代表当前Canvas进攻方或防守方
				canvas.data = 1;
				battleArmyCanvasList_attacker.addItem(canvas);
				
				canvas.setStyle("hideEffect",fadeOut);
				
				if(battleArmyList_attacker.getItemAt(i).attackType!=0){
					canvas.filters = [greyFilter];
				}
				
				if(battleArmyList_attacker.getItemAt(i).amount==0){
					canvas.visible = false;
				}
				
				//将Label的Text与进攻战斗士兵列表的数量绑定
				BindingUtils.bindProperty(canvas.txt,"text",battleArmyList_attacker.getItemAt(i),"amount");
				canvas.txt.text = battleArmy.amount.toString();
				
				canvas.toolTip = " ";
				canvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showArmyTooltip);
				
				canvas.addEventListener(MouseEvent.MOUSE_OVER,armyMouseOver); 
				canvas.addEventListener(MouseEvent.MOUSE_OUT,armyMouseOut);
				if(playerForce==1){
					canvas.addEventListener(MouseEvent.CLICK,armyClick);
				}else if(playerForce==2){
					canvas.addEventListener(MouseEvent.CLICK,armyAttack);
				}
			}else{
				battleArmyCanvasList_attacker.addItem(null);
			}
		}
	}
}

/**
 * 初始化防守方信息
 */
private function initMilitaryDefenderInfo():void{
	if(military_defender!=null){
		battleArmyList_defender = military_defender.battleArmyList;
		for(var i:int=0;i<battleArmyList_defender.length;i++){
			
			var battleArmy:BattleArmyVO = battleArmyList_defender.getItemAt(i) as BattleArmyVO;
			
			if(battleArmy!=null){
				var url:String;
				if(battleInfo.type==1){
					url = ARMY_IMAGE_PATH_PREFIX + "monster/" + "R/" + battleArmy.army.armyID + ".swf";
				}else if(battleInfo.type==2){
					url = ARMY_IMAGE_PATH_PREFIX + battleInfo.militaryDefender.cityInfo.country + "/R/" + battleArmy.army.armyID + ".swf";
				}	
				var canvas:ArmyElement=new ArmyElement(canvasArmy,url,GRID_SIZE,battleArmy.posX/GRID_SIZE,battleArmy.posY/GRID_SIZE,3);
				canvas.name = i.toString();
				//data属性代表当前Canvas进攻方或防守方
				canvas.data = 2;
				battleArmyCanvasList_defender.addItem(canvas);
				
//				canvas.setStyle("moveEffect",moveEffect);
				canvas.setStyle("hideEffect",fadeOut);
				
				if(battleArmy.attackType!=0){
					canvas.filters = [greyFilter];
				}
				
				if(battleArmy.amount==0){
					canvas.visible = false;
				}
				
//				var label:Label = new Label();
//				label.x = 0;
//				label.y = 35;
//				label.width = 50;
//				label.height = 15;
//				label.setStyle("color","#FFFF00");
//				label.setStyle("fontFamily","Arial");
//				label.setStyle("fontSize",10);
//				label.setStyle("textAlign","right");
				//将Label的Text与防守战斗士兵列表的数量绑定
				BindingUtils.bindProperty(canvas.txt,"text",battleArmyList_defender.getItemAt(i),"amount");
				canvas.txt.text = battleArmy.amount.toString();
				
//				var image:Image = new Image();
//				image.x = 0;
//				image.y = 0;
//				image.height = 50;
//				image.width = 50;
				
				
				
//				canvas.addChild(image);
//				canvas.addChild(label);
				
				canvas.toolTip = " ";
				canvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showArmyTooltip);
				
				canvas.addEventListener(MouseEvent.MOUSE_OVER,armyMouseOver);
				canvas.addEventListener(MouseEvent.MOUSE_OUT,armyMouseOut);
				if(playerForce==2){
					canvas.addEventListener(MouseEvent.CLICK,armyClick);
				}else if(playerForce==1){
					canvas.addEventListener(MouseEvent.CLICK,armyAttack);
				}
			}else{
				battleArmyCanvasList_defender.addItem(null);
			}
		}
	}
}

/**
 * 初始化城防信息
 */
private function initCityDefense():void{
	
	var num:int,defenseID:int;
	
	//初始化城防信息
	var haveFence:Boolean,haveBunker:Boolean,haveGun:Boolean,haveAntigun:Boolean;
	for(var i:int=0;i<model.battleInfo.cityDefenseList.length;i++){
		switch(model.battleInfo.cityDefenseList.getItemAt(i).defenseID){
			case 1:
				
				haveFence = true;
			
				fence = model.battleInfo.cityDefenseList.getItemAt(i);
				fence.cityDefenseNO = 0;
				fence.num = model.battleInfo.cityDefenseAmountArray[0];
				fence.posX = (X_GRID_AMOUNT-2) * GRID_SIZE;
				fence.posY = 0;
				fence.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[0];
				cityDefenseList.addItem(fence);
				break;
			case 2:
			
				haveBunker = true;
			
				defenseID = model.battleInfo.cityDefenseList.getItemAt(i).defenseID;
				
				if(model.battleInfo.cityDefenseAmountArray[1]>0){
					bunkerTop = new CityDefenseVO;
					bunkerTop.defenseID = defenseID;
					bunkerTop.cityDefenseNO = 1;
					bunkerTop.num = model.battleInfo.cityDefenseAmountArray[1]
					bunkerTop.posX = (X_GRID_AMOUNT-3) * GRID_SIZE;
					bunkerTop.posY = GRID_SIZE*2;
					bunkerTop.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[1];
					cityDefenseList.addItem(bunkerTop);
				}else{
					cityDefenseList.addItem(null);
				}
				
				if(model.battleInfo.cityDefenseAmountArray[2]>0){
					bunkerBottom = new CityDefenseVO;
					bunkerBottom.defenseID = defenseID;
					bunkerBottom.cityDefenseNO = 2;
					bunkerBottom.num = model.battleInfo.cityDefenseAmountArray[2];
					bunkerBottom.posX = (X_GRID_AMOUNT-3) * GRID_SIZE;
					bunkerBottom.posY = GRID_SIZE*5;
					bunkerBottom.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[2];
					cityDefenseList.addItem(bunkerBottom);
				}else{
					cityDefenseList.addItem(null);
				}
				break;
			case 3:
			
				haveGun = true;
			
				defenseID = model.battleInfo.cityDefenseList.getItemAt(i).defenseID;
				
				if(model.battleInfo.cityDefenseAmountArray[3]>0){
					gunTop = new CityDefenseVO;
					gunTop.defenseID = defenseID;
					gunTop.cityDefenseNO = 3;
					gunTop.num = model.battleInfo.cityDefenseAmountArray[3];
					gunTop.posX = (X_GRID_AMOUNT-1) * GRID_SIZE;
					gunTop.posY = GRID_SIZE*2;
					gunTop.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[3];
					cityDefenseList.addItem(gunTop);
				}else{
					cityDefenseList.addItem(null);
				}
				
				if(model.battleInfo.cityDefenseAmountArray[4]>0){
					gunBottom = new CityDefenseVO;
					gunBottom.defenseID = defenseID;
					gunBottom.cityDefenseNO = 4;
					gunBottom.num = model.battleInfo.cityDefenseAmountArray[4];
					gunBottom.posX = (X_GRID_AMOUNT-1) * GRID_SIZE;
					gunBottom.posY = GRID_SIZE*5;
					gunBottom.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[4];
					cityDefenseList.addItem(gunBottom);
				}else{
					cityDefenseList.addItem(null);
				}
				break;
			case 4:
			
				haveAntigun = true;
			
				defenseID = model.battleInfo.cityDefenseList.getItemAt(i).defenseID;
				
				if(model.battleInfo.cityDefenseAmountArray[5]>0){
					antigunTop = new CityDefenseVO;
					antigunTop.defenseID = defenseID;
					antigunTop.cityDefenseNO = 5;
					antigunTop.num = model.battleInfo.cityDefenseAmountArray[5];
					antigunTop.posX = (X_GRID_AMOUNT-1) * GRID_SIZE;
					antigunTop.posY = GRID_SIZE*1;
					antigunTop.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[5];
					cityDefenseList.addItem(antigunTop);
				}else{
					cityDefenseList.addItem(null);
				}
				
				if(model.battleInfo.cityDefenseAmountArray[6]>0){
					antigunBottom = new CityDefenseVO;
					antigunBottom.defenseID = defenseID;
					antigunBottom.cityDefenseNO = 6;
					antigunBottom.num = model.battleInfo.cityDefenseAmountArray[6];
					antigunBottom.posX = (X_GRID_AMOUNT-1) * GRID_SIZE;
					antigunBottom.posY = GRID_SIZE*6;
					antigunBottom.haveAttacked = model.battleInfo.cityDefenseHaveAttackedArray[6];
					cityDefenseList.addItem(antigunBottom);
				}else{
					cityDefenseList.addItem(null);
				}
				break;
			default:
				break;
		}
		
	}
	if(!haveFence){
		cityDefenseList.addItemAt(null,0);
	}
	
	if(!haveBunker){
		cityDefenseList.addItemAt(null,1);
		cityDefenseList.addItemAt(null,2);
	}
	
	if(!haveGun){
		cityDefenseList.addItemAt(null,3);
		cityDefenseList.addItemAt(null,4);
	}
	
	if(!haveAntigun){
		cityDefenseList.addItemAt(null,5);
		cityDefenseList.addItemAt(null,6);
	}
	
	//围墙
	if(fence!=null && fence.num>0){
		var fenceCanvas:Canvas = new Canvas();
		fenceCanvas.x = fence.posX;
		fenceCanvas.y = fence.posY;
		fenceCanvas.height = 400;
		fenceCanvas.width = 50;
		fenceCanvas.data = fence;
		fenceCanvas.toolTip = " ";
		
		fenceCanvas.setStyle("hideEffect",fadeOut);
		
		if(fence.haveAttacked==1){
			fenceCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(fenceCanvas);
		
		//注册事件
		fenceCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		fenceCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		fenceCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			fenceCanvas.addEventListener(MouseEvent.CLICK,armyAttack); 
		}
		
		var fenceImage:Image = new Image();
		fenceImage.x = 0;
		fenceImage.y = 0;
		fenceImage.height = 400;
		fenceImage.width = 50;
		fenceImage.source = FENCE_IMAGE_PATH;
		fenceCanvas.addChild(fenceImage);
		
		canvasArmy.addChild(fenceCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
	
	//碉堡(上)
	if(bunkerTop!=null && bunkerTop.num>0){
		var bunkerTopCanvas:Canvas = new Canvas();
		bunkerTopCanvas.x = bunkerTop.posX;
		bunkerTopCanvas.y = bunkerTop.posY;
		bunkerTopCanvas.height = 50;
		bunkerTopCanvas.width = 50;
		bunkerTopCanvas.data = bunkerTop;
		bunkerTopCanvas.toolTip = " ";
		
		bunkerTopCanvas.setStyle("hideEffect",fadeOut);
		
		if(bunkerTop.haveAttacked==1){
			bunkerTopCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(bunkerTopCanvas);
		
		//注册事件
		bunkerTopCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		bunkerTopCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		bunkerTopCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			bunkerTopCanvas.addEventListener(MouseEvent.CLICK,armyAttack);
		}else{
			bunkerTopCanvas.addEventListener(MouseEvent.CLICK,clickCityDefense);
		}
		
		var bunkerTopImage:Image = new Image();
		bunkerTopImage.x = 0;
		bunkerTopImage.y = 0;
		bunkerTopImage.height = 50;
		bunkerTopImage.width = 50;
		bunkerTopImage.source = BUNKER_IMAGE_PATH;
		bunkerTopCanvas.addChild(bunkerTopImage);
		
		canvasArmy.addChild(bunkerTopCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
	//碉堡(下)
	if(bunkerBottom!=null && bunkerBottom.num>0){
		var bunkerBottomCanvas:Canvas = new Canvas();
		bunkerBottomCanvas.x = bunkerBottom.posX;
		bunkerBottomCanvas.y = bunkerBottom.posY;
		bunkerBottomCanvas.height = 50;
		bunkerBottomCanvas.width = 50;
		bunkerBottomCanvas.data = bunkerBottom;
		bunkerBottomCanvas.toolTip = " ";
		
		bunkerBottomCanvas.setStyle("hideEffect",fadeOut);
		
		if(bunkerBottom.haveAttacked==1){
			bunkerBottomCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(bunkerBottomCanvas);
		
		//注册事件
		bunkerBottomCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		bunkerBottomCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		bunkerBottomCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			bunkerBottomCanvas.addEventListener(MouseEvent.CLICK,armyAttack);
		}else{
			bunkerBottomCanvas.addEventListener(MouseEvent.CLICK,clickCityDefense);
		}
		
		var bunkerBottomImage:Image = new Image();
		bunkerBottomImage.x = 0;
		bunkerBottomImage.y = 0;
		bunkerBottomImage.height = 50;
		bunkerBottomImage.width = 50;
		bunkerBottomImage.source = BUNKER_IMAGE_PATH;
		bunkerBottomCanvas.addChild(bunkerBottomImage);
		
		canvasArmy.addChild(bunkerBottomCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
	
	//火炮(上)
	if(gunTop!=null && gunTop.num>0){
		var gunTopCanvas:Canvas = new Canvas();
		gunTopCanvas.x = gunTop.posX;
		gunTopCanvas.y = gunTop.posY;
		gunTopCanvas.height = 50;
		gunTopCanvas.width = 50;
		gunTopCanvas.data = gunTop;
		gunTopCanvas.toolTip = " ";
		
		gunTopCanvas.setStyle("hideEffect",fadeOut);
		
		if(gunTop.haveAttacked==1){
			gunTopCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(gunTopCanvas);
		
		//注册事件
		gunTopCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		gunTopCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		gunTopCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			gunTopCanvas.addEventListener(MouseEvent.CLICK,armyAttack);
		}else{
			gunTopCanvas.addEventListener(MouseEvent.CLICK,clickCityDefense);
		}
		
		var gunTopImage:Image = new Image();
		gunTopImage.x = 0;
		gunTopImage.y = 0;
		gunTopImage.height = 50;
		gunTopImage.width = 50;
		gunTopImage.source = GUN_IMAGE_PATH;
		gunTopCanvas.addChild(gunTopImage);
		
		canvasArmy.addChild(gunTopCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
	//火炮(下)
	if(gunBottom!=null && gunBottom.num>0){
		var gunBottomCanvas:Canvas = new Canvas();
		gunBottomCanvas.x = gunBottom.posX;
		gunBottomCanvas.y = gunBottom.posY;
		gunBottomCanvas.height = 50;
		gunBottomCanvas.width = 50;
		gunBottomCanvas.data = gunBottom;
		gunBottomCanvas.toolTip = " ";
		
		gunBottomCanvas.setStyle("hideEffect",fadeOut);
		
		if(gunBottom.haveAttacked==1){
			gunBottomCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(gunBottomCanvas);
		
		//注册事件
		gunBottomCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		gunBottomCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		gunBottomCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			gunBottomCanvas.addEventListener(MouseEvent.CLICK,armyAttack);
		}else{
			gunBottomCanvas.addEventListener(MouseEvent.CLICK,clickCityDefense);
		}
		
		var gunBottomImage:Image = new Image();
		gunBottomImage.x = 0;
		gunBottomImage.y = 0;
		gunBottomImage.height = 50;
		gunBottomImage.width = 50;
		gunBottomImage.source = GUN_IMAGE_PATH;
		gunBottomCanvas.addChild(gunBottomImage);
		
		canvasArmy.addChild(gunBottomCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
	
	//防空炮(上)
	if(antigunTop!=null && antigunTop.num>0){
		var antigunTopCanvas:Canvas = new Canvas();
		antigunTopCanvas.x = antigunTop.posX;
		antigunTopCanvas.y = antigunTop.posY;
		antigunTopCanvas.height = 50;
		antigunTopCanvas.width = 50;
		antigunTopCanvas.data = antigunTop;
		antigunTopCanvas.toolTip = " ";
		
		antigunTopCanvas.setStyle("hideEffect",fadeOut);
		
		if(antigunTop.haveAttacked==1){
			antigunTopCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(antigunTopCanvas);
		
		//注册事件
		antigunTopCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		antigunTopCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		antigunTopCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			antigunTopCanvas.addEventListener(MouseEvent.CLICK,armyAttack);
		}else{
			antigunTopCanvas.addEventListener(MouseEvent.CLICK,clickCityDefense);
		}
		
		var antigunTopImage:Image = new Image();
		antigunTopImage.x = 0;
		antigunTopImage.y = 0;
		antigunTopImage.height = 50;
		antigunTopImage.width = 50;
		antigunTopImage.source = ANTIGUN_IMAGE_PATH;
		antigunTopCanvas.addChild(antigunTopImage);
		
		canvasArmy.addChild(antigunTopCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
	//防空炮(下)
	if(antigunBottom!=null && antigunBottom.num>0){
		var antigunBottomCanvas:Canvas = new Canvas();
		antigunBottomCanvas.x = antigunBottom.posX;
		antigunBottomCanvas.y = antigunBottom.posY;
		antigunBottomCanvas.height = 50;
		antigunBottomCanvas.width = 50;
		antigunBottomCanvas.data = antigunBottom;
		antigunBottomCanvas.toolTip = " ";
		
		antigunBottomCanvas.setStyle("hideEffect",fadeOut);
		
		if(antigunBottom.haveAttacked==1){
			antigunBottomCanvas.filters = [greyFilter];
		}
		
		battleCityDefenseCanvasList.addItem(antigunBottomCanvas);
		
		//注册事件
		antigunBottomCanvas.addEventListener(ToolTipEvent.TOOL_TIP_CREATE,showCityDefenseTooltip);
		antigunBottomCanvas.addEventListener(MouseEvent.MOUSE_OVER,defenceMouseOver);
		antigunBottomCanvas.addEventListener(MouseEvent.MOUSE_OUT,defenceMouseOut);
		if(playerForce==1){
			antigunBottomCanvas.addEventListener(MouseEvent.CLICK,armyAttack);
		}else{
			antigunBottomCanvas.addEventListener(MouseEvent.CLICK,clickCityDefense);
		}
		
		var antigunBottomImage:Image = new Image();
		antigunBottomImage.x = 0;
		antigunBottomImage.y = 0;
		antigunBottomImage.height = 50;
		antigunBottomImage.width = 50;
		antigunBottomImage.source = ANTIGUN_IMAGE_PATH;
		antigunBottomCanvas.addChild(antigunBottomImage);
		
		canvasArmy.addChild(antigunBottomCanvas);
	}else{
		battleCityDefenseCanvasList.addItem(null);
	}
	
}

/**
 * 军队走到目的地
 */
 private function onArmyWalkArrive(e:Event):void{
 	currentArmyCanvas=e.currentTarget as ArmyElement;
 	showPopupCanvas();
 }
 /**
 * 军队开火结束
 */
 private function onArmyFightOver(e:Event):void{
 	//to do
 }