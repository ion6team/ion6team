<template>
  <b-container fluid style="background-color:#e6f3e6">
    <b-container fluid="md" class="p-4" style="height:600px;">
      <b-row align-v="center" style="height:100%">
        <b-col cols='5'>
          <div>
            <button v-for="(category,i) in categories" v-bind:key="i">{{categories[i].id}}{{categories[i].name}}</button>
            <!--카테고리 db받아서 for문 돌릴것 -->
          </div>
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
        categories: [],
      }
    },
    mounted() {
      axios.get("/api/category")
        .then(response => {
          this.categories = response.data
        })
    }
  }
</script>

<style>

</style>