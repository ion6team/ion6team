<template>
  <div>
    <div style="margin:10px 50px 0;">
      <b-card
       @click="$router.push({
          name:'BoardPage',
          params:{
            id:board.id
            }
          })" v-for="(board,i) in list" :key="i"  title="" class="mb-3"
        style="border:1px solid #fec69f; max-width: 40rem;">
        <!-- <img src="../assets/1373b807-50c9-43a7-837f-fb3b2c5e34ce_dog5.jpg" />
        <img src="`../assets/${board.filename}`" />  -->
        <b-row >
          <b-col cols='2'>
            <img class="product-thumb" v-bind:src="'../../upload/'+board.filepath1" style="width:100px;" />
          </b-col>
          <b-col cols='10'>
            <h5><b>{{board.title}}</b></h5>
            
            <table>
              <tr >
                <th style="width:20%;">
                  <b-card-text>가격 : </b-card-text>
                </th>
                <td style="width:15%; ">
                  <b-card-text>{{board.price}}</b-card-text>
                </td>       
                <th style="width:15%; ">
                  <b-card-text>주소 : </b-card-text>
                </th>
                <td style="width:50%">
                  <b-card-text>{{board.hopeaddress}}</b-card-text>
                </td>
              </tr>
              
            </table>
          </b-col>
        </b-row>
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