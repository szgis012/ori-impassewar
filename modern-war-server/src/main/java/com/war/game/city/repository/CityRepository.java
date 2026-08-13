package com.war.game.city.repository;

import com.war.game.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 城市数据访问层
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    
    /**
     * 根据玩家 ID 查找城市
     */
    List<City> findByPlayerId(Long playerId);
    
    /**
     * 查找玩家的主城
     */
    Optional<City> findFirstByPlayerIdOrderByCreatedAtAsc(Long playerId);
}
