package com.war.game.map.dto;

import lombok.Data;

/**
 * 地图格子 DTO
 */
@Data
public class MapTileDTO {
    private Long id;
    private Integer x;
    private Integer y;
    private Integer terrainType;
    private String terrainName;
    private Integer resourceType;
    private String resourceName;
    private Integer resourceLevel;
    private Long ownerId;
    private String ownerName;
    private Boolean hasCity;
    private Long cityId;
    private Integer passDifficulty;
}
