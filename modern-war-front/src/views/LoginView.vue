<template>
  <div class="login-view">
    <div class="login-container">
      <div class="login-box">
        <h1 class="title">绝地战争</h1>
        <p class="subtitle">现代化重制版</p>

        <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input 
              v-model="form.username" 
              placeholder="请输入用户名"
              prefix-icon="User"
              size="large"
            />
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input 
              v-model="form.password" 
              type="password"
              placeholder="请输入密码"
              prefix-icon="Lock"
              size="large"
              show-password
              @keyup.enter="handleLogin"
            />
          </el-form-item>

          <el-form-item>
            <el-checkbox v-model="form.rememberMe">记住我</el-checkbox>
          </el-form-item>

          <el-form-item>
            <el-button 
              type="primary" 
              size="large" 
              :loading="loading"
              @click="handleLogin"
              style="width: 100%"
            >
              登录
            </el-button>
          </el-form-item>

          <div class="links">
            <router-link to="/register">注册新账号</router-link>
          </div>
        </el-form>
      </div>

      <div class="notice-box">
        <h3>游戏公告</h3>
        <div class="notice-content">
          <p>欢迎来到绝地战争现代化重制版！</p>
          <p>游戏已全面升级，采用最新技术打造。</p>
          <p>祝您游戏愉快！</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { login } from '@/api/auth'
import { usePlayerStore } from '@/stores/player'
import CryptoJS from 'crypto-js'

const router = useRouter()
const playerStore = usePlayerStore()
const formRef = ref<FormInstance>()
const loading = ref(false)

const form = reactive({
  username: '',
  password: '',
  rememberMe: false,
})

const rules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度在 3-20 个字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在 6-20 个字符', trigger: 'blur' },
  ],
}

const handleLogin = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        // Base64 编码密码 (兼容旧版)
        const encodedPassword = CryptoJS.enc.Base64.stringify(
          CryptoJS.enc.Utf8.parse(form.password)
        )

        const res = await login({
          username: form.username,
          password: encodedPassword,
          rememberMe: form.rememberMe,
        })

        // 保存玩家信息
        playerStore.setPlayerInfo(res.data)
        playerStore.persist()

        ElMessage.success('登录成功')
        
        // 跳转到游戏页面
        router.push('/game')
      } catch (error) {
        console.error('Login failed:', error)
      } finally {
        loading.value = false
      }
    }
  })
}
</script>

<style scoped lang="scss">
.login-view {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
  position: relative;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: url('@/assets/login-bg.jpg') no-repeat center center;
    background-size: cover;
    opacity: 0.3;
    z-index: 0;
  }
}

.login-container {
  position: relative;
  z-index: 1;
  display: flex;
  gap: 50px;
  max-width: 1000px;
}

.login-box {
  background: rgba(0, 0, 0, 0.7);
  padding: 40px;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  min-width: 400px;

  .title {
    font-size: 36px;
    color: #ffd700;
    text-align: center;
    margin-bottom: 10px;
    text-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
  }

  .subtitle {
    font-size: 16px;
    color: #ccc;
    text-align: center;
    margin-bottom: 30px;
  }
}

.links {
  text-align: center;
  margin-top: 20px;

  a {
    color: #409eff;
    text-decoration: none;

    &:hover {
      text-decoration: underline;
    }
  }
}

.notice-box {
  background: rgba(0, 0, 0, 0.7);
  padding: 30px;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  min-width: 300px;
  max-width: 400px;

  h3 {
    color: #ffd700;
    font-size: 20px;
    margin-bottom: 20px;
    text-align: center;
  }

  .notice-content {
    color: #ccc;
    line-height: 1.8;
    font-size: 14px;

    p {
      margin-bottom: 10px;
    }
  }
}
</style>
