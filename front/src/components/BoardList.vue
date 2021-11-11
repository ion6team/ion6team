<template>
  <div>
    <b-card-group class="cardlayout">
      <b-card @click="$router.push({
          name:'BoardPage',
          params:{
            id:board.id
            }
          })" v-for="(board,i) in list" :key="i" class="list-group">
        <b-link href='#'>
          <b-icon icon='chat-left-fill'></b-icon>
        </b-link>
        <b-icon icon='heart'></b-icon>
        <!-- 클릭시 icon='heart-fill 로 변경하게 해야함-->
        <b-card-text>가격 : {{board.price}}</b-card-text>
        <b-card-text>주소 : {{board.hope_address}}</b-card-text>
        <b-card-text>상세내용 : {{board.contents}}</b-card-text>
      </b-card>
      <!-- <p>{{$store.state.boards[0]}}</p> -->
    </b-card-group>

    <button v-on:click="pagecount--" @click="getList"> &lt; </button>
    <span v-on:click="pagecount=i" @click="getList" class="page_index" v-for="i in parseInt(totalpage)" :key="i">
      {{i}}
    </span>
    <button v-on:click="pagecount++" @click="getList"> &gt; </button>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        list: [],
        pagecount: 1,
        totalpage: 0,
      }
    },
    mounted() {
      axios.get("http://localhost:8080/api/board?page=" + (this.pagecount - 1),{
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ this.$store.state.token
          }
        })
        .then((res) => {
          //console.log(res.data.content[0]);
          this.list = res.data.content;
          this.totalpage = res.data.totalPages;
        })
    },
    methods: {
      getList() {
        if (this.pagecount < 1) this.pagecount = 1;
        axios.get("http://localhost:8080/api/board?page=" + (this.pagecount - 1),{
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ this.$store.state.token
          }
        })
          .then((res) => {
            this.list = res.data.content;
          })
      },


    }

  }
</script>

<style>
  /* .cardlayout{
  position: relative;
} */
</style>