package com.war.game.battle.controller;

import com.war.dto.ApiResponse;
import com.war.game.battle.dto.*;
import com.war.game.battle.service.BattleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 战斗系统控制器
 */
@RestController
@RequestMapping("/api/battle")
@Slf4j
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    /**
     * 训练部队
     */
    @PostMapping("/train")
    public ApiResponse<ArmyDTO> trainArmy(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TrainArmyRequest request) {
        log.info("训练部队请求：{}", request);
        
        // 从用户名获取玩家 ID (实际应该从 token 或 session 获取)
        Long playerId = getCurrentPlayerId(userDetails);
        
        ArmyDTO result = battleService.trainArmy(playerId, request);
        return ApiResponse.success(result);
    }

    /**
     * 获取玩家部队列表
     */
    @GetMapping("/armies")
    public ApiResponse<List<ArmyDTO>> getPlayerArmies(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long playerId = getCurrentPlayerId(userDetails);
        List<ArmyDTO> armies = battleService.getPlayerArmies(playerId);
        return ApiResponse.success(armies);
    }

    /**
     * 获取城市部队列表
     */
    @GetMapping("/city/{cityId}/armies")
    public ApiResponse<List<ArmyDTO>> getCityArmies(@PathVariable Long cityId) {
        List<ArmyDTO> armies = battleService.getCityArmies(cityId);
        return ApiResponse.success(armies);
    }

    /**
     * 部队行军
     */
    @PostMapping("/march")
    public ApiResponse<ArmyDTO> march(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MarchRequest request) {
        log.info("行军请求：{}", request);
        
        Long playerId = getCurrentPlayerId(userDetails);
        ArmyDTO result = battleService.march(playerId, request);
        return ApiResponse.success(result);
    }

    /**
     * 执行战斗
     */
    @PostMapping("/fight")
    public ApiResponse<BattleRecordDTO> executeBattle(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long defenderId,
            @RequestParam Integer x,
            @RequestParam Integer y) {
        
        Long attackerId = getCurrentPlayerId(userDetails);
        BattleRecordDTO result = battleService.executeBattle(attackerId, defenderId, x, y);
        return ApiResponse.success(result);
    }

    /**
     * 获取战斗记录
     */
    @GetMapping("/records")
    public ApiResponse<List<BattleRecordDTO>> getBattleRecords(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "false") Boolean asAttacker) {
        
        Long playerId = getCurrentPlayerId(userDetails);
        List<BattleRecordDTO> records = battleService.getBattleRecords(playerId, asAttacker);
        return ApiResponse.success(records);
    }

    /**
     * 标记战斗记录为已读
     */
    @PostMapping("/records/{recordId}/read")
    public ApiResponse<Void> markBattleAsRead(
            @PathVariable Long recordId,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        Long playerId = getCurrentPlayerId(userDetails);
        battleService.markBattleAsRead(recordId, playerId);
        return ApiResponse.success(null);
    }

    /**
     * 获取当前玩家 ID (简化实现)
     * TODO: 实际项目中应该从 JWT token 中解析玩家信息
     */
    private Long getCurrentPlayerId(UserDetails userDetails) {
        // 这里简化处理，实际应该从数据库查询
        // 假设 username 格式为 "player_{id}"
        String username = userDetails.getUsername();
        if (username.startsWith("player_")) {
            return Long.parseLong(username.substring(7));
        }
        // 默认返回 1
        return 1L;
    }
}
