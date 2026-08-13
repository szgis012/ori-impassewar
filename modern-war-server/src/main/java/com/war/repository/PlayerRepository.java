package com.war.repository;

import com.war.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 玩家数据访问层 - 现代化重制版
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    /**
     * 根据用户名查找玩家
     */
    Optional<Player> findByUserName(String userName);

    /**
     * 根据玩家名称查找玩家
     */
    Optional<Player> findByName(String name);

    /**
     * 检查用户名是否存在
     */
    boolean existsByUserName(String userName);

    /**
     * 检查玩家名称是否存在
     */
    boolean existsByName(String name);

    /**
     * 根据军团 ID 查找玩家列表
     */
    List<Player> findByGuildId(Integer guildId);

    /**
     * 查找指定国家的玩家
     */
    List<Player> findByCountry(Integer country);

    /**
     * 查找在线玩家 (最后登录时间在指定时间之后)
     */
    @Query("SELECT p FROM Player p WHERE p.lastLoginTime > :since")
    List<Player> findOnlinePlayers(@Param("since") Date since);

    /**
     * 按声望排名查询前 N 名玩家
     */
    List<Player> findTop100ByOrderByRenownDesc();

    /**
     * 按进攻点数排名查询前 N 名玩家
     */
    List<Player> findTop100ByOrderByAttackPointDesc();

    /**
     * 按防御点数排名查询前 N 名玩家
     */
    List<Player> findTop100ByOrderByDefensePointDesc();

    /**
     * 统计玩家总数
     */
    long count();

    /**
     * 统计指定国家的玩家数量
     */
    long countByCountry(Integer country);

    /**
     * 更新玩家最后登录时间和 IP
     */
    @Query("UPDATE Player p SET p.lastLoginTime = :lastLoginTime, p.lastLoginIp = :lastLoginIp, p.loginNum = p.loginNum + 1 WHERE p.playerId = :playerId")
    void updateLoginInfo(@Param("playerId") Integer playerId, 
                         @Param("lastLoginTime") Date lastLoginTime, 
                         @Param("lastLoginIp") String lastLoginIp);

    /**
     * 增加玩家在线时间
     */
    @Query("UPDATE Player p SET p.onlineTime = p.onlineTime + :minutes WHERE p.playerId = :playerId")
    void addOnlineTime(@Param("playerId") Integer playerId, @Param("minutes") Integer minutes);

    /**
     * 更新玩家军衔
     */
    @Query("UPDATE Player p SET p.honorId = :honorId, p.honorName = :honorName WHERE p.playerId = :playerId")
    void updateHonor(@Param("playerId") Integer playerId, 
                     @Param("honorId") Integer honorId, 
                     @Param("honorName") String honorName);

    /**
     * 更新玩家金钱
     */
    @Query("UPDATE Player p SET p.money = p.money + :delta WHERE p.playerId = :playerId")
    void updateMoney(@Param("playerId") Integer playerId, @Param("delta") Integer delta);

    /**
     * 更新玩家礼金
     */
    @Query("UPDATE Player p SET p.giftCertificate = p.giftCertificate + :delta WHERE p.playerId = :playerId")
    void updateGiftCertificate(@Param("playerId") Integer playerId, @Param("delta") Integer delta);

    /**
     * 更新玩家声望
     */
    @Query("UPDATE Player p SET p.renown = p.renown + :delta WHERE p.playerId = :playerId")
    void updateRenown(@Param("playerId") Integer playerId, @Param("delta") Long delta);

    /**
     * 更新玩家进攻点数
     */
    @Query("UPDATE Player p SET p.attackPoint = p.attackPoint + :delta WHERE p.playerId = :playerId")
    void updateAttackPoint(@Param("playerId") Integer playerId, @Param("delta") Double delta);

    /**
     * 更新玩家防御点数
     */
    @Query("UPDATE Player p SET p.defensePoint = p.defensePoint + :delta WHERE p.playerId = :playerId")
    void updateDefensePoint(@Param("playerId") Integer playerId, @Param("delta") Double delta);

    /**
     * 设置每日奖励领取状态
     */
    @Query("UPDATE Player p SET p.haveReceiveDailyReward = :received WHERE p.playerId = :playerId")
    void setDailyRewardReceived(@Param("playerId") Integer playerId, @Param("received") Integer received);

    /**
     * 重置所有玩家的每日奖励状态
     */
    @Query("UPDATE Player p SET p.haveReceiveDailyReward = 0")
    void resetDailyRewardForAll();

    /**
     * 查找需要更新排名的玩家
     */
    @Query("SELECT p FROM Player p WHERE p.rank IS NULL OR p.rank < 0")
    List<Player> findPlayersNeedRankUpdate();
}
