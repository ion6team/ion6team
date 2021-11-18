<template>
  <div
  class="py-5" id='profileBG'> 
    <b-row align-h="center">
        <b-col cols="3">
          <h3>{{name}}<span style="font-size:15px;"> 님</span></h3>
        </b-col>
        <b-col cols=4>
          <ul style="list-style:none; text-align:left;">
            <li><i class="material-icons">email</i>{{email}}</li>
            <li><i class="material-icons">place</i>{{address}}{{address_detail}}</li>
            <li><i class="material-icons">phone</i>{{phone}}</li>
          </ul>
        </b-col>
      </b-row>
    </div>
    
 
  
</template>

<script>
import axios from 'axios'
export default {
    name:'myInfo',
     data() {
      return {
        name: '',
        email: '',
        address: '',
        address_detail: '',
        phone: '',
        resident1: '',
  
      }
      },

   mounted(){
     axios.get('/api/member', {
           headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ this.$store.state.token
          }
            })
             .then((res)=>{
              this.name=res.data.name,
              this.email=res.data.email,
              this.address=res.data.address,
              this.address_detail=res.data.address_detail,
              this.phone=res.data.phone,
              this.resident1=res.data.resident1
             })
             
            
     },
}
</script>

<style scoped>
  #profileBG{
    background-image:url(../../assets/profileBG.jpeg);
    background-repeat:no-repeat;
    background-size:cover;
    min-height:300px;
  }
  p{
    margin:0;
  }
</style>