package com.war.game.city.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 建筑信息 DTO
 */
@Data
public class BuildingDTO {
    private Long id;
    private String type;
    private Integer level;
    private Integer posX;
    private Integer posY;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
