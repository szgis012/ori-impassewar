package com.war.game.city.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 城市建筑实体
 */
@Entity
@Table(name = "city_building")
@Data
@NoArgsConstructor
public class Building {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long cityId;
    
    @Column(nullable = false, length = 50)
    private String type; // 建筑类型：TOWN_HALL, BARRACKS, FARM, MINE等
    
    @Column(nullable = false)
    private Integer level = 1;
    
    @Column(nullable = false)
    private Integer posX;
    
    @Column(nullable = false)
    private Integer posY;
    
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
