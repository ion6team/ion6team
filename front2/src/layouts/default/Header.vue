<template> <!-- defaultLayout의 헤더 -->
  
  <b-navbar
  >
    <b-navbar-brand href='/' > <!-- 클릭하면 홈으로 돌아가는 로고 -->
    <img src='../../assets/logo.jpg'>
    </b-navbar-brand>

    <b-nav-form> <!--검색창 -->
      <b-form-input placeholder="검색"></b-form-input>
      <b-button 
        type='submit'
        variant='primary'>
      <b-icon icon='search'></b-icon>
      </b-button>
    </b-nav-form>

    <b-navbar-nav > <!-- 로그인, 회원가입페이지로 넘어가는 네비게이션 -->
      <b-nav-item href='/login'>로그인</b-nav-item>
      <b-nav-item href='/Join'>회원가입</b-nav-item>
    </b-navbar-nav>
    
  </b-navbar>
  
</template>

<script>
export default {
    name:'header',
}
</script>

<style>

</style>