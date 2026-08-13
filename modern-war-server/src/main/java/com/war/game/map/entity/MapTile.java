package com.war.game.map.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 地图格子实体类
 * 表示世界地图上的一个格子
 */
@Entity
@Table(name = "map_tile")
@Data
@NoArgsConstructor
public class MapTile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * X 坐标
     */
    @Column(nullable = false, unique = true)
    private Integer x;

    /**
     * Y 坐标
     */
    @Column(nullable = false, unique = true)
    private Integer y;

    /**
     * 地形类型：1-平原，2-森林，3-山地，4-河流，5-沙漠，6-沼泽
     */
    @Column(nullable = false)
    private Integer terrainType;

    /**
     * 地形名称
     */
    private String terrainName;

    /**
     * 资源类型：0-无，1-金矿，2-伐木场，3-采石场，4-农田
     */
    @Column(nullable = false)
    private Integer resourceType = 0;

    /**
     * 资源等级 (1-10 级)
     */
    private Integer resourceLevel = 0;

    /**
     * 占领者玩家 ID
     */
    private Long ownerId;

    /**
     * 占领时间
     */
    private LocalDateTime occupyTime;

    /**
     * 是否有城市
     */
    @Column(nullable = false)
    private Boolean hasCity = false;

    /**
     * 城市 ID
     */
    private Long cityId;

    /**
     * 通行难度：1-容易，2-普通，3-困难，4-无法通行
     */
    @Column(nullable = false)
    private Integer passDifficulty = 1;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
    }
}
