package com.war.game.city.entity;

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
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false)
    private Integer level = 1;
    
    @Column(nullable = false)
    private Double posX;
    
    @Column(nullable = false)
    private Double posY;
    
    // 资源字段
    @Column(nullable = false)
    private Long gold = 1000L;
    
    @Column(nullable = false)
    private Long wood = 1000L;
    
    @Column(nullable = false)
    private Long stone = 1000L;
    
    @Column(nullable = false)
    private Long food = 1000L;
    
    // 资源产量 (每小时)
    @Column(nullable = false)
    private Integer goldProduction = 100;
    
    @Column(nullable = false)
    private Integer woodProduction = 100;
    
    @Column(nullable = false)
    private Integer stoneProduction = 100;
    
    @Column(nullable = false)
    private Integer foodProduction = 100;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
