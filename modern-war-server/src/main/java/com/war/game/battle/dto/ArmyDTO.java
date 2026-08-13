package com.war.game.battle.dto;

import lombok.Data;
import java.util.List;

/**
 * 部队 DTO
 */
@Data
public class ArmyDTO {
    private Long id;
    private Long playerId;
    private Long cityId;
    private Integer unitType;
    private String unitTypeName;
    private Integer count;
    private Integer level;
    private Integer attack;
    private Integer defense;
    private Integer health;
    private Integer marchState;
    private Integer targetX;
    private Integer targetY;
}
