package com.war.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市实体 - 现代化重制版
 * 对应原项目 City.java
 */
@Data
@Entity
@Table(name = "city")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 城市编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    /** 玩家编号 */
    @Column(name = "player_id", nullable = false)
    private Integer playerId;

    /** 关联的玩家 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Player player;

    /** 城市名称 */
    @Column(name = "city_name", length = 50, nullable = false)
    private String cityName;

    /** 城市等级 */
    @Column(name = "city_level")
    private Integer cityLevel;

    /** 城市类型 (1.主城 2.分城 3.要塞) */
    @Column(name = "city_type")
    private Integer cityType;

    /** 地图 X 坐标 */
    @Column(name = "map_x")
    private Integer mapX;

    /** 地图 Y 坐标 */
    @Column(name = "map_y")
    private Integer mapY;

    /** 城市状态 (参考 CityStateConstant) */
    @Column(name = "state")
    private Integer state;

    /** 石油资源量 */
    @Column(name = "oil")
    private Long oil;

    /** 钢铁资源量 */
    @Column(name = "steel")
    private Long steel;

    /** 铝材资源量 */
    @Column(name = "aluminum")
    private Long aluminum;

    /** 石油产量/小时 */
    @Column(name = "oil_production")
    private Integer oilProduction;

    /** 钢铁产量/小时 */
    @Column(name = "steel_production")
    private Integer steelProduction;

    /** 铝材产量/小时 */
    @Column(name = "aluminum_production")
    private Integer aluminumProduction;

    /** 仓库容量上限 */
    @Column(name = "warehouse_capacity")
    private Integer warehouseCapacity;

    /** 人口上限 */
    @Column(name = "population_limit")
    private Integer populationLimit;

    /** 当前人口 */
    @Column(name = "population_current")
    private Integer populationCurrent;

    /** 城墙耐久度 */
    @Column(name = "wall_durability")
    private Integer wallDurability;

    /** 城墙等级 */
    @Column(name = "wall_level")
    private Integer wallLevel;

    /** 指挥中心等级 */
    @Column(name = "command_center_level")
    private Integer commandCenterLevel;

    /** 新手保护结束时间 */
    @Column(name = "newbie_protect_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date newbieProtectEndTime;

    /** 创建时间 */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /** 更新时间 */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    protected void onCreate() {
        this.createTime = new Date();
        this.updateTime = new Date();
        if (this.cityLevel == null) this.cityLevel = 1;
        if (this.cityType == null) this.cityType = 1; // 默认主城
        if (this.oil == null) this.oil = 1000L;
        if (this.steel == null) this.steel = 1000L;
        if (this.aluminum == null) this.aluminum = 1000L;
        if (this.oilProduction == null) this.oilProduction = 100;
        if (this.steelProduction == null) this.steelProduction = 100;
        if (this.aluminumProduction == null) this.aluminumProduction = 100;
        if (this.warehouseCapacity == null) this.warehouseCapacity = 10000;
        if (this.populationLimit == null) this.populationLimit = 1000;
        if (this.populationCurrent == null) this.populationCurrent = 0;
        if (this.wallDurability == null) this.wallDurability = 1000;
        if (this.state == null) this.state = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Date();
    }
}
