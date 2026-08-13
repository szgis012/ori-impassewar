import axios from 'axios'
import type { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router'

// API 响应类型
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
  timestamp: number
}

// 创建 axios 实例
const request: AxiosInstance = axios.create({
  baseURL: '/api',
  timeout: 30000,
  headers: {
    'Content-Type': 'application/json',
  },
})

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    // 添加 token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response: AxiosResponse<ApiResponse>) => {
    const res = response.data

    // 如果返回的状态码不是 200，说明接口有错误
    if (res.code !== 200) {
      // 401: 未授权
      if (res.code === 401) {
        ElMessage.error(res.message || '登录已过期')
        // 清除本地缓存
        localStorage.removeItem('token')
        localStorage.removeItem('playerInfo')
        // 跳转到登录页
        router.push('/login')
        return Promise.reject(new Error(res.message))
      }

      // 其他错误
      ElMessage.error(res.message || '请求失败')
      return Promise.reject(new Error(res.message))
    }

    return res
  },
  (error) => {
    // 处理网络错误
    let message = '网络错误'
    if (error.response) {
      switch (error.response.status) {
        case 400:
          message = '请求参数错误'
          break
        case 401:
          message = '未授权，请重新登录'
          break
        case 403:
          message = '拒绝访问'
          break
        case 404:
          message = '请求地址不存在'
          break
        case 500:
          message = '服务器内部错误'
          break
        case 502:
          message = '网关错误'
          break
        case 503:
          message = '服务不可用'
          break
        case 504:
          message = '网关超时'
          break
        default:
          message = `连接错误${error.response.status}`
      }
    } else if (error.request) {
      message = '无法连接到服务器'
    }

    ElMessage.error(message)
    return Promise.reject(error)
  }
)

// 封装请求方法
export function get<T = any>(url: string, params?: any): Promise<ApiResponse<T>> {
  return request.get(url, { params })
}

export function post<T = any>(url: string, data?: any): Promise<ApiResponse<T>> {
  return request.post(url, data)
}

export function put<T = any>(url: string, data?: any): Promise<ApiResponse<T>> {
  return request.put(url, data)
}

export function del<T = any>(url: string): Promise<ApiResponse<T>> {
  return request.delete(url)
}

export default request
