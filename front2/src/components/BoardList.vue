<template>
  <div>
    <div>
      <h3 style="postion:flex;" align="left">{{myaddress}} 내 검색결과 </h3>
      <select class="form-control" v-model="selected">
        <option  :key="i" :value="d.id" v-for="(d, i) in options">{{ d.name }}</option>
      </select>
    </div>
    <div style="margin-top:10px; margin-right:0;" >
      <b-row>
      <b-col sm="4">
    <b-form-input type="text" width="100px" placeholder="검색어" v-model="keyword"></b-form-input>
    
      </b-col>
       <b-col lg="4" class="pb-2"><b-button @click="searchApi()">검색</b-button></b-col>
       </b-row>
    </div>

        <hr>
    <!-- <b-card-group class="cardlayout"> -->
    <div>
      <b-card @click="$router.push({
          name:'BoardPage',
          params:{
            id:board.id
            }
          })" v-for="(board,i) in list" :key="i" img-src="https://placekitten.com/300/300" img-alt="Card image"
        img-left title="" class="mb-3" style="border:1px solid #fec69f">
        <h4><b>{{board.title}}</b></h4>
        <template #footer>
          <b-link href='#'>
            <b-icon icon='chat-left-fill' class="m-1"></b-icon>
          </b-link>
          <b-icon icon='heart' class="m-1"></b-icon>
        </template>


        <table class="mx-5 my-3">
          <tr>
            <th style="width:20%">
              <b-card-text>가격 : </b-card-text>
            </th>
            <td style="width:30%">
              <b-card-text>{{board.price}}</b-card-text>
            </td>
            <th style="width:20%">
              <b-card-text>주소 : </b-card-text>
            </th>
            <td style="width:30%">
              <b-card-text>{{board.hopeaddress}}</b-card-text>
            </td>

          </tr>
          <tr>
            <th>
              <b-card-text>상세내용</b-card-text>
            </th>
            <td colspan="3"></td>
          </tr>
          <tr>

            <td colspan="4">
              <p style="height:50px; 
          overflow: hidden;
          text-overflow: ellipsis;">
                {{board.contents}}
              </p>
            </td>
          </tr>
        </table>
      </b-card>
    </div>
    <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center">
    </b-pagination>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        myarea: '',
        keyword: '',
        rows: '',
        perPage: 5,
        currentPage: null,
        list: [],
        pagecount: 1,
        totalpage: 0,
        selected: 1,
        options: []
      }
    },
    mounted() {
      this.currentPage = 1

      var arr = this.$store.state.member.address.split(' ')
      this.myaddress = arr[0] + ' ' + arr[1]

      axios.get('/api/category', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        },
      }).then((res) => {
        console.log(res.data);
        this.options = res.data
      })
    },
    methods: {
      loadApi() {
        const page = this.currentPage - 1
        axios.get('/api/board?page=' + page + '&size=' + this.perPage + '&category_id=' + this.selected + '&keyword=' + this.keyword, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.$store.state.token
            },
          })
          .then(response => {
            this.list = response.data.content
            this.rows = response.data.totalElements
          })
      },
      searchApi(){
        this.loadApi();
      }
    },
    watch: {
      currentPage() {
        this.loadApi()
      },
      selected(){
        this.keyword=''
        this.loadApi()
      }
    }

  }
</script>

<style>
  tr,
  th,
  td {
    text-align: left;
    padding: 10px;
  }
</style>