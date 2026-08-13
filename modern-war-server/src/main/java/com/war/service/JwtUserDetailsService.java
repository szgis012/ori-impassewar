package com.war.service;

import com.war.domain.Player;
import com.war.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * JWT 用户详情服务
 * 实现 Spring Security 的 UserDetailsService 接口
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * 根据用户名加载用户详情
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> playerOpt = playerRepository.findByUserName(username);
        
        if (playerOpt.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在：" + username);
        }

        Player player = playerOpt.get();

        // 检查用户状态
        if (player.getState() != null && player.getState() != 1) {
            throw new UsernameNotFoundException("账号已被封禁：" + username);
        }

        // 创建 Spring Security 的 UserDetails
        return new User(
            player.getUserName(),
            "", // 密码不在此处验证，由登录时验证
            true, // enabled
            true, // accountNonExpired
            true, // credentialsNonExpired
            true, // accountNonLocked
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
