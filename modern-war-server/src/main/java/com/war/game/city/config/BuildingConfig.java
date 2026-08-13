package com.war.game.city.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 建筑配置数据
 * 定义每个建筑的升级成本、前置条件和资源产量加成
 */
@Data
@NoArgsConstructor
public class BuildingConfig {
    
    // 建筑类型常量
    public static final String TOWN_HALL = "TOWN_HALL";
    public static final String BARRACKS = "BARRACKS";
    public static final String FARM = "FARM";
    public static final String GOLD_MINE = "GOLD_MINE";
    public static final String LUMBER_MILL = "LUMBER_MILL";
    public static final String QUARRY = "QUARRY";
    public static final String IRON_MINE = "IRON_MINE";
    public static final String STABLE = "STABLE";
    public static final String WORKSHOP = "WORKSHOP";
    public static final String ACADEMY = "ACADEMY";
    public static final String WALL = "WALL";
    public static final String WAREHOUSE = "WAREHOUSE";
    public static final String HOSPITAL = "HOSPITAL";
    
    // 所有建筑配置缓存
    private static final Map<String, BuildingConfig> CONFIG_CACHE;
    
    static {
        CONFIG_CACHE = Arrays.asList(
            // 市政厅 - 核心建筑
            new BuildingConfig(TOWN_HALL, "市政厅", 
                ResourceCost.empty(), // 市政厅升级不需要前置建筑
                0, 0, 0, 0, // 不直接产资源
                null), // 无前置条件
            
            // 农场 - 粮食生产
            new BuildingConfig(FARM, "农场",
                ResourceCost.of(200, 100, 50, 50),
                0, 0, 0, 100, // 每小时 +100 粮食
                null),
            
            // 金矿 - 金币生产
            new BuildingConfig(GOLD_MINE, "金矿",
                ResourceCost.of(300, 200, 100, 50),
                100, 0, 0, 0,
                List.of(new Prerequisite(FARM, 2))), // 需要农场 2 级
            
            // 伐木场 - 木材生产
            new BuildingConfig(LUMBER_MILL, "伐木场",
                ResourceCost.of(150, 0, 100, 50),
                0, 100, 0, 0,
                null),
            
            // 采石场 - 石材生产
            new BuildingConfig(QUARRY, "采石场",
                ResourceCost.of(200, 150, 0, 50),
                0, 0, 100, 0,
                List.of(new Prerequisite(LUMBER_MILL, 2))),
            
            // 铁矿 - 铁资源生产 (高级)
            new BuildingConfig(IRON_MINE, "铁矿",
                ResourceCost.of(500, 300, 200, 100),
                0, 0, 0, 0, // 铁矿用于特殊用途
                List.of(new Prerequisite(QUARRY, 3), new Prerequisite(TOWN_HALL, 5))),
            
            // 兵营 - 训练士兵
            new BuildingConfig(BARRACKS, "兵营",
                ResourceCost.of(400, 300, 200, 100),
                0, 0, 0, 0,
                List.of(new Prerequisite(TOWN_HALL, 3))),
            
            // 马厩 - 训练骑兵
            new BuildingConfig(STABLE, "马厩",
                ResourceCost.of(600, 400, 300, 200),
                0, 0, 0, 0,
                List.of(new Prerequisite(BARRACKS, 3), new Prerequisite(LUMBER_MILL, 4))),
            
            // 工坊 - 制造攻城器械
            new BuildingConfig(WORKSHOP, "工坊",
                ResourceCost.of(800, 500, 400, 300),
                0, 0, 0, 0,
                List.of(new Prerequisite(IRON_MINE, 2), new Prerequisite(TOWN_HALL, 7))),
            
            // 学院 - 研究科技
            new BuildingConfig(ACADEMY, "学院",
                ResourceCost.of(500, 300, 200, 150),
                0, 0, 0, 0,
                List.of(new Prerequisite(TOWN_HALL, 4))),
            
            // 城墙 - 防御
            new BuildingConfig(WALL, "城墙",
                ResourceCost.of(300, 200, 400, 100),
                0, 0, 0, 0,
                List.of(new Prerequisite(TOWN_HALL, 2))),
            
            // 仓库 - 增加资源上限
            new BuildingConfig(WAREHOUSE, "仓库",
                ResourceCost.of(250, 200, 150, 100),
                0, 0, 0, 0,
                null),
            
            // 医院 - 治疗伤兵
            new BuildingConfig(HOSPITAL, "医院",
                ResourceCost.of(400, 250, 200, 150),
                0, 0, 0, 0,
                List.of(new Prerequisite(TOWN_HALL, 5)))
        ).stream().collect(Collectors.toMap(BuildingConfig::getType, config -> config));
    }
    
    private String type;
    private String name;
    private ResourceCost baseCost;
    private int goldProduction;
    private int woodProduction;
    private int stoneProduction;
    private int foodProduction;
    private List<Prerequisite> prerequisites;
    
    public BuildingConfig(String type, String name, ResourceCost baseCost,
                         int goldProd, int woodProd, int stoneProd, int foodProd,
                         List<Prerequisite> prerequisites) {
        this.type = type;
        this.name = name;
        this.baseCost = baseCost;
        this.goldProduction = goldProd;
        this.woodProduction = woodProd;
        this.stoneProduction = stoneProd;
        this.foodProduction = foodProd;
        this.prerequisites = prerequisites;
    }
    
    /**
     * 获取指定等级的升级成本 (等级越高成本越高)
     */
    public ResourceCost getUpgradeCost(int currentLevel) {
        double multiplier = 1.0 + (currentLevel * 0.3); // 每级增加 30% 成本
        return new ResourceCost(
            (long)(baseCost.getGold() * multiplier),
            (long)(baseCost.getWood() * multiplier),
            (long)(baseCost.getStone() * multiplier),
            (long)(baseCost.getFood() * multiplier)
        );
    }
    
    /**
     * 获取指定等级的资源产量
     */
    public ResourceProduction getProductionAtLevel(int level) {
        return new ResourceProduction(
            goldProduction * level,
            woodProduction * level,
            stoneProduction * level,
            foodProduction * level
        );
    }
    
    /**
     * 检查是否满足前置条件
     */
    public boolean checkPrerequisites(Map<String, Integer> buildingLevels) {
        if (prerequisites == null || prerequisites.isEmpty()) {
            return true;
        }
        
        for (Prerequisite prereq : prerequisites) {
            int currentLevel = buildingLevels.getOrDefault(prereq.buildingType, 0);
            if (currentLevel < prereq.requiredLevel) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 根据类型获取配置
     */
    public static BuildingConfig getConfig(String type) {
        return CONFIG_CACHE.get(type);
    }
    
    /**
     * 获取所有配置
     */
    public static Map<String, BuildingConfig> getAllConfigs() {
        return CONFIG_CACHE;
    }
    
    /**
     * 前置条件
     */
    @Data
    @NoArgsConstructor
    public static class Prerequisite {
        public String buildingType;
        public int requiredLevel;
        
        public Prerequisite(String buildingType, int requiredLevel) {
            this.buildingType = buildingType;
            this.requiredLevel = requiredLevel;
        }
    }
    
    /**
     * 资源成本
     */
    @Data
    @NoArgsConstructor
    public static class ResourceCost {
        public long gold;
        public long wood;
        public long stone;
        public long food;
        
        public ResourceCost(long gold, long wood, long stone, long food) {
            this.gold = gold;
            this.wood = wood;
            this.stone = stone;
            this.food = food;
        }
        
        public static ResourceCost empty() {
            return new ResourceCost(0, 0, 0, 0);
        }
        
        public static ResourceCost of(long gold, long wood, long stone, long food) {
            return new ResourceCost(gold, wood, stone, food);
        }
        
        /**
         * 检查资源是否足够
         */
        public boolean canAfford(long currentGold, long currentWood, 
                                long currentStone, long currentFood) {
            return currentGold >= gold &&
                   currentWood >= wood &&
                   currentStone >= stone &&
                   currentFood >= food;
        }
    }
    
    /**
     * 资源产量
     */
    @Data
    @NoArgsConstructor
    public static class ResourceProduction {
        public int goldPerHour;
        public int woodPerHour;
        public int stonePerHour;
        public int foodPerHour;
        
        public ResourceProduction(int gold, int wood, int stone, int food) {
            this.goldPerHour = gold;
            this.woodPerHour = wood;
            this.stonePerHour = stone;
            this.foodPerHour = food;
        }
    }
}
