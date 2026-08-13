package com.war.game.battle.dto;

import lombok.Data;

/**
 * 训练部队请求 DTO
 */
@Data
public class TrainArmyRequest {
    private Long cityId;
    private Integer unitType;
    private Integer count;
}
