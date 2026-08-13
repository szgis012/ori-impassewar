package com.war.game.city.entity;

import com.war.domain.Player;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 玩家城市实体
 */
@Entity
@Table(name = "player_city")
@Data
@NoArgsConstructor
public class City {
    
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
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false)
    private Integer level = 1;
    
    @Column(nullable = false)
    private Double posX;
    
    @Column(nullable = false)
    private Double posY;
    
    /**
     * 城市坐标 X（整数版本，用于战斗计算）
     */
    @Transient
    public Integer getX() {
        return posX != null ? posX.intValue() : 0;
    }
    
    /**
     * 城市坐标 Y（整数版本，用于战斗计算）
     */
    @Transient
    public Integer getY() {
        return posY != null ? posY.intValue() : 0;
    }
    
    // 资源字段
    @Column(nullable = false)
    private Long gold = 1000L;
    
    @Column(nullable = false)
    private Long wood = 1000L;
    
    @Column(nullable = false)
    private Long stone = 1000L;
    
    @Column(nullable = false)
    private Long food = 1000L;
    
    // 资源上限
    @Column(nullable = false)
    private Long goldCap = 10000L;
    
    @Column(nullable = false)
    private Long woodCap = 10000L;
    
    @Column(nullable = false)
    private Long stoneCap = 10000L;
    
    @Column(nullable = false)
    private Long foodCap = 10000L;
    
    // 资源产量 (每小时)
    @Column(nullable = false)
    private Integer goldProduction = 100;
    
    @Column(nullable = false)
    private Integer woodProduction = 100;
    
    @Column(nullable = false)
    private Integer stoneProduction = 100;
    
    @Column(nullable = false)
    private Integer foodProduction = 100;
    
    // 最后资源收集时间
    @Column(nullable = false)
    private LocalDateTime lastResourceUpdate;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        lastResourceUpdate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * 更新资源产量 (基于时间自动计算)
     */
    public void updateResources() {
        if (lastResourceUpdate == null) {
            lastResourceUpdate = LocalDateTime.now();
            return;
        }
        
        LocalDateTime now = LocalDateTime.now();
        long secondsPassed = java.time.Duration.between(lastResourceUpdate, now).getSeconds();
        
        if (secondsPassed > 0) {
            // 计算增加的资源 (按秒计算产量)
            long goldGain = (goldProduction * secondsPassed) / 3600;
            long woodGain = (woodProduction * secondsPassed) / 3600;
            long stoneGain = (stoneProduction * secondsPassed) / 3600;
            long foodGain = (foodProduction * secondsPassed) / 3600;
            
            // 累加资源，不超过上限
            this.gold = Math.min(goldCap, this.gold + goldGain);
            this.wood = Math.min(woodCap, this.wood + woodGain);
            this.stone = Math.min(stoneCap, this.stone + stoneGain);
            this.food = Math.min(foodCap, this.food + foodGain);
            
            lastResourceUpdate = now;
        }
    }
}
