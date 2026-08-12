package com.hifong.war.constant
{
	import mx.collections.ArrayCollection;
	
	public class HeroStarConstant
	{
		public function HeroStarConstant()
		{
		}
		/**
	 * 强化指挥官星级需要的材料
	 * 数组一维映射需要强化到得级别
	 * 数组二维映射需要的一些材料： {军魄，指挥官经验，金钱}
	 */
	 	/** 所需金钱 */
	 	public static const NEEDED_MONEY:ArrayCollection=new ArrayCollection([50000,150000,300000,1000000,2000000]);
	 	/** 所需经验 */
	 	public static const NEEDED_EXPERIENCE:ArrayCollection=new ArrayCollection([4000,16000,36000,70000,150000]);
	 	/** 所需军魂 */
	 	public static const NEEDED_MILITARYSOUL:ArrayCollection=new ArrayCollection([50,100,150,200,250]);
	 	/** 星级描述 */
	 	public static const STAR_DESCRIBE:ArrayCollection=new ArrayCollection(["一星指挥官军队攻击提高5%",
	 																														"二星指挥官军队攻击提高8%，防御提高5%",
	 																														"三星指挥官军队攻击提高10%，防御提高8%，额外效果A：军队士气高涨触发几率提高5%",
	 																														"四星指挥官军队攻击提高15%，防御提高10%，额外效果A：军队士气高涨触发几率提高10%，额外效果B：提高军队生命5%",
	 																														"五星指挥官军队攻击提高20%，防御提高15%，额外效果A：军队士气高涨触发几率提高15%，额外效果B：提高军队生命10%，额外效果C：指挥官带兵数提高10%"]);
	 	/** 强化提示*/
	 	public static const StrengthenTip:ArrayCollection=new ArrayCollection(["若强化成功，指挥官升至一星；指挥官强化有一定几率失败，确定强化？",
	 																													"若强化成功，指挥官升至二星，若强化失败，指挥官降至无星，指挥官强化有一定几率失败，确定强化？",
	 																													"若强化成功，指挥官升至三星，若强化失败，指挥官降至一星，指挥官强化有一定几率失败，确定强化？",
	 																													"若强化成功，指挥官升至四星，若强化失败，指挥官不降星，指挥官强化有一定几率失败，确定强化？",
	 																													"若强化成功，指挥官升至五星，若强化失败，指挥官降至三星，指挥官强化有一定几率失败，确定强化？"]);																											
	}
}