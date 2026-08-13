package com.war.game.battle.repository;

import com.war.game.battle.entity.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmyRepository extends JpaRepository<Army, Long> {
    
    /**
     * 按玩家 ID 查询部队列表
     */
    List<Army> findByPlayerId(Long playerId);
    
    /**
     * 按城市 ID 查询部队列表
     */
    List<Army> findByCityId(Long cityId);
    
    /**
     * 查询玩家在某城市的特定类型部队
     */
    Army findByPlayerIdAndCityIdAndUnitType(Long playerId, Long cityId, Integer unitType);
    
    /**
     * 查询行军中的部队
     */
    List<Army> findByMarchStateIn(List<Integer> marchStates);
}
