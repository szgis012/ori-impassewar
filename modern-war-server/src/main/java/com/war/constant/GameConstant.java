package com.war.constant;

/**
 * 游戏常量定义 - 从原项目迁移
 */
public class GameConstant {

    /** 默认新手保护时间 (分钟) */
    public static final int NEWBIE_PROTECT_TIME = 720; // 12 小时

    /** 最大城市数量 */
    public static final int MAX_CITY_COUNT = 5;

    /** 初始资源量 */
    public static final int INITIAL_OIL = 1000;
    public static final int INITIAL_STEEL = 1000;
    public static final int INITIAL_ALUMINUM = 1000;
    public static final int INITIAL_MONEY = 10000;

    /** 资源上限 */
    public static final int RESOURCE_MAX_CAPACITY = 1000000;

    /** 行军速度基础值 */
    public static final int MARCH_SPEED_BASE = 100;

    /** 战斗持续时间 (秒) */
    public static final int BATTLE_DURATION = 300;

    /** 间谍侦察持续时间 (秒) */
    public static final int SPY_DURATION = 180;

    /** 市场交易税率 (%) */
    public static final int MARKET_TAX_RATE = 10;

    /** 好友数量上限 */
    public static final int MAX_FRIEND_COUNT = 50;

    /** 军团成员上限 */
    public static final int MAX_GUILD_MEMBER_COUNT = 100;

    /** 聊天消息最大长度 */
    public static final int CHAT_MESSAGE_MAX_LENGTH = 200;

    /** 邮件标题最大长度 */
    public static final int MAIL_TITLE_MAX_LENGTH = 50;

    /** 邮件内容最大长度 */
    public static final int MAIL_CONTENT_MAX_LENGTH = 1000;

    /** 排行榜显示数量 */
    public static final int RANK_DISPLAY_COUNT = 100;

    /** 每日登录奖励重置时间 (小时) */
    public static final int DAILY_REWARD_RESET_HOUR = 5;

    /** 地图格子大小 */
    public static final int MAP_GRID_SIZE = 50;

    /** 视野范围 */
    public static final int VISION_RANGE = 5;

    /** 最大攻击队列数 */
    public static final int MAX_ATTACK_QUEUE = 10;

    /** 最大防御队列数 */
    public static final int MAX_DEFENSE_QUEUE = 5;

    /** 英雄最大等级 */
    public static final int HERO_MAX_LEVEL = 100;

    /** 建筑最大等级 */
    public static final int BUILDING_MAX_LEVEL = 20;

    /** 科技最大等级 */
    public static final int TECHNOLOGY_MAX_LEVEL = 15;

    /** 士兵最大星级 */
    public static final int SOLDIER_MAX_STAR = 6;

    /** 装备最大品质 */
    public static final int EQUIPMENT_MAX_QUALITY = 7;

    /** PVP 保护时间 (分钟) */
    public static final int PVP_PROTECT_TIME = 480; // 8 小时

    /** 迁城冷却时间 (小时) */
    public static final int CITY_RELOCATE_COOLDOWN = 24;

    /** 资源援助上限 */
    public static final int RESOURCE_AID_MAX = 50000;

    /** 战斗报告保留天数 */
    public static final int BATTLE_REPORT_RETAIN_DAYS = 30;

    /** 系统消息保留天数 */
    public static final int SYSTEM_MESSAGE_RETAIN_DAYS = 7;
}
