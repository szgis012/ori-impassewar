package com.war.game.map.service;

import com.war.game.city.entity.City;
import com.war.game.city.repository.CityRepository;
import com.war.game.map.dto.MapTileDTO;
import com.war.game.map.entity.MapTile;
import com.war.game.map.repository.MapTileRepository;
import com.war.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 地图系统服务类
 * 处理地图生成、查询、占领等逻辑
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MapService {

    private final MapTileRepository mapTileRepository;
    private final CityRepository cityRepository;
    private final PlayerRepository playerRepository;

    /**
     * 地形类型名称映射
     */
    private static final Map<Integer, String> TERRAIN_NAMES = new HashMap<>();
    static {
        TERRAIN_NAMES.put(1, "平原");
        TERRAIN_NAMES.put(2, "森林");
        TERRAIN_NAMES.put(3, "山地");
        TERRAIN_NAMES.put(4, "河流");
        TERRAIN_NAMES.put(5, "沙漠");
        TERRAIN_NAMES.put(6, "沼泽");
    }

    /**
     * 资源类型名称映射
     */
    private static final Map<Integer, String> RESOURCE_NAMES = new HashMap<>();
    static {
        RESOURCE_NAMES.put(0, "无");
        RESOURCE_NAMES.put(1, "金矿");
        RESOURCE_NAMES.put(2, "伐木场");
        RESOURCE_NAMES.put(3, "采石场");
        RESOURCE_NAMES.put(4, "农田");
    }

    /**
     * 通行难度映射
     */
    private static final Map<Integer, Integer> TERRAIN_DIFFICULTY = new HashMap<>();
    static {
        TERRAIN_DIFFICULTY.put(1, 1); // 平原 - 容易
        TERRAIN_DIFFICULTY.put(2, 2); // 森林 - 普通
        TERRAIN_DIFFICULTY.put(3, 3); // 山地 - 困难
        TERRAIN_DIFFICULTY.put(4, 4); // 河流 - 无法通行
        TERRAIN_DIFFICULTY.put(5, 2); // 沙漠 - 普通
        TERRAIN_DIFFICULTY.put(6, 3); // 沼泽 - 困难
    }

    /**
     * 初始化地图 (一次性调用)
     * @param mapSize 地图大小 (mapSize x mapSize)
     */
    @Transactional
    public void initializeMap(int mapSize) {
        log.info("开始初始化 {}x{} 的地图", mapSize, mapSize);
        
        Random random = new Random();
        int center = mapSize / 2;
        
        for (int x = -center; x <= center; x++) {
            for (int y = -center; y <= center; y++) {
                // 检查是否已存在
                if (mapTileRepository.findByXAndY(x, y).isPresent()) {
                    continue;
                }
                
                MapTile tile = new MapTile();
                tile.setX(x);
                tile.setY(y);
                
                // 根据距离中心点的距离生成地形
                int distance = Math.abs(x) + Math.abs(y);
                int terrainType = generateTerrainType(distance, random);
                
                tile.setTerrainType(terrainType);
                tile.setTerrainName(TERRAIN_NAMES.get(terrainType));
                tile.setPassDifficulty(TERRAIN_DIFFICULTY.get(terrainType));
                
                // 随机生成资源点 (10% 概率)
                if (random.nextInt(100) < 10 && terrainType != 4) { // 河流不能有资源
                    tile.setResourceType(random.nextInt(4) + 1);
                    tile.setResourceLevel(random.nextInt(5) + 1); // 1-5 级资源
                } else {
                    tile.setResourceType(0);
                    tile.setResourceLevel(0);
                }
                
                mapTileRepository.save(tile);
            }
        }
        
        log.info("地图初始化完成");
    }

    /**
     * 根据距离生成地形类型
     */
    private int generateTerrainType(int distance, Random random) {
        // 中心区域主要是平原
        if (distance < 5) {
            return random.nextInt(100) < 70 ? 1 : 2; // 70% 平原，30% 森林
        }
        // 中等距离混合地形
        if (distance < 15) {
            int rand = random.nextInt(100);
            if (rand < 40) return 1; // 平原
            if (rand < 70) return 2; // 森林
            if (rand < 85) return 3; // 山地
            if (rand < 95) return 5; // 沙漠
            return 6; // 沼泽
        }
        // 边缘区域更多山地和沙漠
        int rand = random.nextInt(100);
        if (rand < 30) return 1; // 平原
        if (rand < 50) return 2; // 森林
        if (rand < 70) return 3; // 山地
        if (rand < 85) return 5; // 沙漠
        return 6; // 沼泽
    }

    /**
     * 获取单个格子信息
     */
    public MapTileDTO getTile(Integer x, Integer y) {
        MapTile tile = mapTileRepository.findByXAndY(x, y)
                .orElseThrow(() -> new RuntimeException("坐标不存在"));
        return convertToDTO(tile);
    }

    /**
     * 获取指定范围的地图格子
     */
    public List<MapTileDTO> getTilesInRange(Integer centerX, Integer centerY, Integer range) {
        int xStart = centerX - range;
        int xEnd = centerX + range;
        int yStart = centerY - range;
        int yEnd = centerY + range;
        
        List<MapTile> tiles = mapTileRepository.findByXBetweenAndYBetween(xStart, xEnd, yStart, yEnd);
        return tiles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * 获取玩家占领的所有格子
     */
    public List<MapTileDTO> getPlayerTiles(Long playerId) {
        List<MapTile> tiles = mapTileRepository.findByOwnerId(playerId);
        return tiles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * 占领资源点
     */
    @Transactional
    public MapTileDTO occupyTile(Long playerId, Integer x, Integer y) {
        log.info("玩家 {} 尝试占领坐标 ({},{})", playerId, x, y);
        
        MapTile tile = mapTileRepository.findByXAndY(x, y)
                .orElseThrow(() -> new RuntimeException("坐标不存在"));
        
        // 检查是否有城市
        if (tile.getHasCity()) {
            throw new RuntimeException("该位置已有城市，无法占领");
        }
        
        // 检查是否已被占领
        if (tile.getOwnerId() != null) {
            throw new RuntimeException("该位置已被其他玩家占领");
        }
        
        // 检查是否有资源
        if (tile.getResourceType() == 0) {
            throw new RuntimeException("该位置没有资源，无法占领");
        }
        
        // 占领
        tile.setOwnerId(playerId);
        tile.setOccupyTime(LocalDateTime.now());
        tile = mapTileRepository.save(tile);
        
        return convertToDTO(tile);
    }

    /**
     * 放弃占领
     */
    @Transactional
    public MapTileDTO abandonTile(Long playerId, Integer x, Integer y) {
        log.info("玩家 {} 放弃占领坐标 ({},{})", playerId, x, y);
        
        MapTile tile = mapTileRepository.findByXAndY(x, y)
                .orElseThrow(() -> new RuntimeException("坐标不存在"));
        
        if (!tile.getOwnerId().equals(playerId)) {
            throw new RuntimeException("无权放弃该领地");
        }
        
        tile.setOwnerId(null);
        tile.setOccupyTime(null);
        tile = mapTileRepository.save(tile);
        
        return convertToDTO(tile);
    }

    /**
     * 在城市位置创建地图格子
     */
    @Transactional
    public MapTileDTO createCityTile(Long cityId, Integer x, Integer y, Long ownerId) {
        MapTile tile = mapTileRepository.findByXAndY(x, y)
                .orElseGet(() -> {
                    MapTile newTile = new MapTile();
                    newTile.setX(x);
                    newTile.setY(y);
                    newTile.setTerrainType(1); // 城市所在地设为平原
                    newTile.setTerrainName("平原");
                    newTile.setPassDifficulty(1);
                    newTile.setResourceType(0);
                    newTile.setResourceLevel(0);
                    return newTile;
                });
        
        tile.setHasCity(true);
        tile.setCityId(cityId);
        tile.setOwnerId(ownerId);
        tile.setOccupyTime(LocalDateTime.now());
        
        tile = mapTileRepository.save(tile);
        return convertToDTO(tile);
    }

    /**
     * MapTile 转 DTO
     */
    private MapTileDTO convertToDTO(MapTile tile) {
        MapTileDTO dto = new MapTileDTO();
        dto.setId(tile.getId());
        dto.setX(tile.getX());
        dto.setY(tile.getY());
        dto.setTerrainType(tile.getTerrainType());
        dto.setTerrainName(tile.getTerrainName());
        dto.setResourceType(tile.getResourceType());
        dto.setResourceName(RESOURCE_NAMES.get(tile.getResourceType()));
        dto.setResourceLevel(tile.getResourceLevel());
        dto.setOwnerId(tile.getOwnerId());
        dto.setHasCity(tile.getHasCity());
        dto.setCityId(tile.getCityId());
        dto.setPassDifficulty(tile.getPassDifficulty());
        
        // TODO: 获取所有者名称
        if (tile.getOwnerId() != null) {
            dto.setOwnerName("Player_" + tile.getOwnerId());
        }
        
        return dto;
    }
}
