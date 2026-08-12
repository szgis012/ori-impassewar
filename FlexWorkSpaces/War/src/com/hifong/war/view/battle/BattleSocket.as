// ActionScript file
import com.adobe.cairngorm.control.CairngormEventDispatcher;
import com.adobe.serialization.json.JSON;
import com.hifong.war.events.military.GetCityBattleListEvent;
import com.hifong.war.util.MsgBox;
import com.hifong.war.view.battle.ArmyElement;
import com.hifong.war.vo.BattleArmyVO;

import flash.events.Event;
import flash.utils.setTimeout;

private function initBattleSocket():void{

	//连接 Socket
	battleSocket.connect(model.SERVER_IP,model.BATTLE_SOCKET_PORT);
	
	//注册 Socket连接事件
	battleSocket.addEventListener(Event.CONNECT,socketConnect);
	//注册 Socket关闭事件
	battleSocket.addEventListener(Event.CLOSE,socketClose);
	//注册 安全错误事件
	battleSocket.addEventListener(SecurityErrorEvent.SECURITY_ERROR,securityError);
	//注册 处理Socket接收数据事件
	battleSocket.addEventListener(ProgressEvent.SOCKET_DATA,socketData);
	//注册 IO错误事件
	battleSocket.addEventListener(IOErrorEvent.IO_ERROR,socketIOError);
	
}

//连接建立
private function socketConnect(event:Event):void{
	
}

//连接关闭
private function socketClose(event:Event):void{
	MsgBox.showMessage("与战斗服务器连接断开，请尝试重新进入战场。");
	this.exitBattle();
}

//安全错误
private function securityError(event:SecurityErrorEvent):void{
	MsgBox.showMessage("连接战斗服务器失败，请尝试重新进入战场。");
	this.exitBattle();
}

//IO错误
private function socketIOError(event:Event):void{
	MsgBox.showMessage("连接战斗服务器失败，请尝试重新进入战场。");
	this.exitBattle();
}

//发送数据
private function sendData(data:String):void{
	var byte:ByteArray = new ByteArray();
	byte.writeUTFBytes(data + "\n");
	battleSocket.writeBytes(byte);
	battleSocket.flush();
}
			
//接收数据
private function socketData(event:ProgressEvent):void{
	
 	var	msg:String = battleSocket.readMultiByte(battleSocket.bytesAvailable,"utf8");
	
	var json:Object = JSON.decode(msg);
	
	var type:int = json.type;
	
	var battleArmyDefender:Object,battleArmyCanvasDefender:Object;
	
	var result:String;
	
	switch(type){
		case 1:
			//======================================================移动
			if(json.operator==1){
				//进攻方
				battleArmyList_attacker.getItemAt(json.armyNO).posX = json.posX;
				battleArmyList_attacker.getItemAt(json.armyNO).posY = json.posY;
				battleArmyList_attacker.getItemAt(json.armyNO).haveMoved = true;
				
//				battleArmyCanvasList_attacker.getItemAt(json.armyNO).x = json.posX;
//				battleArmyCanvasList_attacker.getItemAt(json.armyNO).y = json.posY;
				battleArmyCanvasList_attacker.getItemAt(json.armyNO).walk([[json.posX/50,json.posY/50]]);
				
				/* if(playerForce==1){
					setTimeout(function ():void{
						showPopupCanvas();
					},actionDelay);
				} */
				
			}
			break;
		case 2:
			//攻击
			
			var armyDefenderName:String;
			
			if(json.operator==1){
				
				battleArmyList_attacker[json.armyNO].attackType = 1;
				
				battleArmyDefender = battleArmyList_defender[json.targetArmyNO];
				armyDefenderName = battleArmyDefender.army.name;
				
				result = battleArmyList_attacker[json.armyNO].army.name + "攻击" + armyDefenderName + "；\n造成" + json.damage + "伤害，损失" + json.deadArmyAmount + "个单位。";
					
				if(playerForce==1){
					//我方进攻，添加事件
					addTextareaEvent(result,1);
				}else if(playerForce==2){
					//敌方进攻，添加事件
					addTextareaEvent(result,2);
				}
				
				showEffect(0,battleArmyDefender.posX,battleArmyDefender.posY,function ():void{
					battleArmyCanvasList_attacker[json.armyNO].filters = [greyFilter];
					if(json.surplusArmyAmount==0){
						//军队已被消灭
						battleArmyDefender.amount = 0;
						battleArmyCanvasList_defender[json.targetArmyNO].visible = false;
					}else{
						battleArmyDefender.amount = json.surplusArmyAmount;
					}
					
				});
				
			}else if(json.operator==2){
				
				battleArmyList_defender[json.armyNO].attackType = 1;
				
				battleArmyDefender = battleArmyList_attacker[json.targetArmyNO];
				armyDefenderName = battleArmyDefender.army.name;
				
				result = battleArmyList_defender[json.armyNO].army.name + "攻击" + armyDefenderName + "；\n造成" + json.damage + "点伤害，损失" + json.deadArmyAmount + "个单位。";
					
				if(playerForce==1){
					//敌方进攻，添加事件
					addTextareaEvent(result,2);
				}else if(playerForce==2){
					//我方进攻，添加事件
					addTextareaEvent(result,1);
				}
					
				showEffect(0,battleArmyDefender.posX,battleArmyDefender.posY,function ():void{
					battleArmyCanvasList_defender[json.armyNO].filters = [greyFilter];
					if(json.surplusArmyAmount==0){
						//军队已被消灭
						battleArmyDefender.amount = 0;
						battleArmyCanvasList_attacker[json.targetArmyNO].visible = false;
					}else{
						battleArmyList_attacker[json.targetArmyNO].amount = json.surplusArmyAmount;
					}
					
				});
				
			}
			
			removeRangeGrid();
			break;
		case 3:
			//防御
			if(json.operator==1){
				//进攻方
				battleArmyList_attacker[json.armyNO].attackType = 2;
				battleArmyCanvasList_attacker[json.armyNO].filters = [greyFilter];
			}else if(json.operator==2){
				//防守方
				battleArmyList_defender[json.armyNO].attackType = 2;
				battleArmyCanvasList_defender[json.armyNO].filters = [greyFilter];
			}
			break;
		case 6:
			//攻击城防
			
			var cityDenfeseName:String;
			
			cityDenfeseName = CityDefenseConstant.CITY_DEFENSE_NAME_LIST.getItemAt(json.cityDefenseNO).toString();
			
			if(json.surplusCityDefenseAmount==0){
				//城市防御已被完全摧毁  
				cityDefenseList[json.cityDefenseNO].num = 0;
				battleCityDefenseCanvasList[json.cityDefenseNO].visible = false;
			}else{
				cityDefenseList[json.cityDefenseNO].num = json.surplusCityDefenseAmount;
			}
			
			result = battleArmyList_attacker[json.armyNO].army.name + "攻击" + cityDenfeseName + "；\n造成" + json.damage + "点伤害，损失" + json.destoryCityDefenseAmount + "个单位。";
				
			if(playerForce==1){
				//我方进攻，添加事件
				addTextareaEvent(result,1);
			}else if(playerForce==2){
				//敌方进攻，添加事件
				addTextareaEvent(result,2);
			}
			
			break;
		case 7:
			//城防攻击
			
			var armyDefenderName_CityDefense:String;
			
			armyDefenderName_CityDefense = battleArmyList_attacker[json.targetArmyNO].army.name;
			
			if(json.surplusArmyAmount==0){
				//军队已被消灭
				battleArmyList_attacker[json.targetArmyNO].amount = 0;
				battleArmyCanvasList_attacker[json.targetArmyNO].visible = false;
			}else{
				battleArmyList_attacker[json.targetArmyNO].amount = json.surplusArmyAmount;
			}
			
			cityDefenseList[json.cityDefenseNO].haveAttacked = 1;
			
			result = CityDefenseConstant.CITY_DEFENSE_NAME_LIST.getItemAt(json.cityDefenseNO).toString() + "攻击" + armyDefenderName_CityDefense + "；\n造成" + json.damage + "点伤害，损失" + json.deadArmyAmount + "个单位。";
			
			if(playerForce==1){
				//敌方进攻，添加事件
				addTextareaEvent(result,2);
			}else if(playerForce==2){
				//我方进攻，添加事件
				addTextareaEvent(result,1);
			}
			
			break;
		case 8:
			//释放技能
			var targetBattleArmy:BattleArmyVO;
			
			result = "指挥官";
			if(json.operator==1){
				result += military_attacker.cityHero.name;
			}else if(json.operator==2){
				result += military_defender.cityHero.name;
			}
			
			result = "释放" + json.heroSkillName + "技能，对";
			
			if(json.targetArmyForce==1){
				result += "进攻方";
				targetBattleArmy = battleArmyList_attacker[json.targetArmyNO];
			}else if(json.targetArmyForce==2){
				result += "防守方";
				targetBattleArmy = battleArmyList_defender[json.targetArmyNO];
			}
			result += targetBattleArmy.army.name + "\n";
			
			showEffect(json.skillID,targetBattleArmy.posX,targetBattleArmy.posY,function():void{
				
				switch(json.skillType){
					case 1:
						if(json.surplusArmyAmount==0){
							//军队已被消灭
							targetBattleArmy.amount = 0;
							targetBattleArmy = null;
							if(json.targetArmyForce==1){
								battleArmyList_attacker[json.targetArmyNO].amount = 0;
								battleArmyCanvasList_attacker[json.targetArmyNO].visible = false;
							}else if(json.targetArmyForce==2){
								battleArmyList_defender[json.targetArmyNO].amount = 0;
								battleArmyCanvasList_defender[json.targetArmyNO].visible = false;
							}
						}else{
							targetBattleArmy.amount = json.surplusArmyAmount;
						}
						result += "造成" + json.damage + "点伤害，损失" + json.deadArmyAmount + "个单位。";
						break;
					case 2:
						if(json.attack>targetBattleArmy.army.attack){
							result += "增加" + (json.attack-targetBattleArmy.army.attack) + "点攻击";
						}else{
							result += "减少" + (targetBattleArmy.army.attack-json.attack) + "点攻击";
						}
						targetBattleArmy.army.attack = json.attack;
						break;
					case 3:
						if(json.defense>targetBattleArmy.army.defense){
							result += "增加" + (json.defense-targetBattleArmy.army.defense) + "点防御";
						}else{
							result += "减少" + (targetBattleArmy.army.defense-json.defense) + "点防御";
						}
						targetBattleArmy.army.defense = json.defense;
						break;
					case 4:
						if(json.speed>targetBattleArmy.army.speed){
							result += "增加" + (json.speed-targetBattleArmy.army.speed) + "点速度";
						}else{
							result += "减少" + (targetBattleArmy.army.speed-json.speed) + "点速度";
						}
						targetBattleArmy.army.speed = json.speed;
						break;
					case 5:
						if(json.range>targetBattleArmy.army.range){
							result += "增加" + (json.range-targetBattleArmy.army.range) + "点攻击范围";
						}else{
							result += "减少" + (targetBattleArmy.army.range-json.range) + "点攻击范围";
						}
						targetBattleArmy.army.range = json.range;
						break;
					default:
						break;
						
				}
				
				if(playerForce==1){
					//我方进攻，添加事件
					addTextareaEvent(result,1);
				}else if(playerForce==2){
					//敌方进攻，添加事件
					addTextareaEvent(result,2);
				}
			});
			
		case 11:
			//手动结束回合
			
			break;
		case 12:
			//军队撤退
			
			break;
		case 21:
			//自动战斗 - 移动 & 防御
			removeRouteGrid();
			removeRangeGrid();
			hidePopupCanvas();
			
			//移动
			if(json.operator==1){
				//进攻方
				battleArmyList_attacker.getItemAt(json.armyNO).posX = json.posX;
				battleArmyList_attacker.getItemAt(json.armyNO).posY = json.posY;
				battleArmyList_attacker.getItemAt(json.armyNO).haveMoved = true;
				
//				battleArmyCanvasList_attacker.getItemAt(json.armyNO).x = json.posX;
//				battleArmyCanvasList_attacker.getItemAt(json.armyNO).y = json.posY;
				battleArmyCanvasList_attacker.getItemAt(json.armyNO).walk([[json.posX/50,json.posY/50]]);
			}else if(json.operator==2){
				//防守方
				battleArmyList_defender.getItemAt(json.armyNO).posX = json.posX;
				battleArmyList_defender.getItemAt(json.armyNO).posY = json.posY;
				battleArmyList_defender.getItemAt(json.armyNO).haveMoved = true;
				
//				battleArmyCanvasList_defender.getItemAt(json.armyNO).x = json.posX;
//				battleArmyCanvasList_defender.getItemAt(json.armyNO).y = json.posY;
				battleArmyCanvasList_defender.getItemAt(json.armyNO).walk([[json.posX/50,json.posY/50]]);
			}
			
			//防御
			setTimeout(function ():void{
				if(json.operator==1){
					//进攻方
					battleArmyList_attacker[json.armyNO].attackType = 2;
					battleArmyCanvasList_attacker[json.armyNO].filters = [greyFilter];
				}else if(json.operator==2){
					//防守方
					battleArmyList_defender[json.armyNO].attackType = 2;
					battleArmyCanvasList_defender[json.armyNO].filters = [greyFilter];
				}
			},actionDelay);
			break;
		case 22:
			//自动战斗 - 移动 & 攻击 
			removeRouteGrid();
			removeRangeGrid();
			hidePopupCanvas();
			
			//进攻方
			if(json.operator==1){
				//移动
				battleArmyList_attacker.getItemAt(json.armyNO).posX = json.posX;
				battleArmyList_attacker.getItemAt(json.armyNO).posY = json.posY;
				battleArmyList_attacker.getItemAt(json.armyNO).haveMoved = true;
				
				battleArmyCanvasList_attacker.getItemAt(json.armyNO).walk([[json.posX/50,json.posY/50]]);
				//攻击
				battleArmyCanvasDefender = battleArmyCanvasList_defender[json.targetArmyNO];
					battleArmyDefender = battleArmyList_defender[json.targetArmyNO];
					
					battleArmyList_attacker[json.armyNO].attackType = 1;
					
					result = battleArmyList_attacker[json.armyNO].army.name + "攻击" + battleArmyDefender.army.name + "；\n造成" + json.damage + "伤害，损失" + json.deadArmyAmount + "个单位。";
					//////////////////////////////////////////////////////////////////////
					var sourceArmy:ArmyElement=battleArmyCanvasList_attacker[json.armyNO];
					sourceArmy.setFightDirection(getDirection(json.posX,json.posY,battleArmyCanvasDefender.x,battleArmyCanvasDefender.y));
					//////////////////////////////////////////////////////////////////////
					if(playerForce==1){
						//我方进攻，添加事件
						addTextareaEvent(result,1);
					}else if(playerForce==2){
						//敌方进攻，添加事件
						addTextareaEvent(result,2);
					}
					
					showEffect(0,battleArmyDefender.posX,battleArmyDefender.posY,function ():void{
						battleArmyCanvasList_attacker[json.armyNO].filters = [greyFilter];
						if(json.surplusArmyAmount==0){
							//军队已被消灭
							battleArmyDefender.amount = 0;
							battleArmyCanvasDefender.visible = false;
						}else{
							battleArmyDefender.amount = json.surplusArmyAmount;
						}
						
					});
				
			}else if(json.operator==2){
				//防守方
				battleArmyList_defender.getItemAt(json.armyNO).posX = json.posX;
				battleArmyList_defender.getItemAt(json.armyNO).posY = json.posY;
				battleArmyList_defender.getItemAt(json.armyNO).haveMoved = true;
				
				battleArmyCanvasList_defender.getItemAt(json.armyNO).walk([[json.posX/50,json.posY/50]]);
				//攻击
				battleArmyCanvasDefender = battleArmyCanvasList_attacker[json.targetArmyNO];
					battleArmyDefender = battleArmyList_attacker[json.targetArmyNO];
					
					battleArmyList_defender[json.armyNO].attackType = 1;
					
					result = battleArmyList_defender[json.armyNO].army.name + "攻击" + battleArmyDefender.army.name + "；\n造成" + json.damage + "点伤害，损失" + json.deadArmyAmount + "个单位。";
					
					/////////////////////////////////////////////////////////////////////
					sourceArmy=battleArmyCanvasList_defender[json.armyNO];
					sourceArmy.setFightDirection(getDirection(json.posX,json.posX,battleArmyCanvasDefender.x,battleArmyCanvasDefender.y));
					/////////////////////////////////////////////////////////////////////
					if(playerForce==1){
						//敌方进攻，添加事件
						addTextareaEvent(result,2);
					}else if(playerForce==2){
						//我方进攻，添加事件
						addTextareaEvent(result,1);
					}
					
					showEffect(0,battleArmyDefender.posX,battleArmyDefender.posY,function ():void{
						battleArmyCanvasList_defender[json.armyNO].filters = [greyFilter];
						if(json.surplusArmyAmount==0){
							//军队已被消灭
							battleArmyDefender.amount = 0;
							battleArmyCanvasDefender.visible = false;
						}else{
							battleArmyDefender.amount = json.surplusArmyAmount;
						}
						
					});
			}
			break;
		case 31:
			//下回合开始
			currentOperator = json.operator;
			round = (json.round+1)/2;
			roundFinished();
			break;
		case 33:
			//战斗结束
			timer.stop();
			clearInterval(mapMonsterAutoBattleHandleID);
			clearInterval(autoBattleHandleID);
			MsgBox.showMessage("战斗结束。", false, function ():void{
				exitBattle();
				CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityBattleListEvent(model.cityInfo.cityID));
			});
			break;
		case 34:
			//战斗结束(进攻方逃跑)
			timer.stop();
			clearInterval(mapMonsterAutoBattleHandleID);
			clearInterval(autoBattleHandleID);
			if(playerForce==1){
				MsgBox.showMessage("战斗结束，您的军队已逃跑。", false, function ():void{
					exitBattle();
					CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityBattleListEvent(model.cityInfo.cityID));
				});
			}else if(playerForce==2){
				MsgBox.showMessage("战斗结束，对方军队已逃跑。", false, function ():void{
					exitBattle();
					CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityBattleListEvent(model.cityInfo.cityID));
				});
			}
			break;
		case 37:
			//进攻方信息
			if(playerForce==1){
				MsgBox.showMessage(json.message);
			}
			break;
		case 38:
			//防守方信息
			if(playerForce==2){
				MsgBox.showMessage(json.message);
			}
			break;
		case 39:
			//双方信息
			MsgBox.showMessage(json.message);
			break;
		default:
			break;
	}

	
}