package com.war.game.city.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 城市管理服务
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CityService {
    
    private final CityRepository cityRepository;
    private final BuildingRepository buildingRepository;
    private final TechnologyRepository technologyRepository;
    
    /**
     * 获取玩家城市列表
     */
    @Transactional(readOnly = true)
    public List<CityDTO> getCitiesByPlayerId(Long playerId) {
        List<City> cities = cityRepository.findByPlayerId(playerId);
        return cities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 获取玩家主城
     */
    @Transactional(readOnly = true)
    public CityDTO getMainCity(Long playerId) {
        City city = cityRepository.findFirstByPlayerIdOrderByCreatedAtAsc(playerId)
                .orElseThrow(() -> new RuntimeException("玩家没有城市"));
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
     * 升级建筑
     */
    public BuildingDTO upgradeBuilding(Long cityId, String buildingType) {
        Building building = buildingRepository.findByCityIdAndType(cityId, buildingType);
        if (building == null) {
            throw new RuntimeException("建筑不存在");
        }
        
        // TODO: 检查资源是否足够、检查前置条件等
        
        building.setLevel(building.getLevel() + 1);
        Building savedBuilding = buildingRepository.save(building);
        
        // 更新城市资源产量
        updateCityProduction(cityId);
        
        return convertToDTO(savedBuilding);
    }
    
    /**
     * 开始研究科技
     */
    public TechnologyDTO researchTechnology(Long cityId, String techType, Integer researchTimeSeconds) {
        // 检查是否已在研究
        technologyRepository.findByCityIdAndType(cityId, techType)
                .ifPresent(existing -> {
                    if (existing.getFinishedAt() != null && 
                        existing.getFinishedAt().isAfter(LocalDateTime.now())) {
                        throw new RuntimeException("正在研究中");
                    }
                });
        
        Technology tech = new Technology();
        tech.setCityId(cityId);
        tech.setType(techType);
        tech.setLevel(1);
        tech.setResearchingAt(LocalDateTime.now());
        tech.setFinishedAt(LocalDateTime.now().plusSeconds(researchTimeSeconds));
        
        Technology savedTech = technologyRepository.save(tech);
        return convertToDTO(savedTech);
    }
    
    /**
     * 收集资源
     */
    public CityDTO collectResources(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("城市不存在"));
        
        // TODO: 实现资源收集逻辑 (根据时间和产量计算)
        
        return convertToDTO(city);
    }
    
    /**
     * 更新城市资源产量
     */
    private void updateCityProduction(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("城市不存在"));
        
        List<Building> buildings = buildingRepository.findByCityId(cityId);
        
        // 根据建筑等级计算资源产量
        int goldProd = 100;
        int woodProd = 100;
        int stoneProd = 100;
        int foodProd = 100;
        
        for (Building building : buildings) {
            switch (building.getType()) {
                case "GOLD_MINE":
                    goldProd += building.getLevel() * 50;
                    break;
                case "LUMBER_MILL":
                    woodProd += building.getLevel() * 50;
                    break;
                case "QUARRY":
                    stoneProd += building.getLevel() * 50;
                    break;
                case "FARM":
                    foodProd += building.getLevel() * 50;
                    break;
            }
        }
        
        city.setGoldProduction(goldProd);
        city.setWoodProduction(woodProd);
        city.setStoneProduction(stoneProd);
        city.setFoodProduction(foodProd);
        
        cityRepository.save(city);
    }
    
    /**
     * 转换为 DTO
     */
    private CityDTO convertToDTO(City city) {
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
        dto.setGoldProduction(city.getGoldProduction());
        dto.setWoodProduction(city.getWoodProduction());
        dto.setStoneProduction(city.getStoneProduction());
        dto.setFoodProduction(city.getFoodProduction());
        
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
