package com.hifong.war.view.guild
{
	import mx.collections.ArrayCollection;
	
	/**
	 * 军团常量s
	 */
	public class GuildConfig
	{
		public function GuildConfig()
		{
		}
		/** 军团补贴 */
		public static const GUILD_Subsidy:String=""; 
		/** 旗帜类型*/
		public static const oriflammeTypeArray:Array=["士官军旗","校官军旗","元帅军旗"];
		/** 自身拥有的 旗帜数 */
		public static var playerOriflammeArray:Array=[0,0,0];
		/** 自身在军团的职位*/
		public static var GuildName:String;
		
		/** 军团升级需求*/
		[Bindable]
		public static var upgradeGuildRequire:ArrayCollection=new ArrayCollection([{level:1,memberNum:10,money:10,renown:10,honor:10, oriflammeLowerNum:0 ,oriflammeIntermediateNum:0,oriflammeAdvancedNum:0},
																				{level:2,memberNum:20,money:20,renown:20,honor:20, oriflammeLowerNum:0,oriflammeIntermediateNum:0,oriflammeAdvancedNum:0 },
																				{level:3,memberNum:30,money:30,renown:30,honor:30, oriflammeLowerNum:0,oriflammeIntermediateNum:0,oriflammeAdvancedNum:0 },
																				{level:4,memberNum:40,money:40,renown:40,honor:40, oriflammeLowerNum:40,oriflammeIntermediateNum:40,oriflammeAdvancedNum:40 },
																				{level:5,memberNum:50,money:50,renown:50,honor:50, oriflammeLowerNum:50,oriflammeIntermediateNum:50,oriflammeAdvancedNum:50 } ] );
	}
}