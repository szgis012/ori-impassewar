package com.war.test.service;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.domain.Guild;
import com.war.domain.GuildIncExpHistory;
import com.war.domain.GuildPlayer;
import com.war.domain.GuildTechnology;
import com.war.service.IGuildService;
import com.war.service.IHeroService;
import com.war.service.ITreasureService;

public class GuildServiceTest {

	@SuppressWarnings("unused")
	private static IGuildService guildService;
	private static ITreasureService treasureService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildService = (IGuildService)SpringService.getApplicationContext().getBean("guildService");
		treasureService = (ITreasureService) SpringService.getApplicationContext().getBean("treasureService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
//		guildService.applyJoinGuild(1, 10);
//		guildService.accpetPlayerJoinGuildApplication(10, 1);
	}

	@After
	public void tearDown() throws Exception {
//		guildService.deleteGuildPlayerByGuildIDAndPlayerID(1, 10);
	}
	
	// @Test
	public void testDonateGoosToGuild(){
		Integer playerID = 10;
		Integer guildID = 3;
		String targetGuildName = "南瓜";
		Integer guildPlayerID = 3;
		Integer cityID = 4;
		Integer technologyID = 111;
		Long money = 10000L;
		//取得军团成员列表
//		List<GuildPlayer> list = guildService.getGuildMemeberPagingList(guildID, 0, 3);
//		for (Iterator<GuildPlayer> it=list.iterator(); it.hasNext();) {
//			System.out.println(it.next().getPlayer().getName());
//		}
		//军团权限变更
//		GuildPlayer guildPlayer = new GuildPlayer();
//		guildPlayer.setGuildID(guildID);
//		guildPlayer.setPlayerID(playerID);
//		guildPlayer.setPermission("0-0-0-0-0-0");
//		guildPlayer.setDutyName("统帅");
//		guildService.guildMemeberGrant(guildPlayer);
		// 未捐献金币之前的金币
//		Guild guild = guildService.getGuildByID(guildID);
//		System.out.println(guild.getMoney());
		// 捐献物资 
//		guildService.donateMoney(guildID, playerID, money);
		//捐献旗帜
//		guildService.donateOriflamme(guildID, playerID, "PETTY_OFFICER", 5);
		// 捐献金币之后的金币 
//		guild = guildService.getGuildByID(guildID);
//		System.out.println(guild.getMoney());
		
		// 测试升级
//		guildService.upgradeGuild(guildID);
		//取得军团所有收入信息
//		guildService.getAllGuildIncomeInfo(guildID);
		//领取补贴
//		guildService.receiveSubsidy(guildID, playerID);
		//取得军团可列表
//		List<GuildTechnology> list = guildService.getGuildTechnology(guildID);
//		System.out.println(list.get(0).getDescription());
		//查看军团成员历史捐献总记录
//		List<GuildPlayer> list = guildService.getTotalAlmsOfGuildMemberInGuild(guildID, 0, 1);
//		for (Iterator<GuildPlayer> it = list.iterator(); it.hasNext();) {
//			GuildPlayer gp = it.next();
//			System.out.println(gp.getGuildIncExpHistory().getMoney()+gp.getPlayer().getName());
//		}
		//查看军团成员历史捐献
//		List<GuildPlayer> list = guildService.getTotalAlmsOfGuildMemberInGuild(guildID, guildPlayerID, null);
//		for (Iterator<GuildPlayer> it = list.iterator(); it.hasNext();) {
//			GuildPlayer gp = it.next();
//			System.out.println(gp.getGuildIncExpHistory().getMoney()+gp.getPlayer().getName());
//		}
		//取得军团内某个成员历史捐献记录
//		List<GuildIncExpHistory> list = guildService.getAlmsHistoryOfGuildMember(guildID, guildPlayerID);
//		for (Iterator<GuildIncExpHistory> it = list.iterator(); it.hasNext();) {
//			System.out.println(it.next().getMoney());
//		}
		//取得军团科技列表
//		List<GuildTechnology> gtList = guildService.getGuildTechnology(guildID);
//		for (int i=0; i<gtList.size(); i++) {
//			System.out.println(gtList.get(i).getName()+"\n");
//			System.out.println(gtList.get(i).getGuildtechnologyCost().getLevel()+"\n");
//			System.out.println(gtList.get(i).getGuildtechnologyCost().getDescription()+"\n");
//		}
		//研究军团科技
//		guildService.researchGuildTechnologyByTechnologyID(cityID, technologyID, guildID);
		//取消研究军团科技
//		guildService.cancelResearchGuildTechnology(cityID, technologyID, guildID);
		//移除军团关系
		guildService.removeGuildRelationship(guildID, targetGuildName);
	}
	
	//@Test
	public void testDonateOriflamme() {
		Integer guildID = 7;
		Integer playerID = 44;
		String oriflammeType = "PETTY_OFFICER";
		Integer oriflammeNum = 1;
		
		// guildService.donateOriflamme(guildID, playerID, oriflammeType, oriflammeNum);
	}
	
	// @Test
	public void testRefreshRank() {
		guildService.refreshGuildRenownAndRank();
	}
	
	@Test
	public void testRewardGuildPlayer() {
		// ===== 【壮志凌云】======================
		// 7: 司令部
		// 1: 战争机器
		// 4: ┈华夏☭联盟
		
		// ===== 【钢铁咆哮】======================
		// 26: 共和国￠集团军
		// 6: 纵横联盟
		// 225: 月光倾城醉
		
		
		// 643	装甲论
		// 513	高级火药
		// 523	高级护甲
		// 413	精致保险箱
		
		// 642	拿破仑传
		// 512	中级火药
		// 522	中级护甲
		// 412	普通保险箱
		
		// 641	高卢战记
		// 511	初级火药
		// 521	初级护甲
		// 411	陈旧保险箱

		int[] guildIDs = {26,6,225};
		
		for (int i = 0; i < guildIDs.length; i++) {
			List<GuildPlayer> guildPlayerList = guildService.getGuildMemberList(guildIDs[i]);
			System.out.println(guildPlayerList.size());
			for (GuildPlayer guildPlayer : guildPlayerList) {
				switch (i) {
					case 0:
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 643, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 513, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 523, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 413, 1);
						break;
					case 1:
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 642, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 512, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 522, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 412, 1);
						break;
					case 2:
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 641, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 511, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 521, 1);
						treasureService.increasePlayerTreasure(guildPlayer.getPlayerID(), 411, 1);
						break;
				}
			}
		}
	}
	
}
