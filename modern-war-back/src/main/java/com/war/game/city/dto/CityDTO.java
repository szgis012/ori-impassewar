package com.war.game.city.dto;

import lombok.Data;
import java.util.List;

/**
 * 城市信息 DTO
 */
@Data
public class CityDTO {
    private Long id;
    private String name;
    private Integer level;
    private Double posX;
    private Double posY;
    
    // 资源
    private Long gold;
    private Long wood;
    private Long stone;
    private Long food;
    
    // 资源产量 (每小时)
    private Integer goldProduction;
    private Integer woodProduction;
    private Integer stoneProduction;
    private Integer foodProduction;
    
    // 建筑列表
    private List<BuildingDTO> buildings;
    
    // 科技列表
    private List<TechnologyDTO> technologies;
}
