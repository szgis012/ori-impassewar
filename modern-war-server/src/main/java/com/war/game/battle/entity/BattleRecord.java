package com.war.game.battle.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 战斗记录实体类
 * 存储每次战斗的详细信息
 */
@Entity
@Table(name = "battle_record")
@Data
@NoArgsConstructor
public class BattleRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 攻击方玩家 ID
     */
    @Column(nullable = false)
    private Long attackerId;

    /**
     * 防守方玩家 ID
     */
    @Column(nullable = false)
    private Long defenderId;

    /**
     * 攻击方城市 ID
     */
    private Long attackerCityId;

    /**
     * 防守方城市 ID
     */
    private Long defenderCityId;

    /**
     * 战斗地点 X 坐标
     */
    @Column(nullable = false)
    private Integer battleX;

    /**
     * 战斗地点 Y 坐标
     */
    @Column(nullable = false)
    private Integer battleY;

    /**
     * 战斗结果：0-攻击方胜利，1-防守方胜利，2-平局
     */
    @Column(nullable = false)
    private Integer result;

    /**
     * 攻击方损失兵力
     */
    @Column(nullable = false)
    private Integer attackerLoss = 0;

    /**
     * 防守方损失兵力
     */
    @Column(nullable = false)
    private Integer defenderLoss = 0;

    /**
     * 掠夺资源：金币
     */
    private Integer lootGold = 0;

    /**
     * 掠夺资源：木材
     */
    private Integer lootWood = 0;

    /**
     * 掠夺资源：石材
     */
    private Integer lootStone = 0;

    /**
     * 掠夺资源：粮食
     */
    private Integer lootFood = 0;

    /**
     * 战斗报告详情 (JSON 格式)
     */
    @Column(columnDefinition = "TEXT")
    private String reportDetail;

    /**
     * 是否已读
     */
    @Column(nullable = false)
    private Boolean isRead = false;

    /**
     * 战斗时间
     */
    @Column(updatable = false)
    private LocalDateTime battleTime;

    @PrePersist
    protected void onCreate() {
        battleTime = LocalDateTime.now();
    }
}
