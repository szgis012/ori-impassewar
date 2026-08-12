package com.war.test.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.domain.CityRank;
import com.war.domain.GuildPlayer;
import com.war.domain.GuildRank;
import com.war.domain.PlayerRank;
import com.war.service.IGuildService;
import com.war.service.IRankService;
import com.war.service.ITreasureService;

public class RankRewardTreasureTest {

	private static IRankService rankService;
	private static ITreasureService treasureService;
	private static IGuildService guildService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rankService = (IRankService)SpringService.getApplicationContext().getBean("rankService");
		treasureService = (ITreasureService)SpringService.getApplicationContext().getBean("treasureService");
		guildService = (IGuildService)SpringService.getApplicationContext().getBean("guildService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCityRank(){
		List<CityRank> cityRankList = rankService.getCityPopulationRankListByRank(1);
		int baseNum = 10;
		int treasureID = 26;
		for(int i=0;i<cityRankList.size();i++){
			treasureService.increasePlayerTreasure(cityRankList.get(i).getPlayerID(), treasureID, baseNum);
			baseNum--;
		}
	}
	
	@Test
	public void testPlayerRank(){
		List<PlayerRank> playerRankList = rankService.getPlayerRankListByRank(1);
		int baseNum = 10;
		int treasureID = 26;
		for(int i=0;i<playerRankList.size();i++){
			treasureService.increasePlayerTreasure(playerRankList.get(i).getPlayerID(), treasureID, baseNum);
			baseNum--;
		}
	}
	
	@Test
	public void testGuildRank(){
		List<GuildRank> guildRankList = rankService.getGuildRankListByRank(1);
		int guildID = guildRankList.get(0).getGuildID();
		List<GuildPlayer> guildPlayerList = guildService.getGuildMemberList(guildID);
		int treasureID = 26;
		for(int i=0;i<guildPlayerList.size();i++){
			treasureService.increasePlayerTreasure(guildPlayerList.get(i).getPlayerID(), treasureID, 1);
		}
	}
	
}