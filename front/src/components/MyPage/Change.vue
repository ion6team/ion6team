<template>
   <div class="container">
    <div class="input-form-backgroud">
      
      <div class="input-form col-md-12 mx-auto">
        <div style="height:300px;" align-center>

        </div>
        <h4 class="mb-3">내정보수정</h4>
          
        <div class="row">

              <label for="address">우편번호</label>
              <div class="col-md-10 mb-3">
                <input type="text" class="form-control" v-model="address_num" placeholder="우편번호" readonly>
              </div>
              <div class="col-md-2 mb-3">
                <b-button class="ml-2" @click="addressApi()">검색</b-button>
              </div>

        </div>
            <div class="mb-3">
              <label for="address">주소</label>
              <input type="text" class="form-control" v-model="address" placeholder="서울특별시 강남구" readonly>

            </div>
            <div class="mb-3">
              <label for="address2">상세주소<span class="text-muted">&nbsp;(필수 아님)</span></label>
              <input type="text" class="form-control" v-model="address_detail" placeholder="상세주소를 입력해주세요.">
            </div>


            <div class="mb-3">
              <label for="phone">전화번호<span class="text-muted">&nbsp;('-' 없이 입력)</span></label>
              <input type="tel" class="form-control" v-model="phone" placeholder="전화번호를 입력해주세요.">
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

</div>

</div>
 </div>


  
</template>

<script>
import axios from 'axios';
export default {
    name:'change',
    data() {
      return {
        isDuplicateEmail: true,
        name: '',
        email: '',
        password: '',
        address_num: '',
        address: '',
        address_detail: '',
        phone: '',
        resident1: '',
        resident2: '',
        aggrement: '',
  
      }
      },
      addressApi() {
        new window.daum.Postcode({
          oncomplete: (
            data
          ) => { // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분. // 도로명 주소의 노출 규칙에 따라 주소를 조합한다. // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let fullRoadAddr = data.roadAddress; // 도로명 주소 변수 
            let extraRoadAddr =
              ''; // 도로명 조합형 주소 변수 // 법정동명이 있을 경우 추가한다. (법정리는 제외) // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다. 

            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
              extraRoadAddr += data.bname;
            } // 건물명이 있고, 공동주택일 경우 추가한다.

            if (data.buildingName !== '' && data.apartment === 'Y') {
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            } // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다. 
            if (extraRoadAddr !== '') {
              extraRoadAddr = ' (' + extraRoadAddr + ')';
            } // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다. 
            if (fullRoadAddr !== '') {
              fullRoadAddr += extraRoadAddr;
            } // 우편번호와 주소 정보를 해당 필드에 넣는다. 

            this.address_num = data.zonecode; //5자리 새우편번호 사용 
            this.address = fullRoadAddr;

          }
        }).open(this.$refs.embed)
      },

   mounted(){
     axios.put('/api/member', {
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
  .input-form {
    max-width: 680px;
    margin-top: 80px;
    padding: 32px;
    background: #fff;
    border: 4px double orange;
    box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
  }
</style>