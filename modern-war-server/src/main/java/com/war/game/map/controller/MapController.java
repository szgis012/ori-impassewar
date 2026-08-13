package com.war.game.map.controller;

import com.war.dto.ApiResponse;
import com.war.game.map.dto.MapTileDTO;
import com.war.game.map.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地图系统控制器
 */
@RestController
@RequestMapping("/api/map")
@Slf4j
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    /**
     * 初始化地图 (仅管理员调用)
     */
    @PostMapping("/initialize")
    public ApiResponse<Map<String, Object>> initializeMap(@RequestParam(defaultValue = "50") int size) {
        log.info("初始化地图，大小：{}x{}", size, size);
        mapService.initializeMap(size);
        
        Map<String, Object> result = new HashMap<>();
        result.put("mapSize", size);
        result.put("totalTiles", size * size);
        result.put("message", "地图初始化完成");
        
        return ApiResponse.success(result);
    }

    /**
     * 获取单个格子信息
     */
    @GetMapping("/tile")
    public ApiResponse<MapTileDTO> getTile(@RequestParam Integer x, @RequestParam Integer y) {
        MapTileDTO tile = mapService.getTile(x, y);
        return ApiResponse.success(tile);
    }

    /**
     * 获取指定范围的地图
     */
    @GetMapping("/tiles")
    public ApiResponse<List<MapTileDTO>> getTilesInRange(
            @RequestParam Integer centerX,
            @RequestParam Integer centerY,
            @RequestParam(defaultValue = "5") Integer range) {
        List<MapTileDTO> tiles = mapService.getTilesInRange(centerX, centerY, range);
        return ApiResponse.success(tiles);
    }

    /**
     * 获取玩家占领的领地
     */
    @GetMapping("/my-tiles")
    public ApiResponse<List<MapTileDTO>> getPlayerTiles(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long playerId = getCurrentPlayerId(userDetails);
        List<MapTileDTO> tiles = mapService.getPlayerTiles(playerId);
        return ApiResponse.success(tiles);
    }

    /**
     * 占领资源点
     */
    @PostMapping("/occupy")
    public ApiResponse<MapTileDTO> occupyTile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Integer x,
            @RequestParam Integer y) {
        log.info("占领请求：坐标 ({},{})", x, y);
        
        Long playerId = getCurrentPlayerId(userDetails);
        MapTileDTO tile = mapService.occupyTile(playerId, x, y);
        return ApiResponse.success(tile);
    }

    /**
     * 放弃占领
     */
    @PostMapping("/abandon")
    public ApiResponse<MapTileDTO> abandonTile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Integer x,
            @RequestParam Integer y) {
        log.info("放弃占领：坐标 ({},{})", x, y);
        
        Long playerId = getCurrentPlayerId(userDetails);
        MapTileDTO tile = mapService.abandonTile(playerId, x, y);
        return ApiResponse.success(tile);
    }

    /**
     * 获取当前玩家 ID (简化实现)
     */
    private Long getCurrentPlayerId(UserDetails userDetails) {
        String username = userDetails.getUsername();
        if (username.startsWith("player_")) {
            return Long.parseLong(username.substring(7));
        }
        return 1L;
    }
}
