<template>
  <div>
    <div>
      <b-card @click="$router.push({
          name:'BoardPage',
          params:{
            id:board.id
            }
          })" v-for="(board,i) in list" :key="i"  title="" class="mb-3"
        style="border:1px solid #fec69f; max-width: 22rem; min-height:450px;">
        <!-- <img src="../assets/1373b807-50c9-43a7-837f-fb3b2c5e34ce_dog5.jpg" />
        <img src="`../assets/${board.filename}`" />  -->
        <img class="product-thumb" v-bind:src="'../../upload/'+board.filepath1" />
        <h4><b>{{board.title}}</b></h4>
        
        <table >
          <tr >
            <th style="width:30%; ">
              <b-card-text>가격 : </b-card-text>
            </th>
            <td style="width:70%; ">
              <b-card-text>{{board.price}}</b-card-text>
            </td>
          </tr>
          <tr>
            <th style="width:30%; height:60px; ">
              <b-card-text>주소 : </b-card-text>
            </th>
            <td style="width:70%">
              <b-card-text>{{board.hopeaddress}}</b-card-text>
            </td>

          </tr>
          <tr>
            <th>
              <b-card-text>상세내용</b-card-text>
            </th>
            </tr>
            <tr>
            <td colspan="2">
              <p style=" 
          overflow: hidden;
          text-overflow: ellipsis;">
                <span v-html="board.contents"></span>
              </p>
            </td>
          </tr>
        </table>
      </b-card>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    name: 'zzim',
    data() {
      return {
        list: [],
      }
    },
    mounted() {
      axios.get('/api/member/zzim', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        },
      }).then((res) => {
        console.log(res.data);
        this.list = res.data
      })
    }
  }
</script>

<style>

</style>