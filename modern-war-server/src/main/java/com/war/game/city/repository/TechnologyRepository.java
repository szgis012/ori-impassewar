package com.war.game.city.repository;

import com.war.game.city.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 科技数据访问层
 */
@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    
    /**
     * 根据城市 ID 查找所有科技
     */
    List<Technology> findByCityId(Long cityId);
    
    /**
     * 根据城市 ID 和科技类型查找
     */
    Optional<Technology> findByCityIdAndType(Long cityId, String type);
}
