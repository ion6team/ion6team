<template>
  <div class="container">
    <div class="input-form-backgroud">
      <div class="input-form col-md-12 mx-auto">

        <div style="height:300px;" align-center>
          <div>
            <b-link href='/'>
              <img src='../assets/Daangeun_Logo_vertical_Color_RGB.png'>
            </b-link>
          </div>
        </div>

        <h4 class="mb-3">로그인</h4>
        <!-- <form class="validation-form" novalidate> -->

        <div class="mb-3">
          <label for="email">아이디(이메일)</label>
          <input type="email" class="form-control" v-model="email" placeholder="you@example.com" required>
        </div>
        <div class="mb-3">
          <label for="password">비밀번호</label>
          <input type="password" class="form-control" v-model="password" required>
          <div class="invalid-feedback"> 비밀번호를 입력해주세요. </div>
        </div>
        <div class="mb-4"></div>
        <button class="btn btn-primary btn-lg btn-block" type="submit" @click="login()">로그인</button>

        <b-row class="m-3">
          <b-col>
            <a href="#" class="mx-3">아이디/비밀번호찾기</a>
          </b-col>
          <b-col>
            <b-link href='/join' class="mx-3">회원가입</b-link>
          </b-col>
        </b-row>

          <div class="mb-3">
            <label for="email">아이디(이메일)</label>
            <input type="email" class="form-control" v-model="email" placeholder="you@example.com" required>
          </div>
          <div class="mb-3">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" v-model="password" required>
            <div class="invalid-feedback"> 비밀번호를 입력해주세요. </div>
          </div>
          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="submit" @click="login()">로그인</button>
        <!-- </form> -->
        <b-row class="m-3">
          <b-col>
             <a href="#" class="mx-3">아이디/비밀번호찾기</a>
          </b-col>
         <b-col>
            <b-link href='/join' class="mx-3">회원가입</b-link>
         </b-col>
        </b-row>

        {{this.$store.state.token}}

      </div>
      <Findemail v-if="isModalViewed" @close-modal="isModalViewed = false">
      </Findemail>
      <button @click="isModalViewed = true">Open Modal</button>
    </div>
  </div>
</template>

<script>
import Findemail from '../components/Modal/Findemail.vue'
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
      Findemail
    },
    methods: {
      async login() {
        try {
          await this.$store.dispatch('login', {
            email: this.email,
            password: this.password,
          })
        } catch (err) {
          throw new Error(err)
        }
        if (this.$store.state.islogin == true) {
          this.$router.push('/')
        }

      }
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
</style>