import { post, get } from './request'
import type { ApiResponse } from './request'

// 玩家信息类型
export interface PlayerInfo {
  playerId: number
  userName: string
  name: string
  headImg: string
  honorId: number
  honorName: string
  guildId: number
  country: number
  renown: number
  attackPoint: number
  defensePoint: number
  rank: number
  money: number
  giftCertificate: number
  haveReceiveDailyReward: number
  state: number
  loginNum: number
  onlineTime: number
  lastLoginTime: string
  createTime: string
  guildName: string
  city?: {
    cityId: number
    cityName: string
    cityLevel: number
    cityType: number
    mapX: number
    mapY: number
    oil: number
    steel: number
    aluminum: number
    oilProduction: number
    steelProduction: number
    aluminumProduction: number
    warehouseCapacity: number
    populationLimit: number
    populationCurrent: number
    wallDurability: number
    wallLevel: number
    commandCenterLevel: number
  }
}

// 登录请求参数
export interface LoginParams {
  username: string
  password: string
  rememberMe?: boolean
}

/**
 * 用户登录
 */
export function login(data: LoginParams): Promise<ApiResponse<PlayerInfo>> {
  return post<PlayerInfo>('/auth/login', data)
}

/**
 * 用户注册
 */
export function register(
  username: string,
  password: string,
  playerName: string,
  country: number = 1
): Promise<ApiResponse<PlayerInfo>> {
  return post<PlayerInfo>('/auth/register', null, {
    params: { username, password, playerName, country },
  })
}

/**
 * 获取玩家信息
 */
export function getPlayerInfo(playerId: number): Promise<ApiResponse<PlayerInfo>> {
  return get<PlayerInfo>(`/auth/player/${playerId}`)
}

/**
 * 检查用户名是否存在
 */
export function checkUsername(username: string): Promise<ApiResponse<boolean>> {
  return get<boolean>(`/auth/check-username/${username}`)
}

/**
 * 检查玩家名称是否存在
 */
export function checkPlayerName(playerName: string): Promise<ApiResponse<boolean>> {
  return get<boolean>(`/auth/check-playername/${playerName}`)
}
