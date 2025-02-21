<script setup lang="ts">
import axios from "axios";
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import router from "@/router";
import {container} from "tsyringe";
import ItemRepository from "@/repository/ItemRepository.ts";
import type Item from "@/entity/item/Item.ts";
import {types} from "sass-embedded";
import Paging from "@/entity/data/Paging.ts";
import type UserProfile from "@/entity/user/UserProfile.ts";
import UserRepository from "@/repository/UserRepository.ts";
import ProfileRepository from "@/repository/ProfileRepository.ts";


const USER_REPOSITORY = container.resolve(UserRepository)
const PROFILE_REPOSITORY = container.resolve(ProfileRepository)


const ITEM_REPOSITORY = container.resolve(ItemRepository);

type StateType = {
  itemList: Paging<Item>;
}

const state = reactive<StateType>({
  itemList: new Paging<Item>(),
})

type UserStateType = {
  profile: UserProfile | null
}

const userstate = reactive<UserStateType>({
  profile: null,
})

function getList(page = 1) {
  ITEM_REPOSITORY.getList(page).then((itemList) => {
    state.itemList = itemList
  })
  console.log(state.itemList);
}

onMounted(() => {
  getList();
})

onBeforeMount(() => {
  USER_REPOSITORY.getProfile().then((profile) => {
    PROFILE_REPOSITORY.setProfile(profile)
    userstate.profile = profile
  })
})

</script>

<template>
  <div class="signup-container">
    <ul>
      <li v-for="item in state.itemList.items" :key="item.id">
        <div class="title">
          <router-link :to="{ name: 'item', params: { itemId: item.id  } }">
            {{ item.name }}
          </router-link>
        </div>
        <div class="content">
          {{ item.description }}
        </div>
        <div class="button" v-if="userstate.profile?.id === item.memberId">
          <el-button type="primary" @click="router.push(`/item/${item.id}`)">수정</el-button>
        </div>
        <div class="sub">
          <span class="regDate">{{ item.getDisplayRegDate() }}</span>
        </div>
      </li>

      <div style="width: 100%; display: flex; justify-content: center">
        <el-pagination
          :background="true"
          layout="prev, pager, next"
          v-model:current-page="state.itemList.page"
          :total="state.itemList.totalElements"
          :default-page-size="10"
          @current-change="(page: number) => getList(page)"
        />
      </div>
    </ul>
  </div>

</template>


<style scoped lang="scss">
.signup-container {
  width: 300px; /* 모바일 가로 크기 정도로 가정 */
  margin: 0 auto;
  padding: 1rem;
}

ul {
  list-style: none;
  padding: 0;

  li {
    margin-bottom: 2rem;

    .title {
      a {
        font-size: 1.1rem;
        color: #383838;
        text-decoration: none;
      }

      &:hover {
        text-decoration: underline;
      }
    }

    .content {
      font-size: 0.85rem;
      margin-top: 8px;
      line-height: 1.4;
      color: #7e7e7e;
    }

    &:last-child {
      margin-bottom: 0;
    }
    .button{
      display: flex;
      justify-content: flex-end;
      margin-top: 8px;
    }
    .sub {
      margin-top: 8px;
      font-size: 0.78rem;

      .regDate {
        margin-left: 10px;
        color: #6b6b6b;
      }

    }

  }
}

</style>
