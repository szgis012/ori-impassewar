package com.war.game.battle.service;

import com.war.domain.Player;
import com.war.game.battle.dto.*;
import com.war.game.battle.entity.Army;
import com.war.game.battle.entity.BattleRecord;
import com.war.game.battle.repository.ArmyRepository;
import com.war.game.battle.repository.BattleRecordRepository;
import com.war.game.city.entity.City;
import com.war.game.city.repository.CityRepository;
import com.war.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 战斗系统服务类
 * 处理部队训练、行军、战斗等逻辑
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BattleService {

    private final ArmyRepository armyRepository;
    private final BattleRecordRepository battleRecordRepository;
    private final CityRepository cityRepository;
    private final PlayerRepository playerRepository;

    /**
     * 部队类型名称映射
     */
    private static final Map<Integer, String> UNIT_TYPE_NAMES = new HashMap<>();
    static {
        UNIT_TYPE_NAMES.put(1, "步兵");
        UNIT_TYPE_NAMES.put(2, "骑兵");
        UNIT_TYPE_NAMES.put(3, "弓兵");
        UNIT_TYPE_NAMES.put(4, "攻城车");
    }

    /**
     * 部队类型基础属性
     */
    private static final Map<Integer, Map<String, Integer>> UNIT_BASE_STATS = new HashMap<>();
    static {
        // 攻击力，防御力，生命值，训练成本 (金币，木材，石材，粮食)
        Map<String, Integer> infantry = new HashMap<>();
        infantry.put("attack", 10);
        infantry.put("defense", 15);
        infantry.put("health", 100);
        infantry.put("gold", 50);
        infantry.put("wood", 0);
        infantry.put("stone", 0);
        infantry.put("food", 30);
        UNIT_BASE_STATS.put(1, infantry);

        Map<String, Integer> cavalry = new HashMap<>();
        cavalry.put("attack", 20);
        cavalry.put("defense", 10);
        cavalry.put("health", 150);
        cavalry.put("gold", 100);
        cavalry.put("wood", 50);
        cavalry.put("stone", 0);
        cavalry.put("food", 60);
        UNIT_BASE_STATS.put(2, cavalry);

        Map<String, Integer> archer = new HashMap<>();
        archer.put("attack", 25);
        archer.put("defense", 5);
        archer.put("health", 80);
        archer.put("gold", 80);
        archer.put("wood", 60);
        archer.put("stone", 0);
        archer.put("food", 40);
        UNIT_BASE_STATS.put(3, archer);

        Map<String, Integer> siege = new HashMap<>();
        siege.put("attack", 50);
        siege.put("defense", 20);
        siege.put("health", 200);
        siege.put("gold", 200);
        siege.put("wood", 150);
        siege.put("stone", 100);
        siege.put("food", 80);
        UNIT_BASE_STATS.put(4, siege);
    }

    /**
     * 训练部队
     */
    @Transactional
    public ArmyDTO trainArmy(Long playerId, TrainArmyRequest request) {
        log.info("玩家 {} 在城市 {} 训练 {} 个部队类型 {}", 
                playerId, request.getCityId(), request.getCount(), request.getUnitType());

        // 获取城市
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("城市不存在"));

        // 验证城市属于该玩家
        if (!city.getPlayerId().equals(playerId)) {
            throw new RuntimeException("无权在该城市训练部队");
        }

        // 检查资源是否充足
        Map<String, Integer> costs = calculateTrainingCost(request.getUnitType(), request.getCount());
        if (city.getGold() < costs.get("gold") ||
            city.getWood() < costs.get("wood") ||
            city.getStone() < costs.get("stone") ||
            city.getFood() < costs.get("food")) {
            throw new RuntimeException("资源不足，无法训练部队");
        }

        // 扣除资源
        city.setGold(city.getGold() - costs.get("gold"));
        city.setWood(city.getWood() - costs.get("wood"));
        city.setStone(city.getStone() - costs.get("stone"));
        city.setFood(city.getFood() - costs.get("food"));
        cityRepository.save(city);

        // 查找或创建部队记录
        Army army = armyRepository.findByPlayerIdAndCityIdAndUnitType(
                playerId, request.getCityId(), request.getUnitType());

        if (army == null) {
            army = new Army();
            army.setPlayerId(playerId);
            army.setCityId(request.getCityId());
            army.setUnitType(request.getUnitType());
            
            // 设置基础属性
            Map<String, Integer> stats = UNIT_BASE_STATS.get(request.getUnitType());
            army.setAttack(stats.get("attack"));
            army.setDefense(stats.get("defense"));
            army.setHealth(stats.get("health"));
            army.setCount(0);
        }

        army.setCount(army.getCount() + request.getCount());
        army = armyRepository.save(army);

        return convertToDTO(army);
    }

    /**
     * 计算训练成本
     */
    private Map<String, Integer> calculateTrainingCost(Integer unitType, Integer count) {
        Map<String, Integer> baseStats = UNIT_BASE_STATS.get(unitType);
        if (baseStats == null) {
            throw new RuntimeException("未知的部队类型");
        }

        Map<String, Integer> costs = new HashMap<>();
        costs.put("gold", baseStats.get("gold") * count);
        costs.put("wood", baseStats.get("wood") * count);
        costs.put("stone", baseStats.get("stone") * count);
        costs.put("food", baseStats.get("food") * count);
        return costs;
    }

    /**
     * 获取玩家部队列表
     */
    public List<ArmyDTO> getPlayerArmies(Long playerId) {
        List<Army> armies = armyRepository.findByPlayerId(playerId);
        return armies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * 获取城市部队列表
     */
    public List<ArmyDTO> getCityArmies(Long cityId) {
        List<Army> armies = armyRepository.findByCityId(cityId);
        return armies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * 部队行军
     */
    @Transactional
    public ArmyDTO march(Long playerId, MarchRequest request) {
        log.info("玩家 {} 派遣部队 {} 前往坐标 ({},{})", 
                playerId, request.getArmyId(), request.getTargetX(), request.getTargetY());

        Army army = armyRepository.findById(request.getArmyId())
                .orElseThrow(() -> new RuntimeException("部队不存在"));

        // 验证部队属于该玩家
        if (!army.getPlayerId().equals(playerId)) {
            throw new RuntimeException("无权指挥该部队");
        }

        // 设置行军目标
        army.setTargetX(request.getTargetX());
        army.setTargetY(request.getTargetY());
        army.setMarchState(request.getActionType());
        army.setDepartureTime(LocalDateTime.now());
        
        // 简单计算到达时间 (假设每秒移动 1 格)
        int distance = calculateDistance(
                army.getCity().getX(), army.getCity().getY(),
                request.getTargetX(), request.getTargetY());
        army.setArrivalTime(LocalDateTime.now().plusSeconds(distance));

        army = armyRepository.save(army);
        return convertToDTO(army);
    }

    /**
     * 计算两点间距离
     */
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    /**
     * 执行战斗 (简化版)
     */
    @Transactional
    public BattleRecordDTO executeBattle(Long attackerId, Long defenderId, 
                                         Integer battleX, Integer battleY) {
        log.info("战斗爆发：玩家 {} 攻击玩家 {} 在 ({},{})", 
                attackerId, defenderId, battleX, battleY);

        // 获取双方部队
        List<Army> attackerArmies = armyRepository.findByPlayerId(attackerId);
        List<Army> defenderArmies = armyRepository.findByPlayerId(defenderId);

        // 计算双方战力
        int attackerPower = calculateTotalPower(attackerArmies);
        int defenderPower = calculateTotalPower(defenderArmies);

        // 判定胜负
        int result;
        int attackerLoss = 0;
        int defenderLoss = 0;

        if (attackerPower > defenderPower) {
            result = 0; // 攻击方胜利
            defenderLoss = (int) (defenderPower * 0.3); // 防守方损失 30%
            attackerLoss = (int) (attackerPower * 0.1); // 攻击方损失 10%
        } else if (defenderPower > attackerPower) {
            result = 1; // 防守方胜利
            attackerLoss = (int) (attackerPower * 0.3);
            defenderLoss = (int) (defenderPower * 0.1);
        } else {
            result = 2; // 平局
            attackerLoss = (int) (attackerPower * 0.2);
            defenderLoss = (int) (defenderPower * 0.2);
        }

        // 创建战斗记录
        BattleRecord record = new BattleRecord();
        record.setAttackerId(attackerId);
        record.setDefenderId(defenderId);
        record.setBattleX(battleX);
        record.setBattleY(battleY);
        record.setResult(result);
        record.setAttackerLoss(attackerLoss);
        record.setDefenderLoss(defenderLoss);
        
        // TODO: 实现资源掠夺逻辑
        
        record = battleRecordRepository.save(record);

        // 更新部队数量
        updateArmiesAfterBattle(attackerArmies, attackerLoss);
        updateArmiesAfterBattle(defenderArmies, defenderLoss);

        return convertToDTO(record);
    }

    /**
     * 计算总战力
     */
    private int calculateTotalPower(List<Army> armies) {
        return armies.stream()
                .mapToInt(army -> army.getCount() * (army.getAttack() + army.getDefense()))
                .sum();
    }

    /**
     * 战斗后更新部队数量
     */
    private void updateArmiesAfterBattle(List<Army> armies, int totalLoss) {
        if (armies.isEmpty() || totalLoss <= 0) {
            return;
        }

        int remainingLoss = totalLoss;
        for (Army army : armies) {
            if (remainingLoss <= 0) break;
            
            int loss = Math.min(army.getCount(), remainingLoss);
            army.setCount(army.getCount() - loss);
            remainingLoss -= loss;
        }
        armyRepository.saveAll(armies);
    }

    /**
     * 获取战斗记录
     */
    public List<BattleRecordDTO> getBattleRecords(Long playerId, boolean asAttacker) {
        List<BattleRecord> records;
        if (asAttacker) {
            records = battleRecordRepository.findByAttackerIdOrderByBattleTimeDesc(playerId);
        } else {
            records = battleRecordRepository.findByDefenderIdOrderByBattleTimeDesc(playerId);
        }
        return records.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * 标记战斗记录为已读
     */
    @Transactional
    public void markBattleAsRead(Long recordId, Long playerId) {
        BattleRecord record = battleRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("战斗记录不存在"));
        
        if (!record.getDefenderId().equals(playerId) && !record.getAttackerId().equals(playerId)) {
            throw new RuntimeException("无权查看该战斗记录");
        }
        
        record.setIsRead(true);
        battleRecordRepository.save(record);
    }

    /**
     * Army 转 DTO
     */
    private ArmyDTO convertToDTO(Army army) {
        ArmyDTO dto = new ArmyDTO();
        dto.setId(army.getId());
        dto.setPlayerId(army.getPlayerId());
        dto.setCityId(army.getCityId());
        dto.setUnitType(army.getUnitType());
        dto.setUnitTypeName(UNIT_TYPE_NAMES.get(army.getUnitType()));
        dto.setCount(army.getCount());
        dto.setLevel(army.getLevel());
        dto.setAttack(army.getAttack());
        dto.setDefense(army.getDefense());
        dto.setHealth(army.getHealth());
        dto.setMarchState(army.getMarchState());
        dto.setTargetX(army.getTargetX());
        dto.setTargetY(army.getTargetY());
        return dto;
    }

    /**
     * BattleRecord 转 DTO
     */
    private BattleRecordDTO convertToDTO(BattleRecord record) {
        BattleRecordDTO dto = new BattleRecordDTO();
        dto.setId(record.getId());
        dto.setAttackerId(record.getAttackerId());
        dto.setDefenderId(record.getDefenderId());
        dto.setBattleX(record.getBattleX());
        dto.setBattleY(record.getBattleY());
        dto.setResult(record.getResult());
        dto.setResultDesc(getResultDescription(record.getResult()));
        dto.setAttackerLoss(record.getAttackerLoss());
        dto.setDefenderLoss(record.getDefenderLoss());
        dto.setLootGold(record.getLootGold());
        dto.setLootWood(record.getLootWood());
        dto.setLootStone(record.getLootStone());
        dto.setLootFood(record.getLootFood());
        dto.setIsRead(record.getIsRead());
        dto.setBattleTime(record.getBattleTime());
        return dto;
    }

    private String getResultDescription(Integer result) {
        switch (result) {
            case 0: return "攻击方胜利";
            case 1: return "防守方胜利";
            case 2: return "平局";
            default: return "未知";
        }
    }
}
