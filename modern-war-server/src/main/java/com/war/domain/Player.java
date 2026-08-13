package com.war.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家实体 - 现代化重制版
 * 对应原项目 Player.java
 */
@Data
@Entity
@Table(name = "player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 玩家编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    /** 用户编号 */
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    /** 玩家名称 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /** 玩家头像 */
    @Column(name = "head_img", length = 255)
    private String headImg;

    /** 军衔编号 */
    @Column(name = "honor_id")
    private Integer honorId;

    /** 军衔名称 */
    @Column(name = "honor_name", length = 50)
    private String honorName;

    /** 军团编号 */
    @Column(name = "guild_id")
    private Integer guildId;

    /** 国家 (参考 CountryConstant) */
    @Column(name = "country")
    private Integer country;

    /** 声望 */
    @Column(name = "renown")
    private Long renown;

    /** 进攻点数 */
    @Column(name = "attack_point", precision = 10, scale = 2)
    private Double attackPoint;

    /** 防御点数 */
    @Column(name = "defense_point", precision = 10, scale = 2)
    private Double defensePoint;

    /** 排名 */
    @Column(name = "rank")
    private Integer rank;

    /** 金钱 */
    @Column(name = "money")
    private Integer money;

    /** 礼金数量 */
    @Column(name = "gift_certificate")
    private Integer giftCertificate;

    /** 是否已领取每日登录奖励 (0.否 1.是) */
    @Column(name = "have_receive_daily_reward")
    private Integer haveReceiveDailyReward;

    /** 玩家状态 (参考 PlayerStateConstant) */
    @Column(name = "state")
    private Integer state;

    /** 登录次数 */
    @Column(name = "login_num")
    private Integer loginNum;

    /** 在线时间 (单位：分钟) */
    @Column(name = "online_time")
    private Integer onlineTime;

    /** 最后登陆时间 */
    @Column(name = "last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    /** 创建时间 */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /** 关联的城市信息 */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "player")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private City city;

    /** 军团名称 (冗余字段，方便查询) */
    @Column(name = "guild_name", length = 100)
    private String guildName;

    /** 密码 (加密存储) */
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    /** 邮箱 */
    @Column(name = "email", length = 100)
    private String email;

    /** 手机号 */
    @Column(name = "phone", length = 20)
    private String phone;

    /** IP 地址 */
    @Column(name = "last_login_ip", length = 50)
    private String lastLoginIp;

    /** 版本标识 */
    @Version
    private Long version;

    /** 更新时间 */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Date();
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = new Date();
        this.updateTime = new Date();
        if (this.renown == null) this.renown = 0L;
        if (this.attackPoint == null) this.attackPoint = 0.0;
        if (this.defensePoint == null) this.defensePoint = 0.0;
        if (this.money == null) this.money = 0;
        if (this.giftCertificate == null) this.giftCertificate = 0;
        if (this.haveReceiveDailyReward == null) this.haveReceiveDailyReward = 0;
        if (this.loginNum == null) this.loginNum = 0;
        if (this.onlineTime == null) this.onlineTime = 0;
        if (this.state == null) this.state = 1; // 默认正常状态
    }
}
