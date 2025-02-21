

<script setup lang="ts">
import {reactive} from "vue";
import SignUp from "@/entity/user/SignUp.ts";
import axios from "axios";

const state = reactive({
  signUp: new SignUp(),
})

function doSignUp() {
  console.log(state.signUp)
  axios.post("/api/auth/signup", state.signUp)
    .then(() => {
      console.log("회원가입 성공")
      location.href = '/'
    })
    .catch(() => {
      console.log("회원가입 실패")
    })
}

</script>
<template>
  <el-row>
    <el-col :span="10" :offset="7">
      <el-form label-position="top">

        <el-form-item label="이름">
          <el-input v-model="state.signUp.name"></el-input>
        </el-form-item>

        <el-form-item label="아이디">
          <el-input v-model="state.signUp.email"></el-input>
        </el-form-item>

        <el-form-item label="비밀번호">
          <el-input type="password" v-model="state.signUp.password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="doSignUp()">회원가입</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style scoped>
.signup-container {
  width: 300px;
  margin: 0 auto;
  padding: 1rem;
}
</style>
