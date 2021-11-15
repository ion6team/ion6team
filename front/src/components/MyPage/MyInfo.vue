<template>
  <div class="container">
    <div class="input-form-backgroud">
      
      <div class="input-form col-md-12 mx-auto">
        <div style="height:300px;" align-center>
        </div>
        <h4 class="mb-3">내정보</h4>
          <div class="row">
            <label for="address">이메일</label>
             <div class="col-md-3 mb-4">
               <div class="card" style="width: 38rem;">
                  <ul class="list-group list-group-flush">
                     <li class="list-group-item"> 
                      {{email}} </li>
                   </ul>
              </div>
             </div>
           </div>
           <div class="row">
            <label for="address">이름</label>
             <div class="col-md-3 mb-4">
               <div class="card" style="width: 38rem;">
                  <ul class="list-group list-group-flush">
                     <li class="list-group-item"> 
                      {{name}}</li>
                   </ul>
              </div>
             </div>
           </div>
           <div class="row">
             <label for="address">주소</label>
             <div class="col-md-3 mb-4">
               <div class="card" style="width: 38rem;">
                  <ul class="list-group list-group-flush">
                     <li class="list-group-item"> 
                     {{address}}</li>
                   </ul>
              </div>
             </div>
           </div>
           <div class="row">
              <label for="address">상세주소</label>
             <div class="col-md-3 mb-4">
               <div class="card" style="width: 38rem;">
                  <ul class="list-group list-group-flush">
                     <li class="list-group-item"> 
                      {{address_detail}}</li>
                   </ul>
              </div>
             </div>
           </div>
           <div class="row">
                <label for="address">전화번호</label>
             <div class="col-md-3 mb-4">
               <div class="card" style="width: 38rem;">
                  <ul class="list-group list-group-flush">
                     <li class="list-group-item"> 
                      {{phone}}</li>
                   </ul>
              </div>
             </div>
           </div>
  <div class="row">
     <label for="address">주민등록번호</label>
  <div class="col-sm-6">
    <div class="card" style="width: 18rem;">
      {{resident1}}
    
        
       
        
    
    </div>
  </div>
  <div class="col-sm-6 mb-4">
    <div class="card">
     
       *******
        
     
    </div>
  </div>
</div>
       
            
           
       <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#"> <b-button class="btn btn-primary btn-lg btn-block" type="submit" variant="primary">위로
            </b-button></a>
        </div>     
      </div>
           </div>
   
 


    
 

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
  .container {
    min-height: 100vh;
    margin-bottom: 80px;
  }
</style>