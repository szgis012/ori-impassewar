package com.war.game.social.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 军团实体类
 */
@Entity
@Table(name = "legion")
@Data
@NoArgsConstructor
public class Legion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 军团名称
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * 军团宣言
     */
    @Column(length = 500)
    private String declaration;

    /**
     * 军团长 ID
     */
    @Column(nullable = false)
    private Long leaderId;

    /**
     * 成员数量
     */
    @Column(nullable = false)
    private Integer memberCount = 1;

    /**
     * 最大成员数
     */
    @Column(nullable = false)
    private Integer maxMembers = 50;

    /**
     * 军团等级
     */
    @Column(nullable = false)
    private Integer level = 1;

    /**
     * 军团经验
     */
    @Column(nullable = false)
    private Integer experience = 0;

    /**
     * 军团资金
     */
    @Column(nullable = false)
    private Integer funds = 0;

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
