package com.hifong.war.common
{
	import com.hifong.war.vo.CityVO;
	import com.hifong.war.vo.PlayerVO;
	
	public class PlayerInfo
	{
		//
		public var player:PlayerVO;
		
		public function PlayerInfo()
		{
		}

//		public var playerID:int = 1;
//		public var playerName:String;
//		public var guildID:int;
//		public var cityID:int = 1;


		 /** 玩家编号 */
		 public function get playerID():int{
		 	return player.playerID;
		 }
		 
	    /** 用户编号 */
	     public function get userID():int{
		 	return player.userID;
		 }
		 
	    /** 玩家名称 */
	     public function get playerName():String{
		 	return player.name;
		 }
		 
	    /** 头衔 */
	    public function get honor():String{
		 	return player.honor;
		 }
		 
	    /** 工会编号 */
	    public function get guildID():int{
		 	return player.guildID;
		 }
		 
	    /** 国家 */
	    public function get country():int{
		 	return player.country;
		 }
		 
	    /** 声望 */
	   	public function get renown():int{
		 	return player.renown;
		 }

	    /** 进攻点数 */
	    public function get attackPoint():int{
		 	return player.attackPoint;
		 }
		 
	    /** 防御点数 */
	     public function get defensePoint():int{
		 	return player.defensePoint;
		 }
		 
	    /** 排名 */
	    public function get rank():int{
		 	return player.rank;
		 }
		 
	    /** 创建时间 */
	     public function get createTime():Date{
		 	return player.createTime;
		 }
		 
	    /** 城市信息 */
	     public function get city():CityVO{
		 	return player.city;
		 }
		 
	}
}