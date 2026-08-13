package com.war.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家信息 DTO - 用于返回给前端
 */
@Data
public class PlayerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 玩家编号 */
    private Integer playerId;

    /** 用户名 */
    private String userName;

    /** 玩家名称 */
    private String name;

    /** 玩家头像 */
    private String headImg;

    /** 军衔编号 */
    private Integer honorId;

    /** 军衔名称 */
    private String honorName;

    /** 军团编号 */
    private Integer guildId;

    /** 国家 */
    private Integer country;

    /** 声望 */
    private Long renown;

    /** 进攻点数 */
    private Double attackPoint;

    /** 防御点数 */
    private Double defensePoint;

    /** 排名 */
    private Integer rank;

    /** 金钱 */
    private Integer money;

    /** 礼金数量 */
    private Integer giftCertificate;

    /** 是否已领取每日登录奖励 */
    private Integer haveReceiveDailyReward;

    /** 玩家状态 */
    private Integer state;

    /** 登录次数 */
    private Integer loginNum;

    /** 在线时间 (分钟) */
    private Integer onlineTime;

    /** 最后登陆时间 */
    private Date lastLoginTime;

    /** 创建时间 */
    private Date createTime;

    /** 军团名称 */
    private String guildName;

    /** 城市信息 */
    private CityDTO city;

    /**
     * 内部城市 DTO
     */
    @Data
    public static class CityDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer cityId;
        private String cityName;
        private Integer cityLevel;
        private Integer cityType;
        private Integer mapX;
        private Integer mapY;
        private Long oil;
        private Long steel;
        private Long aluminum;
        private Integer oilProduction;
        private Integer steelProduction;
        private Integer aluminumProduction;
        private Integer warehouseCapacity;
        private Integer populationLimit;
        private Integer populationCurrent;
        private Integer wallDurability;
        private Integer wallLevel;
        private Integer commandCenterLevel;
    }
}
