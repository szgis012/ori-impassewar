package com.war.controller;

import com.war.dto.ApiResponse;
import com.war.dto.LoginRequest;
import com.war.dto.PlayerDTO;
import com.war.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证控制器 - 现代化重制版
 * 处理用户登录、注册等 HTTP 请求
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // 开发环境允许跨域，生产环境需要配置具体域名
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@Valid @RequestBody LoginRequest request, 
                                         HttpServletRequest httpRequest) {
        try {
            String clientIp = getClientIp(httpRequest);
            Map<String, Object> result = authService.login(request, clientIp);
            return ApiResponse.success("登录成功", result);
        } catch (RuntimeException e) {
            return ApiResponse.error(401, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error("登录失败：" + e.getMessage());
        }
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ApiResponse<PlayerDTO> register(@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String playerName,
                                            @RequestParam(defaultValue = "1") Integer country) {
        try {
            PlayerDTO playerDTO = authService.register(username, password, playerName, country);
            return ApiResponse.success("注册成功", playerDTO);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error("注册失败：" + e.getMessage());
        }
    }

    /**
     * 获取玩家信息
     */
    @GetMapping("/player/{playerId}")
    public ApiResponse<PlayerDTO> getPlayerById(@PathVariable Integer playerId) {
        try {
            PlayerDTO playerDTO = authService.getPlayerById(playerId);
            return ApiResponse.success(playerDTO);
        } catch (RuntimeException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error("获取玩家信息失败：" + e.getMessage());
        }
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping("/check-username/{username}")
    public ApiResponse<Boolean> checkUsername(@PathVariable String username) {
        // TODO: 需要在 Service 层添加对应方法
        return ApiResponse.success(false);
    }

    /**
     * 检查玩家名称是否存在
     */
    @GetMapping("/check-playername/{playerName}")
    public ApiResponse<Boolean> checkPlayerName(@PathVariable String playerName) {
        // TODO: 需要在 Service 层添加对应方法
        return ApiResponse.success(false);
    }

    /**
     * 获取客户端 IP 地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多个代理，取第一个 IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}
