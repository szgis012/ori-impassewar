package com.war.game.map.repository;

import com.war.game.map.entity.MapTile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapTileRepository extends JpaRepository<MapTile, Long> {
    
    /**
     * 按坐标查询地图格子
     */
    Optional<MapTile> findByXAndY(Integer x, Integer y);
    
    /**
     * 查询指定范围内的地图格子
     */
    List<MapTile> findByXBetweenAndYBetween(Integer xStart, Integer xEnd, Integer yStart, Integer yEnd);
    
    /**
     * 查询所有者 ID
     */
    List<MapTile> findByOwnerId(Long ownerId);
    
    /**
     * 查询有城市的格子
     */
    List<MapTile> findByHasCityTrue();
    
    /**
     * 查询特定地形类型的格子
     */
    List<MapTile> findByTerrainType(Integer terrainType);
}
