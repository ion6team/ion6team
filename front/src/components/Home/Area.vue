<template>
  <b-container fluid>
    <b-container fluid="md" class="p-4" style="height:600px;">
      <b-row align-v="center" style="height:100%">
        <b-col cols='7'>
          <b-row>
            <h2>우리동네</h2>
            <h2>중고직거래마켓</h2>
            <p>동네 주민들과 가깝고 따뜻한 거래를 지금 경험해보세요.</p>
          </b-row>
          <b-row>
            <b-col>

              <b-button @click="goboard()" class=" m-2 " style="background-color:#ff8a3d; border-color:#fec69f;">
                내지역보기
              </b-button>


            </b-col>
          </b-row>
        </b-col>

        <b-col cols='5'>
          <img src="../../assets/map.png">
        </b-col>
      </b-row>

      <div>

        <b-modal id="needLogin" ref="my-modal" centered hide-header hide-footer style="position:relative;">
<template #default="{ close }">
                  <div style="background-color: #fbf7f2; min-height:200px; width:100%;">
                      <b-button style="background-color:#ff8a3d; border:3px solid white; border-radius:50%; height:40px; width:40px; text0align:center; position:absolute; top:-10px; right:-10px;"
                    @click="close()">
                      X
                    </b-button>
                  
                    <needLogin />

                  </div>
              </template>
            </b-modal>

      </div>


    </b-container>
  </b-container>
</template>

<script>
  import NeedLogin from '../Modal/NeedLogin';

  export default {
    name: 'area',
    components: {
      NeedLogin
    },
    methods: {
       goboard() {
        if (this.$store.state.islogin==false) {
       
          this.$refs['my-modal'].show()
        } else {
            this.$router.push({
          name:'Board',
          params:{
            id:'0',
            keyword:''
            }
            })
            this.$store.commit('setctindex', 0)
            this.$store.commit('setkeywordsearch','')
        }
      } 
    }

  }
</script>

<style scoped>
  h2 {
    font-size: 50px;
  }

  p {
    margin: 20px 0;
  }
  button:hover{
    box-shadow: 0 0 2px black;
  }
</style>