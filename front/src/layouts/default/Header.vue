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
        <!--ㅋㅋㅋ-->
        <b-col cols='6' align-self="center" class="nav justify-content-center">
          <b-input-group>
            <!--검색창 -->
            <b-form-input placeholder="검색" v-model="keyword" style=" border-color:#fec69f; border-right:0; ">
            </b-form-input>
            <b-input-group-append>
              <b-button @click="search(keyword)" type='submit' style="background-color:#ff8a3d; border-color:#fec69f;">
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
            <b-nav-item v-if="this.$store.state.islogin==true">{{this.$store.state.member.name}}님</b-nav-item>
            <b-nav-item v-if="this.$store.state.islogin==true" href='/MyPage'>마이페이지</b-nav-item>
            <span v-if="this.$store.state.islogin==true">
              <b-nav-item v-if="this.$store.state.member.email=='admin'" href='/admin'>관리자페이지</b-nav-item>
            </span>
            <b-nav-item v-if="this.$store.state.islogin==true" @click="showModal">로그아웃</b-nav-item>


            <b-modal ref="my-modal" hide-footer title="Using Component Methods">
              <div class="d-block text-center">
                <h3>로그아웃 하실건가요</h3>
              </div>
              <b-button @click="hideModal">네</b-button>
              <b-button @click="toggleModal">아니요</b-button>
            </b-modal>
          </b-navbar-nav>
        </b-col>

      </b-navbar>
    </b-row>

  </b-container>
</template>

<script>
  export default {
    name: 'header',
    data() {
      return {
        name: '',
        keyword: '',

      }
    },
    methods: {
      search(value) {
        if (this.$store.state.islogin == false) {
          this.$refs['my-modal'].show()
        } else {
          this.$router.push({
            name: 'Board',
            params: {
              id: '0',
              keyword: this.keyword
            }
          })
          console.log(this.keyword)
          this.$store.commit('setkeywordsearch', this.keyword)
          this.$store.commit('setctindex', 0)
          this.$router.go(this.$router.currentRoute);


        }

      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$store.dispatch('logout')
        this.$refs['my-modal'].hide()
      },
      toggleModal() {
        // We pass the ID of the button that we want to return focus to
        // when the modal has hidden
        this.$refs['my-modal'].toggle('#toggle-btn')
      }
    }
  }
</script>

<style scoped>

</style>