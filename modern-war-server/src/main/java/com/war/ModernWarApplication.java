package com.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 绝地战争现代化重制版 - 启动类
 * @author Modern War Team
 */
@SpringBootApplication
@EnableScheduling
public class ModernWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModernWarApplication.class, args);
        System.out.println("=================================================");
        System.out.println("     绝地战争现代化重制版服务器启动成功!");
        System.out.println("=================================================");
    }
}
