<template>
  <!-- defaultLayout의 헤더 -->
  <b-container fluid="md" style="height:100px;">
    <b-row>
      <b-navbar>
        <b-col align-self="center" class="nav justify-content-start">
          <b-navbar-brand href='/'>
            <!-- 클릭하면 홈으로 돌아가는 로고 -->
            <img src='../../assets/Daangeun_Logo_Color_RGB.png'>
          </b-navbar-brand>
        </b-col>

        <b-col cols='6' align-self="center" class="nav justify-content-center">
          <b-input-group>
            <!--검색창 -->
            <b-form-input placeholder="검색"></b-form-input>
            <b-input-group-append>
              <b-button type='submit'>
                <b-icon icon='search'></b-icon>
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </b-col>

        <b-col align-self="start">
          <b-navbar-nav class="nav justify-content-end">
            <!-- 로그인, 회원가입페이지로 넘어가는 네비게이션 -->
            <b-nav-item v-if="this.$store.state.islogin==false" href='/login'>로그인</b-nav-item>
            <!-- if문으로 로그인후 변경하려면 b-navbar-nav 이하로 복사해서 바꾸면됨 -->
            <b-nav-item v-if="this.$store.state.islogin==false" href='/Join'>회원가입</b-nav-item>
            <b-nav-item v-if="this.$store.state.islogin==true" >{{this.$store.state.member.name}}님</b-nav-item>
            <b-nav-item v-if="this.$store.state.islogin==true" href='/MyPage'>마이페이지</b-nav-item>
            <b-nav-item v-if="this.$store.state.islogin==true" @click='logout()'>로그아웃</b-nav-item>

          </b-navbar-nav>
        </b-col>

      </b-navbar>
    </b-row>
  </b-container>
</template>

<script>
  export default {
    name: 'header',
    data(){
      return{
        name:'',
      }
    },
    methods: {
      logout() {
        this.$store.dispatch('logout')
        alert("로그아웃 되었습니다")
      }
    }
  }
</script>

<style scoped>

</style>