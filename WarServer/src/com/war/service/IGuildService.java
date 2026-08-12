package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.Guild;
import com.war.domain.GuildAttack;
import com.war.domain.GuildEvent;
import com.war.domain.GuildExt;
import com.war.domain.GuildIncExpHistory;
import com.war.domain.GuildPlaAppInv;
import com.war.domain.GuildPlayer;
import com.war.domain.GuildRelationship;
import com.war.domain.GuildTechnology;
import com.war.domain.ProcessQueue;
import com.war.exception.GameException;

public interface IGuildService {
	
	/**
	 * 初始化军团编号军团名称Map
	 * @return 军团编号军团名称Map(key:军团编号 value:军团名称)
	 */
	public Map<Integer, String> initGuildIDGuildNameMap();
	
	/**
	 * 创建军团
	 * @param guild
	 * @return 新建军团的编号
	 * @throws 
	 * GameException 会长/创始人已加入军团
	 * GameException 公会名已存在
	 */
	public Integer createGuild(Guild guild) ;

	/**
	 * 更新军团信息(名称、介绍、公告)
	 * @param guild
	 */
	public void updateGuildInfo(Guild guild);
	
	/**
	 * 更新军团
	 * @param guild
	 */
	public void updateGuild(Guild guild);
	
	/**
	 * 根据军团名称获得军团编号
	 * @param guildName
	 * @return
	 */
	public Integer getGuildIDByGuildName(String guildName);
	
	/**
	 * 根据军团名称获得军团排名
	 * @param guildName
	 * @return
	 */
	public Integer getGuildRankByGuildName(String guildName);
	
	/**
	 * 根据编号获得军团名称
	 * @param guildID
	 */
	public String getGuildNameByID(Integer guildID);
	
	/**
	 * 根据编号获得军团玩家列表
	 * @param guildID
	 * @return
	 */
	public List<GuildPlayer> getGuildPlayerListByGuildID(Integer guildID);
	
	/**
	 * 获得玩家军团编号及名称
	 * @param playerID
	 * @return
	 */
	public Map<String,Object> getPlayerGuildIDAndName(Integer playerID);
	
	/**
	 * 解散军团
	 * @param guildID
	 */
	public void dismissGuild(Integer playerID);

	/**
	 * 刷新军团声望及排名
	 */
	public void refreshGuildRenownAndRank();
	
	/**
	 * 根据军团编号获得军团
	 * @param guildID
	 * @return
	 */
	public Guild getGuildByID(Integer guildID);
	
	/**
	 * 获得军团数量
	 * @return
	 */
	public Integer getGuildNum();
	
	/**
	 * 获得军团分页列表
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<Guild> getGuildPagingList(Integer start,Integer offset);
	
	/**
	 * 获得军团列表
	 * @return
	 */
	public List<Guild> getGuildList();
	
	/**
	 * 申请加入军团
	 * @param guildID
	 * @param playerID
	 */
	public void applyJoinGuild(Integer guildID,Integer playerID);
	
	/**
	 * 取消申请加入军团
	 * @param guildID
	 * @param playerID
	 */
	public void cancelApplyJoinGuild(Integer guildID,Integer playerID);
	
	/**
	 * 同意加入军团申请
	 * @param playerID
	 * @param guildID
	 */
	public void accpetPlayerJoinGuildApplication(Integer playerID,Integer guildID);
	
	/**
	 * 拒绝加入军团申请
	 * @param playerID
	 * @param guildID
	 */
	public void refusePlayerJoinGuildApplication(Integer playerID,Integer guildID);
	
	/**
	 * 邀请加入军团
	 * @param guildID
	 * @param playerName
	 */
	public void inviteJoinGuild(Integer guildID,String playerName);
	
	/**
	 * 取消邀请玩家
	 * @param guildID
	 * @param playerID
	 */
	public void cancelInvitePlayer(Integer guildID,Integer playerID);
	
	/**
	 * 同意军团邀请
	 * @param playerID
	 * @param guildID
	 */
	public void acceptGuildInvitation(Integer playerID,Integer guildID);
	
	/**
	 * 拒绝军团邀请
	 * @param playerID
	 * @param guildID
	 */
	public void refuseGuildInvitation(Integer playerID,Integer guildID);
	
	/**
	 * 退出军团
	 * @param playerID
	 */
	public void exitGuild(Integer playerID);
	
	/**
	 * 获得玩家军团申请邀请列表
	 * @param playerID
	 * @return
	 */
	public List<GuildPlaAppInv> getPlayerAppInvList(Integer playerID);
	
	/**
	 * 获得军团申请邀请列表
	 * @param guildID
	 * @return
	 */
	public List<GuildPlaAppInv> getGuildPlaAppInvList(Integer guildID);
	
	/**
	 * 新增军团成员
	 * @param guildPlayer
	 * @throws
	 */
	public void addGuildPlayer(GuildPlayer guildPlayer);
	
	/**
	 * 更新军团成员
	 * @param guildPlayer
	 */
	public void updateGuildPlayer(GuildPlayer guildPlayer);

	/**
	 * 根据军团编号及成员编号删除军团成员
	 * @param guildID
	 * @param playerID
	 */
	public void deleteGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID);
	
	/**
	 * 根据军团编号及玩家名称删除军团成员
	 * @param guildID
	 * @param playerName
	 */
	public void deleteGuildPlayerByGuildIDAndPlayerName(Integer guildID,String playerName);
	
	/**
	 * 军团成员授权
	 * @param guildPlayer(guildID,playerID,dutyName,permission)
	 */
	public void guildMemeberGrant(GuildPlayer guildPlayer);

	/**
	 * 获得军团成员数量
	 * @param guildID
	 * @return
	 */
	public Integer getGuildMemberAmount(Integer guildID);
	
	/**
	 * 获得军团成员列表(分页)
	 * @param guildID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildPlayer> getGuildMemeberPagingList(Integer guildID,Integer start,Integer offset);
	
	/**
	 * 获得军团成员列表
	 * @param guildID
	 * @return
	 */
	public List<GuildPlayer> getGuildMemberList(Integer guildID);
	
	/**
	 * 获得军团事件数量
	 * @param guildID
	 * @return
	 */
	public Integer getGuildEventAmount(Integer guildID);
	
	/**
	 * 获得军团事件列表(分页)
	 * @param guildID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildEvent> getGuildEventPagingList(Integer guildID,Integer start,Integer offset);
	
	/**
	 * 获得军团攻击数量
	 * @param guildID
	 * @return
	 */
	public Integer getGuildAttackAmount(Integer guildID);
	
	/**
	 * 获得军团攻击列表(分页)
	 * @param guildID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildAttack> getGuildAttackPagingList(Integer guildID,Integer start,Integer offset);
	
	/**
	 * 根据军团编号及成员编号获得军团成员
	 * @param guildID
	 * @param playerID
	 * @return
	 */
	public GuildPlayer getGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID);
	
	/**
	 * 添加军团关系
	 * @param guildID
	 * @param guildName
	 * @param type 1.友好 2.中立 3.敌对
	 */
	public void addGuildRelationship(Integer guildID,String targetGuildName,Integer type);
	
	/**
	 * 移除军团关系
	 * @param guildID
	 * @param guildName
	 */
	public void removeGuildRelationship(Integer guildID,String targetGuildName);
	
	/**
	 * 根据军团编号获得军团关系列表
	 * @param guildID
	 * @return
	 */
	public List<GuildRelationship> getGuildRelationshipListByGuildID(Integer guildID);
	
	/**
	 * 玩家是否已经创建或者加入了军团
	 * @param playerID 
	 * @return
	 */
	public boolean hasCreateOrJoinGuild(Integer playerID);
	
	/**
	 * 添加军团攻击历史
	 * @param guildID
	 * @param targetGuildID
	 * @param description
	 * @param type
	 */
	public void addGuildAttack(Integer guildID, Integer targetGuildID, String description, Integer type);

	/**
	 * 官员辞职
	 * @param guildID
	 * @param playerID
	 * @param type
	 */
	public void resignOfficialPosition(Integer guildID, Integer playerID, Integer type);
	
	/**
	 * 军团成员捐献物资到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param money		金币数量
	 */
	public void donateMoney(Integer guildID, Integer playerID, Integer money);
	
	/**
	 * 军团成员捐献军旗到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param oriflammeType		军旗类型
	 * @param oriflammeNum	军旗数量
	 */
	public void donateOriflamme(Integer guildID, Integer playerID, Integer oriflammeType, Integer oriflammeNum);
	
	/**
	 * 升级军团
	 * @param guildID 	军团ID
	 */
	public void upgradeGuild(Integer guildID);
	
	/**
	 * 
	 * 取得军团可研究科技列表
	 * @param guildID
	 * @return list
	 */
	public List<GuildTechnology> getGuildTechnology(Integer guildID);
	
	/**
	 * 升级军团科技
	 * @param guildID
	 * @param technologyID
	 */
	public void upgradeTechnology(Integer guildID, Integer technologyID);
	
	/**
	 * 
	 * 领取军团补贴
	 * @param guildID
	 * @param playerID
	 */
	public void receiveSubsidy(Integer guildID, Integer playerID);
	
	/**
	 * 是否领取过军团补贴
	 * @param playerID
	 * @return
	 */
	public boolean hasReceivedSubsidy(Integer playerID);
	
	/**
	 * 军团分红
	 * @param guildID
	 */
	public void shareProfit(Integer guildID);
	
	/**
	 * 取得军团所有的收入历史
	 * @param guildID
	 * @param type
	 * @return list
	 */
	public List<GuildIncExpHistory> getAllGuildIncomeInfo(Integer guildID);
	
	/**
	 * 取得军团所有的支出历史
	 * @param guildID
	 * @param type
	 * @return list
	 */
	public List<GuildIncExpHistory> getAllGuildExpenseInfo(Integer guildID);
	
	/**
	 * 取得军团收入总和或支出总和或玩家捐献总和
	 * 1：若playerID为null表示取得军团收入总和或支出总和（type不为null）
	 * 2：若playerID不为Null表示取得军团玩家捐献总和（type应为null）
	 * @param guildID
	 * @param guildPlayerID
	 * @param type
	 */
	public List<GuildPlayer> getTotalAlmsOfGuildMemberInGuild(Integer guildID, Integer guildPlayerID, Integer type);
	
	/**
	 * 取得军团成员捐献的历史记录
	 * @param guildID
	 * @param guildPlayerID
	 */
	public List<GuildIncExpHistory> getAlmsHistoryOfGuildMember(Integer guildID, Integer guildPlayerID);
	
	/**
	 * 研究军团科技
	 * @param cityID
	 * @param technologyID
	 * @param guildID
	 * @return 
	 */
	public void researchGuildTechnologyByTechnologyID(Integer cityID, Integer technologyID, Integer guildID);
	
	/**
	 * 取消研究军团科技
	 * @param cityID
	 * @param technologyID
	 * @param guildID
	 * @return 
	 */
	public void cancelResearchGuildTechnology(Integer cityID, Integer technologyID, Integer guildID);
	
	/**
	 * 完成研究军团科技
	 * @param processQueue
	 * @return 
	 */
	public void finishResearchGuildTechnology(ProcessQueue processQueue);
	
	/**
	 * 修改军团介绍和公告
	 * @param guildID
	 * @return list
	 */
	public void modifyGuildNoticeAndIntro(Integer guildID, String introduction, String notice);
	
	/**
	 * 获得军团扩展信息
	 * @param guildID
	 */
	public GuildExt getGuildExt(Integer guildID);
	
	/**
	 * 获得用户的军团贡献值：用户没有加入军团返回0
	 * @param guildID
	 * @param playerID
	 * @return
	 */
	public long getGuildPlayerContribution(Integer guildID, Integer playerID);

	/**
	 * 更改是否允许驻军的状态
	 * @param playerID
	 * @param guildID
	 * @param state 是否允许驻军
	 */
	public void changeGarrisonState(Integer playerID, Integer guildID, Integer state);
	
}
