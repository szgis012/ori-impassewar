package com.war.game.battle.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 战斗记录 DTO
 */
@Data
public class BattleRecordDTO {
    private Long id;
    private Long attackerId;
    private String attackerName;
    private Long defenderId;
    private String defenderName;
    private Integer battleX;
    private Integer battleY;
    private Integer result;
    private String resultDesc;
    private Integer attackerLoss;
    private Integer defenderLoss;
    private Integer lootGold;
    private Integer lootWood;
    private Integer lootStone;
    private Integer lootFood;
    private Boolean isRead;
    private LocalDateTime battleTime;
}
