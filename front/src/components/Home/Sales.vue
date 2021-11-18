<template>
  <b-container <b-container fluid>
    <b-container fluid="md" class="p-4" style="height:600px;">

      <b-row align-v="center" style="height:100%">
        <b-col cols='7'>

          <h2>나도 판매하기</h2>
          <p>더이상 사용하지 않는 내 물품, 다른사람들에게는 필요할 수 있어요</p>
          <b-button @click="goboard()" class="m-2 " style="background-color:#ff8a3d; border-color:#fec69f;">
            판매 글쓰기
          </b-button>
        </b-col>

        <b-col cols='5'>
          <img src="../../assets/sales.png">
        </b-col>
      </b-row>

        <b-modal id="needLogin" ref="my-modal" centered hide-footer>
          <needLogin />
        </b-modal>

    </b-container>
  </b-container>
</template>

<script>
  import NeedLogin from '../Modal/NeedLogin';

  export default {
    name: 'sales',
        components: {
      NeedLogin
    },
    methods: {
      goboard() {
        if (this.$store.state.islogin == false) {
          this.$refs['my-modal'].show()
        } else {
          this.$router.push("/write");
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
</style>