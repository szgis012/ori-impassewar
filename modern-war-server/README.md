# 绝地战争现代化重制版 - 后端服务

## 技术栈
- **框架**: Spring Boot 3.2.0
- **语言**: Java 17
- **数据库**: MySQL 8.0+
- **ORM**: Spring Data JPA (Hibernate)
- **缓存**: Redis
- **安全**: Spring Security + BCrypt
- **API**: RESTful + WebSocket

## 项目结构
```
modern-war-server/
├── src/main/java/com/war/
│   ├── ModernWarApplication.java    # 启动类
│   ├── config/                      # 配置类
│   │   ├── SecurityConfig.java      # 安全配置
│   │   └── WebConfig.java           # Web 配置
│   ├── constant/                    # 常量定义
│   │   └── GameConstant.java        # 游戏常量
│   ├── controller/                  # REST 控制器
│   │   └── AuthController.java      # 认证控制器
│   ├── domain/                      # JPA 实体
│   │   ├── Player.java              # 玩家实体
│   │   └── City.java                # 城市实体
│   ├── dto/                         # 数据传输对象
│   │   ├── ApiResponse.java         # 通用响应
│   │   ├── LoginRequest.java        # 登录请求
│   │   └── PlayerDTO.java           # 玩家信息 DTO
│   ├── repository/                  # 数据访问层
│   │   ├── PlayerRepository.java    # 玩家 Repository
│   │   └── CityRepository.java      # 城市 Repository
│   └── service/                     # 业务逻辑层
│       └── AuthService.java         # 认证服务
├── src/main/resources/
│   └── application.yml              # 应用配置
└── pom.xml                          # Maven 配置
```

## 快速开始

### 前置要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 安装步骤

1. **创建数据库**
```sql
CREATE DATABASE war_modern CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. **修改配置**
编辑 `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/war_modern
    username: your_username
    password: your_password
  data:
    redis:
      host: localhost
      port: 6379
```

3. **编译打包**
```bash
mvn clean package -DskipTests
```

4. **运行应用**
```bash
java -jar target/modern-war-server-1.0.0-SNAPSHOT.jar
```

或使用 Maven:
```bash
mvn spring-boot:run
```

## API 接口

### 认证相关

#### 用户登录
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "dGVzdDEyMw==",  // Base64 编码的密码
  "rememberMe": true
}
```

#### 用户注册
```http
POST /api/auth/register?username=test&password=test123&playerName=测试玩家&country=1
```

#### 获取玩家信息
```http
GET /api/auth/player/{playerId}
```

## 从旧版本迁移

### 数据库迁移
原项目的数据库结构需要转换:
1. 表名和字段名改为小写 + 下划线风格
2. 主键统一为自增 ID
3. 添加版本控制和审计字段

### 代码迁移对照
| 旧版本 | 新版本 |
|--------|--------|
| Spring 2.5 | Spring Boot 3.2 |
| iBATIS | Spring Data JPA |
| AMF 协议 | REST JSON |
| WebService | REST Controller |
| Quartz | @EnableScheduling |

## 下一步开发计划

1. ✅ 基础架构搭建
2. ✅ 玩家实体和认证模块
3. ⏳ 城市管理系统
4. ⏳ 资源生产系统
5. ⏳ 建筑升级系统
6. ⏳ 军事系统
7. ⏳ 战斗系统
8. ⏳ 军团系统
9. ⏳ 聊天系统
10. ⏳ WebSocket 实时通信

## 开发规范

- 使用 Lombok 简化代码
- 统一使用 ApiResponse 封装返回结果
- Service 层处理业务逻辑和事务
- Controller 层只处理 HTTP 相关
- Repository 层只负责数据访问
- 异常通过 RuntimeException 抛出，全局异常处理器捕获

## 注意事项

- 生产环境必须修改默认密码和密钥
- 配置合适的 CORS 策略
- 启用 HTTPS
- 配置日志轮转
- 设置合理的数据库连接池参数
