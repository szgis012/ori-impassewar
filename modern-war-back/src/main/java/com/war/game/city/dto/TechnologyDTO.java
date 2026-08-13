package com.war.game.city.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 科技信息 DTO
 */
@Data
public class TechnologyDTO {
    private Long id;
    private String type;
    private Integer level;
    private LocalDateTime researchingAt;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
