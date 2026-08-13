import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { PlayerInfo } from '@/api/auth'

export const usePlayerStore = defineStore('player', () => {
  // 状态
  const playerInfo = ref<PlayerInfo | null>(null)
  const token = ref<string>('')
  const isLoggedIn = ref(false)

  // 计算属性
  const playerName = computed(() => playerInfo.value?.name || '')
  const playerLevel = computed(() => playerInfo.value?.city?.cityLevel || 1)
  const money = computed(() => playerInfo.value?.money || 0)
  const resources = computed(() => ({
    oil: playerInfo.value?.city?.oil || 0,
    steel: playerInfo.value?.city?.steel || 0,
    aluminum: playerInfo.value?.city?.aluminum || 0,
  }))

  // 方法
  function setPlayerInfo(info: PlayerInfo) {
    playerInfo.value = info
    isLoggedIn.value = true
  }

  function setToken(newToken: string) {
    token.value = newToken
    localStorage.setItem('token', newToken)
  }

  function updateResources(updates: Partial<typeof resources.value>) {
    if (playerInfo.value?.city) {
      Object.assign(playerInfo.value.city, updates)
    }
  }

  function updateMoney(amount: number) {
    if (playerInfo.value) {
      playerInfo.value.money = (playerInfo.value.money || 0) + amount
    }
  }

  function logout() {
    playerInfo.value = null
    token.value = ''
    isLoggedIn.value = false
    localStorage.removeItem('token')
    localStorage.removeItem('playerInfo')
  }

  // 持久化
  function hydrate() {
    const storedToken = localStorage.getItem('token')
    const storedInfo = localStorage.getItem('playerInfo')
    
    if (storedToken) {
      token.value = storedToken
    }
    
    if (storedInfo) {
      try {
        playerInfo.value = JSON.parse(storedInfo)
        isLoggedIn.value = true
      } catch (e) {
        console.error('Failed to parse stored player info:', e)
      }
    }
  }

  function persist() {
    if (playerInfo.value) {
      localStorage.setItem('playerInfo', JSON.stringify(playerInfo.value))
    }
  }

  return {
    // 状态
    playerInfo,
    token,
    isLoggedIn,
    // 计算属性
    playerName,
    playerLevel,
    money,
    resources,
    // 方法
    setPlayerInfo,
    setToken,
    updateResources,
    updateMoney,
    logout,
    hydrate,
    persist,
  }
})
