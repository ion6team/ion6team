<template>
  <div
  class="py-5"
  style="background-color:white; height:100%; width:100%;" > 
    
        <div style="opacity:1;">
          <h3>{{name}}<span style="font-size:15px;"> 님</span></h3>
        
          <ul style="list-style:none; text-align:left;">
            <li><i class="material-icons md-16">email</i><span>{{email}}</span></li>
            <li><i class="material-icons md-16">place</i><span>{{address}}{{address_detail}}</span></li>
            <li><i class="material-icons md-16">phone</i><span>{{phone}}</span></li>
          </ul>
        </div>
      
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
  
  li{
    margin-bottom:10px;
  }
  .material-icons.md-16 { 
    font-size: 16px; 
    }

  span{
    font-size:18px;
    margin-left:10px;
  }
</style>