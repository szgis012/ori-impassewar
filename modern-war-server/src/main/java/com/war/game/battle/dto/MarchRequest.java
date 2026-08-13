package com.war.game.battle.dto;

import lombok.Data;

/**
 * 行军请求 DTO
 */
@Data
public class MarchRequest {
    private Long armyId;
    private Integer targetX;
    private Integer targetY;
    private Integer actionType; // 1-进攻，2-驻守，3-支援
}
