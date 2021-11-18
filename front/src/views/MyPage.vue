<template>
  <div>
    <div id='profileBG' style="padding:100px; min-height:800px;">
      <div style="max-width:1000px; margin:auto;">
        <b-row style="height:600px">
          <b-col cols='3' style="opacity:0.6;">
            <MyInfo />
          </b-col>

          <b-col cols='9' style="background-color:white;" >
            <b-tabs align="start">
              <b-tab title="정보수정" ><change /></b-tab>
              <b-tab title="찜목록" ><Zzim /></b-tab>
            </b-tabs>
          </b-col>
        </b-row>
      </div>
    </div>
  </div>
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
#profileBG{
    background-image:url(../assets/profileBG.jpg);
    background-repeat:no-repeat;
    background-size:cover;
    min-height:100px;
  }
  
</style> 