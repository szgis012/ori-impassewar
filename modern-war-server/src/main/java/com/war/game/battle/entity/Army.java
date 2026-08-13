package com.war.game.battle.entity;

import com.war.domain.Player;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 部队实体类
 * 用于存储玩家训练的部队信息
 */
@Entity
@Table(name = "army")
@Data
@NoArgsConstructor
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long playerId;

    /**
     * 玩家关联对象（懒加载）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @Column(nullable = false)
    private Long cityId;

    /**
     * 城市关联对象（懒加载）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    private com.war.game.city.entity.City city;

    /**
     * 部队类型：1-步兵，2-骑兵，3-弓兵，4-攻城车
     */
    @Column(nullable = false)
    private Integer unitType;

    /**
     * 部队数量
     */
    @Column(nullable = false)
    private Integer count = 0;

    /**
     * 部队等级
     */
    @Column(nullable = false)
    private Integer level = 1;

    /**
     * 攻击力
     */
    @Column(nullable = false)
    private Integer attack = 10;

    /**
     * 防御力
     */
    @Column(nullable = false)
    private Integer defense = 5;

    /**
     * 生命值
     */
    @Column(nullable = false)
    private Integer health = 100;

    /**
     * 行军状态：0-驻守，1-行军中，2-战斗中，3-返回中
     */
    @Column(nullable = false)
    private Integer marchState = 0;

    /**
     * 目标坐标 X
     */
    private Integer targetX;

    /**
     * 目标坐标 Y
     */
    private Integer targetY;

    /**
     * 出发时间
     */
    private LocalDateTime departureTime;

    /**
     * 预计到达时间
     */
    private LocalDateTime arrivalTime;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}
