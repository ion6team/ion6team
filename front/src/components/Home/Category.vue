<template>
  <b-container fluid style="background-color:#e6f3e6">
    <b-container fluid="md" class="p-4" style="height:600px;">
      <b-row align-v="center" style="height:100%">
        <b-col cols='5'>
              <b-row>
<!--donghyun
                <b-col  v-for="(category,i) in categorylist" :key="i" cols='3'>
                 <a href="/board" @click="setCategoryId(category.id)">
                  {{category.name}}
                  </a>
                  </b-col>
                

-->

                
                <b-col  v-for="(category,i) in categorylist" :key="i" cols='3'>
                 <a href="/board" @click="setCategoryId(category.id)">
                  {{category.name}}
                  </a>
                  </b-col>
                
              </b-row>
        </b-col>

        <b-col cols='7'>
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
  </b-container>
</template>

<script>
import axios from 'axios'
  export default {
    name: 'category',
    data() {
      return {
        categorylist: [],
        id:'',
        category_id:'',
      }
    },
    props:{
category_id:'',
    },
    mounted(){
      axios.get('/api/category', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
          }
        }).then((res)=>
        {
          this.categorylist = res.data;
        })
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