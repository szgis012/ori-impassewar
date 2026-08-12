package com.war.test.service;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.CacheService;
import com.war.common.SpringService;
import com.war.constant.CacheConstant;
import com.war.constant.HeroConstant;
import com.war.constant.TreasureConstant;
import com.war.domain.CityCandidacyHero;
import com.war.domain.CityHero;
import com.war.domain.CityHeroExt;
import com.war.domain.TreasureHistory;
import com.war.service.ICityService;
import com.war.service.IEquipmentService;
import com.war.service.IHeroService;
import com.war.service.IPlayerService;
import com.war.service.ITreasureService;

public class HeroServiceTest {

	private static IHeroService heroService;
	private static ITreasureService treasureService;
	private static ICityService cityService;
	private static IEquipmentService equipmentService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		heroService = (IHeroService)SpringService.getApplicationContext().getBean("heroService");
		treasureService = (ITreasureService) SpringService.getApplicationContext().getBean("treasureService");
		cityService = (ICityService) SpringService.getApplicationContext().getBean("cityService");
		
		// 装备缓存
		equipmentService = (IEquipmentService)SpringService.getApplicationContext().getBean("equipmentService");
		CacheService.putToCache(CacheConstant.EQUIPMENTS_MAP, equipmentService.initEquipmentsMap());
		
		// 城市编号-城市名称对应缓存
		CacheService.putToCache(CacheConstant.CITYID_PLAYERID_MAP, cityService.initCityIDPlayerIDMap());
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
	
	//@Test
	public void testGenerateCityCandidacyHeroList() {
		heroService.refreshCityCandidacyHeroList(8);
	}
	
	//@Test
	public void testRecruitHero() {
		List<CityCandidacyHero> cityCandidacyHeroList = heroService.getCityCandidacyHeroList(8);
		heroService.recruitHero(cityCandidacyHeroList.get(0).getCityCandidacyHeroID());
	}
	
	// @Test
	public void testAddMilitarySpirit() {
		heroService.addMilitarySpirit(16);
	}
	
	// @Test
	public void testStrengthenCityHeroStar() {
		heroService.strengthenCityHeroStar(8, 16, 0, 0);
	}
	
	// @Test
	public void testHandleCityHeroRunAway() {
		heroService.handleCityHeroRunAway();
	}
	
	@Test
	public void testResetBugCityHero() {
		Random random = new Random();
		List<CityHero> cityHeroList = heroService.getBugCityHeroList();
		List<TreasureHistory> useTreasureHistoryList = null;
		int willDistributePoint = 0;
		int playerID = 0;
		
		// 攻击、防御、思维、行政
		int[] characterArray = new int[4];
		
		int tmp;
		CityHeroExt cityHeroExt = null;
		CityHeroExt tmpCityHeroExt = new CityHeroExt();
		
		if (cityHeroList.size() > 0) { 
			playerID = cityService.getPlayerIDByCityID(cityHeroList.get(0).getCityID());
			useTreasureHistoryList = treasureService.getTreasureHistoryList(playerID, TreasureConstant.ADVANCE_REBORN_MEDICAMENT, TreasureConstant.HISTORY_USE_TYPE);
			
			if (useTreasureHistoryList.size() > 0)
				treasureService.increasePlayerTreasure(playerID, TreasureConstant.ADVANCE_REBORN_MEDICAMENT, useTreasureHistoryList.size());
		}
		
		System.out.println(cityHeroList.size());
		for (CityHero cityHero : cityHeroList) {
			
			characterArray[0] = 0;
			characterArray[1] = 0;
			characterArray[2] = 0;
			characterArray[3] = 0;
			
			if (cityHero.getLevel() == 1) {
				willDistributePoint = cityHero.getLevel() * (random.nextInt(HeroConstant.HERO_POINT_MULTIPLE_MAX)+1) + HeroConstant.HERO_BASE_POINT;
			} else {
				willDistributePoint = (cityHero.getLevel() - 1) * 6;
			}
			
			for (int i = willDistributePoint; i > 0; i--) {
				tmp = random.nextInt(4);
				// System.out.print(tmp+",");
				characterArray[tmp]++;
				willDistributePoint--;
			}
			
			tmpCityHeroExt.setCommandEquipmentAdd(0);
			tmpCityHeroExt.setDefenseEquipmentAdd(0);
			tmpCityHeroExt.setMindEquipmentAdd(0);
			tmpCityHeroExt.setExecutivepowerEquipmentAdd(0);
			
			if (cityHero.getEquipmentEpauletObject() != null) {
				tmpCityHeroExt.setCommandEquipmentAdd(tmpCityHeroExt.getCommandEquipmentAdd() + cityHero.getEquipmentEpauletObject().getCommand());
				tmpCityHeroExt.setDefenseEquipmentAdd(tmpCityHeroExt.getDefenseEquipmentAdd() + cityHero.getEquipmentEpauletObject().getDefense());
				tmpCityHeroExt.setMindEquipmentAdd(tmpCityHeroExt.getMindEquipmentAdd() + cityHero.getEquipmentEpauletObject().getMind());
				tmpCityHeroExt.setExecutivepowerEquipmentAdd(tmpCityHeroExt.getExecutivepowerEquipmentAdd() + cityHero.getEquipmentEpauletObject().getExecutivepower());
			}
			
			if (cityHero.getEquipmentCapObject() != null) {
				tmpCityHeroExt.setCommandEquipmentAdd(tmpCityHeroExt.getCommandEquipmentAdd() + cityHero.getEquipmentCapObject().getCommand());
				tmpCityHeroExt.setDefenseEquipmentAdd(tmpCityHeroExt.getDefenseEquipmentAdd() + cityHero.getEquipmentCapObject().getDefense());
				tmpCityHeroExt.setMindEquipmentAdd(tmpCityHeroExt.getMindEquipmentAdd() + cityHero.getEquipmentCapObject().getMind());
				tmpCityHeroExt.setExecutivepowerEquipmentAdd(tmpCityHeroExt.getExecutivepowerEquipmentAdd() + cityHero.getEquipmentCapObject().getExecutivepower());
			}
			
			if (cityHero.getEquipmentClothesObject() != null) {
				tmpCityHeroExt.setCommandEquipmentAdd(tmpCityHeroExt.getCommandEquipmentAdd() + cityHero.getEquipmentClothesObject().getCommand());
				tmpCityHeroExt.setDefenseEquipmentAdd(tmpCityHeroExt.getDefenseEquipmentAdd() + cityHero.getEquipmentClothesObject().getDefense());
				tmpCityHeroExt.setMindEquipmentAdd(tmpCityHeroExt.getMindEquipmentAdd() + cityHero.getEquipmentClothesObject().getMind());
				tmpCityHeroExt.setExecutivepowerEquipmentAdd(tmpCityHeroExt.getExecutivepowerEquipmentAdd() + cityHero.getEquipmentClothesObject().getExecutivepower());
			}
			
			if (cityHero.getEquipmentShoeObject() != null) {
				tmpCityHeroExt.setCommandEquipmentAdd(tmpCityHeroExt.getCommandEquipmentAdd() + cityHero.getEquipmentShoeObject().getCommand());
				tmpCityHeroExt.setDefenseEquipmentAdd(tmpCityHeroExt.getDefenseEquipmentAdd() + cityHero.getEquipmentShoeObject().getDefense());
				tmpCityHeroExt.setMindEquipmentAdd(tmpCityHeroExt.getMindEquipmentAdd() + cityHero.getEquipmentShoeObject().getMind());
				tmpCityHeroExt.setExecutivepowerEquipmentAdd(tmpCityHeroExt.getExecutivepowerEquipmentAdd() + cityHero.getEquipmentShoeObject().getExecutivepower());
			}
			
			if (cityHero.getEquipmentWeaponObject() != null) {
				tmpCityHeroExt.setCommandEquipmentAdd(tmpCityHeroExt.getCommandEquipmentAdd() + cityHero.getEquipmentWeaponObject().getCommand());
				tmpCityHeroExt.setDefenseEquipmentAdd(tmpCityHeroExt.getDefenseEquipmentAdd() + cityHero.getEquipmentWeaponObject().getDefense());
				tmpCityHeroExt.setMindEquipmentAdd(tmpCityHeroExt.getMindEquipmentAdd() + cityHero.getEquipmentWeaponObject().getMind());
				tmpCityHeroExt.setExecutivepowerEquipmentAdd(tmpCityHeroExt.getExecutivepowerEquipmentAdd() + cityHero.getEquipmentWeaponObject().getExecutivepower());
			}
			
			cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHero.getCityHeroID());
			if (tmpCityHeroExt.getCommandEquipmentAdd() != cityHeroExt.getCommandEquipmentAdd() || 
					tmpCityHeroExt.getDefenseEquipmentAdd() != cityHeroExt.getDefenseEquipmentAdd() || 
					tmpCityHeroExt.getMindEquipmentAdd() != cityHeroExt.getMindEquipmentAdd() || 
					tmpCityHeroExt.getExecutivepowerEquipmentAdd() != cityHeroExt.getExecutivepowerEquipmentAdd()) {
				
				cityHeroExt.setCommandEquipmentAdd(tmpCityHeroExt.getCommandEquipmentAdd());
				cityHeroExt.setDefenseEquipmentAdd(tmpCityHeroExt.getDefenseEquipmentAdd());
				cityHeroExt.setMindEquipmentAdd(tmpCityHeroExt.getMindEquipmentAdd());
				cityHeroExt.setExecutivepowerEquipmentAdd(tmpCityHeroExt.getExecutivepowerEquipmentAdd());
				
				heroService.updateCityHeroExt(cityHeroExt);
			}
			
			characterArray[0] += cityHeroExt.getCommandEquipmentAdd() + cityHeroExt.getCommandTreasureAdd();
			characterArray[1] += cityHeroExt.getDefenseEquipmentAdd() + cityHeroExt.getDefenseTreasureAdd();
			characterArray[2] += cityHeroExt.getMindEquipmentAdd() + cityHeroExt.getMindTreasureAdd();
			characterArray[3] += cityHeroExt.getExecutivepowerEquipmentAdd() + cityHeroExt.getExecutivepowerTreasureAdd();
			
			cityHero.setCommand(characterArray[0]);
			cityHero.setDefense(characterArray[1]);
			cityHero.setMind(characterArray[2]);
			cityHero.setExecutivepower(characterArray[3]);
			
			heroService.updateCityHero(cityHero);
		}
		
		
	}
}
