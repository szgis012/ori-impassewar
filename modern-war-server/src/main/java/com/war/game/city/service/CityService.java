package com.war.game.city.service;

import com.war.game.city.config.BuildingConfig;
import com.war.game.city.dto.BuildingDTO;
import com.war.game.city.dto.CityDTO;
import com.war.game.city.dto.TechnologyDTO;
import com.war.game.city.entity.Building;
import com.war.game.city.entity.City;
import com.war.game.city.entity.Technology;
import com.war.game.city.repository.BuildingRepository;
import com.war.game.city.repository.CityRepository;
import com.war.game.city.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 城市管理服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CityService {
    
    private final CityRepository cityRepository;
    private final BuildingRepository buildingRepository;
    private final TechnologyRepository technologyRepository;
    
    /**
     * 获取玩家城市列表 (自动更新资源)
     */
    @Transactional(readOnly = true)
    public List<CityDTO> getCitiesByPlayerId(Long playerId) {
        List<City> cities = cityRepository.findByPlayerId(playerId);
        // 更新每个城市的资源
        cities.forEach(City::updateResources);
        return cities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 获取玩家主城 (自动更新资源)
     */
    @Transactional(readOnly = true)
    public CityDTO getMainCity(Long playerId) {
        City city = cityRepository.findFirstByPlayerIdOrderByCreatedAtAsc(playerId)
                .orElseThrow(() -> new RuntimeException("玩家没有城市"));
        // 更新资源
        city.updateResources();
        return convertToDTO(city);
    }
    
    /**
     * 创建新城市
     */
    public CityDTO createCity(Long playerId, String name, Double posX, Double posY) {
        City city = new City();
        city.setPlayerId(playerId);
        city.setName(name);
        city.setLevel(1);
        city.setPosX(posX);
        city.setPosY(posY);
        
        City savedCity = cityRepository.save(city);
        
        // 创建初始建筑 (市政厅)
        Building townHall = new Building();
        townHall.setCityId(savedCity.getId());
        townHall.setType("TOWN_HALL");
        townHall.setLevel(1);
        townHall.setPosX(0);
        townHall.setPosY(0);
        buildingRepository.save(townHall);
        
        return convertToDTO(savedCity);
    }
    
    /**
     * 升级建筑 (带资源检查、前置条件验证)
     */
    public BuildingDTO upgradeBuilding(Long cityId, String buildingType) {
        // 获取城市
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("城市不存在"));
        
        // 先更新资源
        city.updateResources();
        
        // 获取建筑
        Building building = buildingRepository.findByCityIdAndType(cityId, buildingType);
        if (building == null) {
            throw new RuntimeException("建筑不存在");
        }
        
        // 获取建筑配置
        BuildingConfig config = BuildingConfig.getConfig(buildingType);
        if (config == null) {
            throw new RuntimeException("无效的建筑类型: " + buildingType);
        }
        
        // 1. 检查前置条件
        Map<String, Integer> buildingLevels = getCityBuildingLevels(cityId);
        if (!config.checkPrerequisites(buildingLevels)) {
            throw new RuntimeException("不满足建筑前置条件");
        }
        
        // 2. 计算升级成本
        BuildingConfig.ResourceCost upgradeCost = config.getUpgradeCost(building.getLevel());
        
        // 3. 检查资源是否足够
        if (!upgradeCost.canAfford(city.getGold(), city.getWood(), city.getStone(), city.getFood())) {
            throw new RuntimeException(String.format(
                "资源不足！需要：金币%d, 木材%d, 石材%d, 粮食%d",
                upgradeCost.getGold(), upgradeCost.getWood(), 
                upgradeCost.getStone(), upgradeCost.getFood()
            ));
        }
        
        // 4. 扣除资源
        city.setGold(city.getGold() - upgradeCost.getGold());
        city.setWood(city.getWood() - upgradeCost.getWood());
        city.setStone(city.getStone() - upgradeCost.getStone());
        city.setFood(city.getFood() - upgradeCost.getFood());
        
        // 5. 升级建筑
        building.setLevel(building.getLevel() + 1);
        Building savedBuilding = buildingRepository.save(building);
        
        // 6. 更新城市资源产量
        updateCityProduction(cityId);
        
        // 保存城市资源变化
        cityRepository.save(city);
        
        log.info("玩家 {} 在城市 {} 升级建筑 {} 到 {} 级，消耗资源：金{},木{},石{},粮{}",
                city.getPlayerId(), city.getName(), buildingType, building.getLevel(),
                upgradeCost.getGold(), upgradeCost.getWood(), 
                upgradeCost.getStone(), upgradeCost.getFood());
        
        return convertToDTO(savedBuilding);
    }
    
    /**
     * 获取城市所有建筑的等级映射
     */
    private Map<String, Integer> getCityBuildingLevels(Long cityId) {
        List<Building> buildings = buildingRepository.findByCityId(cityId);
        Map<String, Integer> levels = new HashMap<>();
        for (Building building : buildings) {
            levels.put(building.getType(), building.getLevel());
        }
        return levels;
    }
    
    /**
     * 开始研究科技 (带资源检查)
     */
    public TechnologyDTO researchTechnology(Long cityId, String techType, Integer researchTimeSeconds) {
        // 获取城市
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("城市不存在"));
        
        // 先更新资源
        city.updateResources();
        
        // 检查是否已在研究
        technologyRepository.findByCityIdAndType(cityId, techType)
                .ifPresent(existing -> {
                    if (existing.getFinishedAt() != null && 
                        existing.getFinishedAt().isAfter(LocalDateTime.now())) {
                        throw new RuntimeException("正在研究中");
                    }
                });
        
        // TODO: 添加科技配置，检查前置条件和资源消耗
        
        Technology tech = new Technology();
        tech.setCityId(cityId);
        tech.setType(techType);
        tech.setLevel(1);
        tech.setResearchingAt(LocalDateTime.now());
        tech.setFinishedAt(LocalDateTime.now().plusSeconds(researchTimeSeconds));
        
        Technology savedTech = technologyRepository.save(tech);
        
        log.info("玩家 {} 在城市 {} 开始研究科技 {}", city.getPlayerId(), city.getName(), techType);
        
        return convertToDTO(savedTech);
    }
    
    /**
     * 收集资源 (手动领取累积的资源)
     */
    public CityDTO collectResources(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("城市不存在"));
        
        // 更新资源 (基于时间和产量自动计算)
        city.updateResources();
        
        City savedCity = cityRepository.save(city);
        
        log.info("玩家 {} 在城市 {} 收集资源，当前：金{},木{},石{},粮{}",
                city.getPlayerId(), city.getName(),
                city.getGold(), city.getWood(), city.getStone(), city.getFood());
        
        return convertToDTO(savedCity);
    }
    
    /**
     * 更新城市资源产量 (基于建筑配置)
     */
    private void updateCityProduction(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("城市不存在"));
        
        List<Building> buildings = buildingRepository.findByCityId(cityId);
        
        // 重置为基础产量
        int goldProd = 100;
        int woodProd = 100;
        int stoneProd = 100;
        int foodProd = 100;
        
        for (Building building : buildings) {
            BuildingConfig config = BuildingConfig.getConfig(building.getType());
            if (config != null) {
                // 根据建筑配置计算产量
                BuildingConfig.ResourceProduction production = 
                    config.getProductionAtLevel(building.getLevel());
                goldProd += production.getGoldPerHour();
                woodProd += production.getWoodPerHour();
                stoneProd += production.getStonePerHour();
                foodProd += production.getFoodPerHour();
            }
        }
        
        city.setGoldProduction(goldProd);
        city.setWoodProduction(woodProd);
        city.setStoneProduction(stoneProd);
        city.setFoodProduction(foodProd);
        
        cityRepository.save(city);
        
        log.debug("城市 {} 资源产量更新：金{}/h, 木{}/h, 石{}/h, 粮{}/h",
                city.getName(), goldProd, woodProd, stoneProd, foodProd);
    }
    
    /**
     * 转换为 DTO
     */
    private CityDTO convertToDTO(City city) {
        // 先更新资源
        city.updateResources();
        
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setLevel(city.getLevel());
        dto.setPosX(city.getPosX());
        dto.setPosY(city.getPosY());
        dto.setGold(city.getGold());
        dto.setWood(city.getWood());
        dto.setStone(city.getStone());
        dto.setFood(city.getFood());
        dto.setGoldCap(city.getGoldCap());
        dto.setWoodCap(city.getWoodCap());
        dto.setStoneCap(city.getStoneCap());
        dto.setFoodCap(city.getFoodCap());
        dto.setGoldProduction(city.getGoldProduction());
        dto.setWoodProduction(city.getWoodProduction());
        dto.setStoneProduction(city.getStoneProduction());
        dto.setFoodProduction(city.getFoodProduction());
        dto.setLastResourceUpdate(city.getLastResourceUpdate());
        
        // 加载建筑和科技
        List<Building> buildings = buildingRepository.findByCityId(city.getId());
        dto.setBuildings(buildings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        
        List<Technology> technologies = technologyRepository.findByCityId(city.getId());
        dto.setTechnologies(technologies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }
    
    private BuildingDTO convertToDTO(Building building) {
        BuildingDTO dto = new BuildingDTO();
        dto.setId(building.getId());
        dto.setType(building.getType());
        dto.setLevel(building.getLevel());
        dto.setPosX(building.getPosX());
        dto.setPosY(building.getPosY());
        dto.setCreatedAt(building.getCreatedAt());
        dto.setUpdatedAt(building.getUpdatedAt());
        return dto;
    }
    
    private TechnologyDTO convertToDTO(Technology technology) {
        TechnologyDTO dto = new TechnologyDTO();
        dto.setId(technology.getId());
        dto.setType(technology.getType());
        dto.setLevel(technology.getLevel());
        dto.setResearchingAt(technology.getResearchingAt());
        dto.setFinishedAt(technology.getFinishedAt());
        dto.setCreatedAt(technology.getCreatedAt());
        dto.setUpdatedAt(technology.getUpdatedAt());
        return dto;
    }
}
