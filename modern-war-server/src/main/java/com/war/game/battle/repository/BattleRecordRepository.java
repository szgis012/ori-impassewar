package com.war.game.battle.repository;

import com.war.game.battle.entity.BattleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleRecordRepository extends JpaRepository<BattleRecord, Long> {
    
    /**
     * 查询玩家的战斗记录 (作为攻击方)
     */
    List<BattleRecord> findByAttackerIdOrderByBattleTimeDesc(Long attackerId);
    
    /**
     * 查询玩家的战斗记录 (作为防守方)
     */
    List<BattleRecord> findByDefenderIdOrderByBattleTimeDesc(Long defenderId);
    
    /**
     * 查询未读的战斗记录
     */
    List<BattleRecord> findByDefenderIdAndIsReadFalse(Long defenderId);
}
