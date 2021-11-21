<template>
  <div class="container">
    <div class="input-form-backgroud">
      <div class="input-form col-md-12 mx-auto">

        <div style="height:250px;" align-center>
          <div>
            <b-link href='/'>
              <img src='../assets/Daangeun_Logo_vertical_Color_RGB.png'>
            </b-link>
          </div>
        </div>

        <hr style="background-color:#ff8a3d;">

        <h4 class="my-5" style="color:#ff8a3d;"><b>로그인</b></h4>
        <!-- <form class="validation-form" novalidate> -->

        <b-row>
          <b-col cols='3' offset-md="1" align-self="center">
            <label for="email">아이디(이메일)</label>
          </b-col>

          <b-col cols='7'> 
          <input type="email" class="form-control" v-model="email" placeholder="you@example.com" required style="border-color:#fec69f;">
          </b-col>
        </b-row>
<br>
        <b-row>
          <b-col cols='3' offset-md="1" align-self="center">
            <label for="password">비밀번호</label>
          </b-col>
          <b-col cols='7'> 
            <input type="password" class="form-control" v-model="password" required style="border-color:#fec69f;">
            <div class="invalid-feedback"> 비밀번호를 입력해주세요. </div>
          </b-col>
        </b-row>


        <div class="mb-4"></div>
        <button class="btn btn-primary btn-lg btn-block mb-3" type="submit" @click="login()"
        style="background-color:#ff8a3d; border-color:#fec69f;"
        >로그인</button>



        <b-row class="m-3">
          <b-col>
            <a href="#" class="mx-3" v-b-modal.findEmail>아이디찾기</a>
          </b-col>
          <b-col>
            <a href="#" class="mx-3" v-b-modal.findPassword>비밀번호찾기</a>
          </b-col>
          <b-col>
            <b-link href='/join' class="mx-3">회원가입</b-link>
          </b-col>
        </b-row>
      </div>

  <b-modal id="findEmail"  centered hide-header hide-footer size="lg" style="position:relative;">
              
               <template #default="{ close }">
                  <div style="background-color: #fbf7f2; min-height:200px; width:100%;">
                      <b-button style="background-color:#ff8a3d; border:3px solid white; border-radius:50%; height:40px; width:40px; text0align:center; position:absolute; top:-10px; right:-10px;"
                    @click="close()">
                      X
                    </b-button>
                  
                    <findemail />

                  </div>
              </template>
            </b-modal>

  <b-modal id="findPassword"  centered hide-header hide-footer size="lg" style="position:relative;">
              
               <template #default="{ close }">
                  <div style="background-color: #fbf7f2; min-height:200px; width:100%;">
                      <b-button style="background-color:#ff8a3d; border:3px solid white; border-radius:50%; height:40px; width:40px; text0align:center; position:absolute; top:-10px; right:-10px;"
                    @click="close()">
                      X
                    </b-button>
                  
                    <find-password />

                  </div>
              </template>
            </b-modal>

     </div>

    <b-modal id="welcome" ref="welcome2" centered hide-footer>
    <welcome />
  </b-modal>

      <b-modal id="loginfailed" ref="loginfailed2" centered hide-footer>
    <loginfailed />
  </b-modal>
    
  </div>
</template>

<script>
import Findemail from '../components/Modal/Findemail.vue'
import FindPassword from '../components/Modal/FindPassword.vue'
import Welcome from '../components/Modal/Welcome.vue'
import Loginfailed from '../components/Modal/LoginFailed.vue'

  export default {
    name: 'login',
    data() {
      return {
        email: '',
        password: '',
      isModalViewed: false,
      }
    },
    components: {
      Findemail,
      FindPassword,
        Welcome,
        Loginfailed
    },
    methods:{
        
      async login() {
        try {
          await this.$store.dispatch('login', {
            email: this.email,
            password: this.password,
          })
        } catch (err) {
          alert(err)
        }
        if (this.$store.state.islogin == true) {
          this.$refs['welcome2'].show()
        }
        else{
          this.$refs['loginfailed2'].show()
        }

      },
      
    }
  }
</script>

<style scoped>
  .container {
    min-height: 100vh;
  }

  .input-form {
    max-width: 680px;
    margin-top: 80px;
    padding: 32px;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
  }
  a{
    text-decoration: none;
    color: #56c271;
  }
</style>
