<script setup lang="ts">
import {reactive} from "vue";
import SignUp from "@/entity/user/SignUp.ts";
import axios from "axios";
import {ElMessage} from "element-plus";
import type HttpError from "@/http/HttpError.ts";
import ItemWrite from "@/entity/item/ItemWrite.ts";

const state = reactive({
  item: new ItemWrite(),
})

function doWrite() {
  axios.post('/api/save/item', state.item)
    .then(() => {
      ElMessage({type: 'success', message: '환영합니다 :)'})
      location.href = '/'
    })
    .catch((e: HttpError) => {
      ElMessage({type: 'error', message: e.getMessage()});
      console.log("회원가입 실패")
    });
}

</script>


<template>
  <el-row>
    <el-col :span="10" :offset="7">
      <el-form label-position="top">

        <el-form-item label="상품 이름">
          <el-input v-model="state.item.name"></el-input>
        </el-form-item>

        <el-form-item label="설명">
          <el-input v-model="state.item.description"
                    :rows="4"
                    type="textarea"
                    placeholder="Please input"></el-input>
        </el-form-item>

        <el-form-item label="가격">
          <el-input type="number" v-model="state.item.price"></el-input>
        </el-form-item>

        <el-form-item label="수량">
          <el-input type="number" v-model="state.item.quantity"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="doWrite">아이템 쓰기</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style scoped>
.wriete-container {
  width: 300px;
  margin: 0 auto;
  padding: 1rem;
}
</style>
