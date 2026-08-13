package com.war.game.city.controller;

import com.war.game.common.ApiResponse;
import com.war.game.city.dto.BuildingDTO;
import com.war.game.city.dto.CityDTO;
import com.war.game.city.dto.TechnologyDTO;
import com.war.game.city.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市管理控制器
 */
@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {
    
    private final CityService cityService;
    
    /**
     * 获取玩家城市列表
     */
    @GetMapping("/list")
    public ApiResponse<List<CityDTO>> getCities(@AuthenticationPrincipal UserDetails userDetails) {
        Long playerId = Long.valueOf(userDetails.getUsername());
        List<CityDTO> cities = cityService.getCitiesByPlayerId(playerId);
        return ApiResponse.success(cities);
    }
    
    /**
     * 获取主城信息
     */
    @GetMapping("/main")
    public ApiResponse<CityDTO> getMainCity(@AuthenticationPrincipal UserDetails userDetails) {
        Long playerId = Long.valueOf(userDetails.getUsername());
        CityDTO city = cityService.getMainCity(playerId);
        return ApiResponse.success(city);
    }
    
    /**
     * 创建新城市
     */
    @PostMapping("/create")
    public ApiResponse<CityDTO> createCity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateCityRequest request) {
        Long playerId = Long.valueOf(userDetails.getUsername());
        CityDTO city = cityService.createCity(
                playerId, 
                request.getName(), 
                request.getPosX(), 
                request.getPosY()
        );
        return ApiResponse.success(city);
    }
    
    /**
     * 升级建筑
     */
    @PostMapping("/building/upgrade")
    public ApiResponse<BuildingDTO> upgradeBuilding(
            @RequestParam Long cityId,
            @RequestParam String type) {
        BuildingDTO building = cityService.upgradeBuilding(cityId, type);
        return ApiResponse.success(building);
    }
    
    /**
     * 研究科技
     */
    @PostMapping("/technology/research")
    public ApiResponse<TechnologyDTO> researchTechnology(
            @RequestParam Long cityId,
            @RequestParam String type,
            @RequestParam Integer researchTimeSeconds) {
        TechnologyDTO technology = cityService.researchTechnology(cityId, type, researchTimeSeconds);
        return ApiResponse.success(technology);
    }
    
    /**
     * 收集资源
     */
    @PostMapping("/collect")
    public ApiResponse<CityDTO> collectResources(@RequestParam Long cityId) {
        CityDTO city = cityService.collectResources(cityId);
        return ApiResponse.success(city);
    }
    
    /**
     * 创建城市请求
     */
    @lombok.Data
    public static class CreateCityRequest {
        private String name;
        private Double posX;
        private Double posY;
    }
}
