package com.war.service;

import com.war.domain.Player;
import com.war.dto.LoginRequest;
import com.war.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.war.repository.PlayerRepository;
import com.war.repository.CityRepository;
import com.war.constant.GameConstant;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 认证服务 - 现代化重制版
 * 处理用户登录、注册等认证相关逻辑
 */
@Service
public class AuthService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * 用户登录
     * @param request 登录请求
     * @param ip 客户端 IP
     * @return 包含 Token 和玩家信息的 Map
     */
    @Transactional
    public Map<String, Object> login(LoginRequest request, String ip) {
        // 解码 Base64 密码
        String rawPassword = new String(Base64Utils.decodeFromString(request.getPassword()), StandardCharsets.UTF_8);

        // 查找玩家
        Optional<Player> playerOpt = playerRepository.findByUserName(request.getUsername());
        
        if (playerOpt.isEmpty()) {
            throw new RuntimeException("用户名不存在");
        }

        Player player = playerOpt.get();

        // 验证密码
        if (!passwordEncoder.matches(rawPassword, player.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 检查玩家状态
        if (player.getState() != null && player.getState() != 1) {
            throw new RuntimeException("账号已被封禁");
        }

        // 更新登录信息
        Date now = new Date();
        player.setLastLoginTime(now);
        player.setLastLoginIp(ip);
        player.setLoginNum(player.getLoginNum() + 1);

        // 如果是新的一天，重置每日奖励状态
        if (player.getLastLoginTime() != null) {
            long daysDiff = (now.getTime() - player.getLastLoginTime().getTime()) / (24 * 60 * 60 * 1000);
            if (daysDiff >= 1) {
                player.setHaveReceiveDailyReward(0);
            }
        }

        player = playerRepository.save(player);

        // 生成 JWT Token
        String token = jwtService.generateToken(player.getUserName());

        // 转换为 DTO
        PlayerDTO playerDTO = convertToDTO(player);

        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("expiresIn", jwtExpiration);
        result.put("player", playerDTO);

        return result;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param playerName 玩家名称
     * @param country 国家
     * @return 玩家信息 DTO
     */
    @Transactional
    public PlayerDTO register(String username, String password, String playerName, Integer country) {
        // 检查用户名是否存在
        if (playerRepository.existsByUserName(username)) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查玩家名称是否存在
        if (playerRepository.existsByName(playerName)) {
            throw new RuntimeException("玩家名称已存在");
        }

        // 创建玩家
        Player player = new Player();
        player.setUserName(username);
        player.setPassword(passwordEncoder.encode(password));
        player.setName(playerName);
        player.setCountry(country);
        player.setState(1);
        player.setRenown(0L);
        player.setAttackPoint(0.0);
        player.setDefensePoint(0.0);
        player.setMoney(GameConstant.INITIAL_MONEY);
        player.setGiftCertificate(0);
        player.setHaveReceiveDailyReward(0);
        player.setLoginNum(0);
        player.setOnlineTime(0);

        player = playerRepository.save(player);

        // 创建初始城市
        createInitialCity(player);

        return convertToDTO(player);
    }

    /**
     * 创建初始城市
     */
    private void createInitialCity(Player player) {
        // 这里应该实现城市创建逻辑
        // 为简化示例，暂时跳过详细实现
    }

    /**
     * 将 Player 实体转换为 DTO
     */
    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(player.getPlayerId());
        dto.setUserName(player.getUserName());
        dto.setName(player.getName());
        dto.setHeadImg(player.getHeadImg());
        dto.setHonorId(player.getHonorId());
        dto.setHonorName(player.getHonorName());
        dto.setGuildId(player.getGuildId());
        dto.setCountry(player.getCountry());
        dto.setRenown(player.getRenown());
        dto.setAttackPoint(player.getAttackPoint());
        dto.setDefensePoint(player.getDefensePoint());
        dto.setRank(player.getRank());
        dto.setMoney(player.getMoney());
        dto.setGiftCertificate(player.getGiftCertificate());
        dto.setHaveReceiveDailyReward(player.getHaveReceiveDailyReward());
        dto.setState(player.getState());
        dto.setLoginNum(player.getLoginNum());
        dto.setOnlineTime(player.getOnlineTime());
        dto.setLastLoginTime(player.getLastLoginTime());
        dto.setCreateTime(player.getCreateTime());
        dto.setGuildName(player.getGuildName());

        // 如果有城市信息，也转换
        if (player.getCity() != null) {
            PlayerDTO.CityDTO cityDTO = new PlayerDTO.CityDTO();
            cityDTO.setCityId(player.getCity().getCityId());
            cityDTO.setCityName(player.getCity().getCityName());
            cityDTO.setCityLevel(player.getCity().getCityLevel());
            cityDTO.setCityType(player.getCity().getCityType());
            cityDTO.setMapX(player.getCity().getMapX());
            cityDTO.setMapY(player.getCity().getMapY());
            cityDTO.setOil(player.getCity().getOil());
            cityDTO.setSteel(player.getCity().getSteel());
            cityDTO.setAluminum(player.getCity().getAluminum());
            dto.setCity(cityDTO);
        }

        return dto;
    }

    /**
     * 根据 ID 获取玩家信息
     */
    public PlayerDTO getPlayerById(Integer playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("玩家不存在"));
        return convertToDTO(player);
    }
}
