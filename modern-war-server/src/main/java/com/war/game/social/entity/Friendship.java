package com.war.game.social.entity;

import com.war.domain.Player;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 好友关系实体类
 */
@Entity
@Table(name = "friendship")
@Data
@NoArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Player user;

    /**
     * 好友 ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", nullable = false)
    private Player friend;

    /**
     * 好友状态：0-待审核，1-已同意，2-已拒绝，3-已拉黑
     */
    @Column(nullable = false)
    private Integer status = 0;

    /**
     * 备注名
     */
    private String remark;

    /**
     * 分组 ID
     */
    private Integer groupId;

    /**
     * 亲密度
     */
    @Column(nullable = false)
    private Integer intimacy = 0;

    /**
     * 添加时间
     */
    @Column(updatable = false)
    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
    }
}
