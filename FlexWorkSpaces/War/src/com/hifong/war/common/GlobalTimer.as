package com.hifong.war.common
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.constant.CityBuildingStateConstant;
	import com.hifong.war.events.building.ClientProcessFinishedEvent;
	import com.hifong.war.events.building.armory.FinishProduceOrdnanceEvent;
	import com.hifong.war.events.building.citycenter.ClientFinishEnlistCitizenEvent;
	import com.hifong.war.events.building.defense.FinishBuildDefenseEvent;
	import com.hifong.war.events.common.LoadGameInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.vo.CityBuildingVO;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import flash.events.TimerEvent;
	import flash.net.LocalConnection;
	import flash.utils.Timer;
	
	import mx.collections.ArrayCollection;
	

	public class GlobalTimer extends Timer
	{
		//model
		private var model:ModelLocator = ModelLocator.getInstance();
		//event dispatcher
		private var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
		//60秒钟计时
		private var second60:int = 0;
		
		
		public function GlobalTimer()
		{
			//间隔为1秒
			super(1000);
			
			this.addEventListener(TimerEvent.TIMER,onTime);
			
			//计时器开始
			this.start();
		}
		
		private function onTime(event:TimerEvent):void{
			//计时
			this.second60++;
			
 			/******* 这里添加每秒钟进行的计算 *******/
			//服务器时间增加
 			model.serverTime.setSeconds(model.serverTime.getSeconds() + 1);
 			//更新城市内建筑信息
			updateCityBuildingState();
			//更新城防信息
			updateCityDefenseInfo();
			//更新军械信息
			updateCityOrdnanceInfo();
			//更新招募市民信息
			updateEnlistCitizenInfo();
			
			/******* 这里添加每分钟进行的计算 *******/
			if(this.second60 >= 60){
				//同步服务器时间
				dispatcher.dispatchEvent(new LoadGameInfoEvent(model.playerInfo.playerID,model.cityInfo.cityID));
				
				//垃圾回收
				doGC();
				
				//计时归零
				this.second60 = 0;
			}
			
		}
		
		//通过抛出异常的方式强制flash进行垃圾回收
		private function doGC():void{
			try{
				new LocalConnection().connect("gcConnection");
				new LocalConnection().connect("gcConnection");
			}catch (e:Error) {
			}
		}
		
		//刷新城市内建筑信息，对建造，升级，拆除完成的建筑信息进行更新
		private function updateCityBuildingState():void{
			var buildingList:ArrayCollection = model.cityBuildingList;
			var cityBuilding:CityBuildingVO;
			
			if(buildingList && buildingList.length>0){
				for(var i:int=0; i<buildingList.length; i++){
					cityBuilding = buildingList.getItemAt(i) as CityBuildingVO;
					
					//如果建筑处在升级或者拆除中,并且建造过程已经完成
					 if(cityBuilding.state != CityBuildingStateConstant.NORMAL && model.serverTime.time >= cityBuilding.processQueue.finishTime.time){
						//通知服务器时间已经完成
						dispatcher.dispatchEvent(new ClientProcessFinishedEvent(cityBuilding));
//						cityBuilding.state==CityBuildingStateConstant.NORMAL;
//						cityBuilding.processQueue=null;
					} 
				}
				
			}
		}
		
		//城防建造完毕时更新其信息
		private function updateCityDefenseInfo():void{
			//城防建造队列
			var defenseList:ArrayCollection = model.cityDefenseInfo.defenseProcessList;
			
			if(defenseList && defenseList.length>0){
				//得到正在建造的进程信息
				var process:ProductionQueueVO = defenseList.getItemAt(0) as ProductionQueueVO;
				//如果已经完成,就向服务器发送消息更新城防信息
				if(model.serverTime.time > process.finishTime.time){
					dispatcher.dispatchEvent(new FinishBuildDefenseEvent(process));
				}
			}
		}
		
		//军械生产完成时更新其信息
		private function updateCityOrdnanceInfo():void{
			//城防建造队列
			var ordnanceList:ArrayCollection = model.ordnanceInfo.ordnanceProcessList;
			
			if(ordnanceList && ordnanceList.length>0){
				//得到正在建造的进程信息
				var process:ProductionQueueVO = ordnanceList.getItemAt(0) as ProductionQueueVO;
				//如果已经完成,就向服务器发送消息更新城防信息
				if(model.serverTime.time > process.finishTime.time){
					dispatcher.dispatchEvent(new FinishProduceOrdnanceEvent(process));
				}
			}
		}
		
		//进行招募市民完成时的更新
		private function updateEnlistCitizenInfo():void{
			//招募市民进程信息
			var enlistCitizenProcess:ProductionQueueVO = model.enlistCitizenProcess;
			
			if(enlistCitizenProcess){
				//如果完成就更新市民数量
				if(model.serverTime.time > enlistCitizenProcess.finishTime.time){
					dispatcher.dispatchEvent(new ClientFinishEnlistCitizenEvent(enlistCitizenProcess));
				}
			}
		}

	}
}