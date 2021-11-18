<template>
  
  <b-container fluid >
    <b-container
      fluid="md"
      class="p-4"
      style="height:600px;"
      >
      {{this.$store.state.member.zzim}}zzz
        <b-row align-v="center" style="height:100%">
          <b-col cols='7'>
            <b-row>
              <h2>우리동네</h2>
              <h2>중고직거래마켓</h2>
              <p>동네 주민들과 가깝고 따뜻한 거래를 지금 경험해보세요.</p>
            </b-row>
            <b-row>
              <b-col>
                
                <b-button href="/board"
                class="bg-primary m-2 ">
                내지역보기
                </b-button>

                <b-button
                class="bg-primary m-2"
                v-b-modal.needLogin>
                버튼
                </b-button>

              </b-col>
            </b-row>
          </b-col>

          <b-col cols='5'>
              <img src="../../assets/map.png">
          </b-col>
       </b-row>

      <div>

        <b-modal id="needLogin" centered hide-footer>
           <needLogin />
        </b-modal>
  
</div>
  
      

    </b-container>
  </b-container>
</template>

<script>
import NeedLogin from '../Modal/NeedLogin';

export default {
    name:'area',
    components:{
      NeedLogin
    }
    
}
</script>

<style scoped>
h2{
  font-size: 50px;
}
p{
  margin: 20px 0;
}
</style>