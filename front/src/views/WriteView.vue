
<script setup>
import router from '@/router';
import axios from 'axios';
import { reactive, toRaw } from 'vue';

const state = reactive({
  email: '',
  password: '',
  name: '',
});

const join = async () => {
  const url = '/api/saveMember';
  const rawData = toRaw(state); // reactive 객체 → 일반 객체 변환
  axios
    .post(url, rawData,
    { timeout: 900 }) // JSON.stringify 필요 없음!
    .then((response) => {
      console.log('# 응답객체 : ', response);
      router.replace('home')
    })
    .catch((e) => {
      console.log('에러=================');
      console.log(e);
      if (e instanceof Error) console.log(e.message);
      else console.log(e);
    });

};
</script>

<template>
  <div class="login-container">
    <div class="mb-3 mt-3">
      <label for="email">아이디</label>
      <input type="text" class="form-control" v-model="state.email" placeholder="아이디를 입력하세요." name="email" />
    </div>
    <div class="mb-3 mt-3">
      <label for="password">비밀번호</label>
      <input type="password" class="form-control" v-model="state.password" placeholder="비밀번호를 입력하세요." name="password" />
    </div>
    <div class="mb-3 mt-3">
      <label for="name">이름</label>
      <input type="text" class="form-control" v-model="state.name" placeholder="이름을 입력하세요." name="name" />
    </div>
    <button type="button" class="btn btn-primary ms-3" @click="join">회원가입</button>
  </div>
</template>

<style></style>
