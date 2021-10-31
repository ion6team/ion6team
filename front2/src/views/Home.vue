<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <h1>홈페이지입니다.</h1>
    <div v-if="this.$store.state.loginSuccess==false">
      <p> 로그인 안하셨어요 </p>
    </div>
    <div v-if="this.$store.state.loginSuccess==true">
    <p> 이메일 >> {{this.$store.state.member.email}} </p>
    <p> 이름 >> {{this.$store.state.member.name}} </p>
        <p> 주소 >> {{this.$store.state.member.address}} </p>
    <p> 상세주소 >> {{this.$store.state.member.address_detail}} </p>
    <p> 권한 >> {{this.$store.state.member.auth}} </p>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {
  }
}
</script>
