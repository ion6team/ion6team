<template>
  <b-container fluid style="background-color:#e6f3e6">
    <b-container fluid="md" class="p-4" style="height:600px;">
      <b-row align-v="center" style="height:100%">

        <b-col cols='7'>
              <b-row>
<!--donghyun
                <b-col  v-for="(category,i) in categorylist" :key="i" cols='3'>
                 <a href="/board" @click="setCategoryId(category.id)">
                  {{category.name}}
                  </a>
                  </b-col>
                
-->

                
 <!-- <b-col  v-for="(category,i) in categorylist" :key="i" cols='3'> -->
     
     <b-col @click="$router.push({
          name:'Board',
          params:{
            id:category.id
            }
          })" v-for="(category,i) in categorylist" :key="i" cols='3'>
                              <ul style="list-style:none; display:flex; width:240px; margin:0;">
                                <li id="imgBack" style="">
                 <!-- <a href="/board" @click="setCategoryId(category.id)"> -->
          <img  v-bind:src= "'../../upload/category/'+category.icon" width="40px" height="40px" style="text-align:center; margin:10px 0;">
                 </li>
                                       <li  ><p class="categoryName" style="">{{category.name}}</p></li>
                    </ul>
         </b-col>
                  <!-- </a> -->
                  <!-- </b-col> -->
<!--
                <b-col  v-for="(category,i) in categorylist" :key="i" cols='3' >
                 <a href="/board" @click="setCategoryId(category.id)" style="background-color:blue;">
                    <ul style="list-style:none; display:flex; width:240px; margin:0;">
                      <li id="imgBack" style="">
                        <img  v-bind:src= "'../../upload/category/'+category.icon" width="40px" height="40px" style="text-align:center; margin:10px 0;">
                      </li>
                      <li  ><p class="categoryName" style="">{{category.name}}</p></li>
                    </ul>
                </a>
               </b-col>
-->
                
              </b-row>

        </b-col>

        <b-col cols='5'>
          <b-row>
            <b-col>
              <h2>카테고리별</h2>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <h2>검색하기</h2>
              <p>개인용품부터 사무용품까지 내가 필요한 물품만 모아보아요</p>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
    </b-container>
            <b-modal id="needLogin" ref="my-modal" centered hide-footer>
          <needLogin />
        </b-modal>
  </b-container>
</template>

<script>
  import axios from 'axios'
  import NeedLogin from '../Modal/NeedLogin';
  export default {
    name: 'category',
    components: {
      NeedLogin
    },
    data() {
      return {
        categorylist: [],
        id: '',
        category_id: '',
      }
    },
    props: {
      category_id: '',
    },
    mounted() {
      axios.get('/api/category', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        }
      }).then((res) => {
        this.categorylist = res.data;
      })
    },
    methods: {
      goboard() {
        if (this.$store.state.islogin == false) {
          this.$refs['my-modal'].show()
        } else {
          this.$router.push("/board");
        }
      }
    }
  }
</script>

<style scoped>
  h2 {
    font-size: 50px;
    
  }
  
  #imgBack{
    z-index: 2;
    height:60px;
    width:60px; 
    background-color:white; 
    border-radius:50%;
    border: 4px solid #56c271;
  }
  .categoryName:hover{
    background-color: #56c271;
    color: white;
  }
  .categoryName{
    width:140px; 
    padding:10px; 
    margin:10px; 
    margin-left:-15px; 
    background-color:white;
    border-radius: 0 5px 5px 0;
    border: 1px solid #56c271;
    transition: background-color 0.5s;
  }
  a{
    color: black;
    text-decoration: none;
  }
</style>