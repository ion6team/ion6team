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
        <!-- </form> -->
        {{this.$store.state.token}}
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data() {
      return {
        email: '',
        password: '',
      }
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