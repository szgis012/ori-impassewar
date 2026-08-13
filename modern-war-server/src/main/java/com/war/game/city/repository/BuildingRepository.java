package com.war.game.city.repository;

import com.war.game.city.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 建筑数据访问层
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    
    /**
     * 根据城市 ID 查找所有建筑
     */
    List<Building> findByCityId(Long cityId);
    
    /**
     * 根据城市 ID 和建筑类型查找
     */
    Building findByCityIdAndType(Long cityId, String type);
}
