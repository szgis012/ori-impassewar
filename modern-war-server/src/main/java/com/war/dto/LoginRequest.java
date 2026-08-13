package com.war.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求 DTO
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String username;

    /** 密码 (Base64 编码) */
    private String password;

    /** 是否记住我 */
    private Boolean rememberMe;
}
