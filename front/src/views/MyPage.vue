<template>
  <b-container>
    <b-tabs content-class="mt-3" justified>
      <b-tab title="회원정보" active><MyInfo /></b-tab>
      <b-tab title="정보수정"><change /></b-tab>
      <b-tab title="찜목록"><Zzim /></b-tab>
    </b-tabs>
  </b-container>
</template>

<script>
import MyInfo from '../components/MyPage/MyInfo.vue'
import Change from '../components/MyPage/Change.vue'
import Zzim from '../components/MyPage/Zzim.vue'
export default {
    name:'myPage',
    components:{
      MyInfo:MyInfo,
      Change:Change,
      Zzim:Zzim
    }
}
</script>

<style scoped>
</style> 