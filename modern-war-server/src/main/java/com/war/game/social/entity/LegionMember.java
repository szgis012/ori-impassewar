package com.war.game.social.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 军团成员实体类
 */
@Entity
@Table(name = "legion_member")
@Data
@NoArgsConstructor
public class LegionMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 军团 ID
     */
    @Column(nullable = false)
    private Long legionId;

    /**
     * 玩家 ID
     */
    @Column(nullable = false)
    private Long playerId;

    /**
     * 职位：1-军团长，2-副军团长，3-精英，4-普通成员
     */
    @Column(nullable = false)
    private Integer position = 4;

    /**
     * 贡献度
     */
    @Column(nullable = false)
    private Integer contribution = 0;

    /**
     * 加入时间
     */
    @Column(updatable = false)
    private LocalDateTime joinTime;

    /**
     * 最后在线时间
     */
    private LocalDateTime lastOnlineTime;

    @PrePersist
    protected void onCreate() {
        joinTime = LocalDateTime.now();
        lastOnlineTime = LocalDateTime.now();
    }
}
