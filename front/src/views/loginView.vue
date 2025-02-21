
<script setup lang="ts">
import {reactive} from "vue";
import Login from "@/entity/user/Login.ts";
import axios from "axios";

const state = reactive({
  login: new Login(),
})

function doLogin() {
  console.log(state.login)
  axios.post("/api/auth/login", state.login)
    .then(() => {
      console.log("회원가입 성공")
      location.href = '/'
    })
    .catch(() => {
      console.log("회원가입 실패")
    })
}

function doSignUp() {
  location.href='/signUp'
}

</script>

<template>
  <div class="signup-container">
  <el-row>
    <el-col :span="10" :offset="7">
      <el-form label-position="top">
        <el-form-item label="아이디">
          <el-input v-model="state.login.email"></el-input>
        </el-form-item>

        <el-form-item label="비밀번호">
          <el-input type="password" v-model="state.login.password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="doLogin()">로그인</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="info" style="width: 100%" @click="doSignUp()">회원가입 하러 가기</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
  </div>
</template>

<style scoped lang="scss"></style>
