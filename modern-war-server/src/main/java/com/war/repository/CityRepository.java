package com.war.repository;

import com.war.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 城市数据访问层 - 现代化重制版
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    /**
     * 根据玩家 ID 查找城市
     */
    Optional<City> findByPlayerId(Integer playerId);

    /**
     * 根据地图坐标查找城市
     */
    Optional<City> findByMapXAndMapY(Integer mapX, Integer mapY);

    /**
     * 查找玩家的所有城市
     */
    List<City> findByPlayerIdOrderByCityType(Integer playerId);

    /**
     * 统计玩家的城市数量
     */
    long countByPlayerId(Integer playerId);

    /**
     * 查找指定坐标范围内的城市
     */
    List<City> findByMapXBetweenAndMapYBetween(Integer minX, Integer maxX, Integer minY, Integer maxY);
}
