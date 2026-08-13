package com.war.game.city.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 科技研究实体
 */
@Entity
@Table(name = "city_technology")
@Data
@NoArgsConstructor
public class Technology {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long cityId;
    
    @Column(nullable = false, length = 50)
    private String type; // 科技类型：INFANTRY_ATTACK, ARCHER_DEFENSE等
    
    @Column(nullable = false)
    private Integer level = 0;
    
    @Column(nullable = false)
    private LocalDateTime researchingAt; // 开始研究时间
    
    @Column(nullable = true)
    private LocalDateTime finishedAt; // 完成时间
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (researchingAt == null) {
            researchingAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
