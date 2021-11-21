<template>
  <div>
        <h5>회원정보 변경</h5>
        <b-input-group>
            <b-row>
                <b-col sm="7">
                    <label type="id" for="name">아이디</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="name" type="text" v-model="id"></b-form-input>
                </b-col>
            </b-row>
             
            <b-row>
                <b-col sm="7">
                    <label type="text" for="name">이름</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="name" type="text" v-model="name"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="email" for="email">이메일</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="email" type="text" v-model="email"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="text" for="address">주소</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input  id="address" type="text" v-model="address" @click="addressApi()"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="text" for="address_detail">상세주소</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input  id="address_detail" type="text" v-model="address_detail"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="tel" for="phone">전화번호</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="phone" type="text" v-model="phone"></b-form-input>
                </b-col>
            </b-row>
            <b-row>
                <b-col sm="7">
                    <label type="tel" for="password">비밀번호</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="password" type="password" v-model="password"></b-form-input>
                </b-col>
            </b-row>

            
        </b-input-group>
        <button @click="putMember()">추가</button>
        <button> 취소</button>


    </div>
</template>

<script>
 import axios from 'axios';
export default {
    name : 'AdminChangeMember',

    props: {
      name: String ,
      email: String ,
      address : String ,
      address_detail: String ,
      phone :     Number ,
      password  : Number ,
      id: Number,

      
    },
     methods: {
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
            } // 우편번호와 주소 정보를 해당 필드에 넣는다. ㄴ
            //5자리 새우편번호 사용 
            this.address = fullRoadAddr;

          }
        }).open(this.$refs.embed)
      },
   putMember() {
       console.log(this.id);
        axios.put('/api/member/'+this.id, {
            name: this.name,
            email: this.email,
            password: this.password,
            phone: this.phone,
            address: this.address,
            address_detail: this.address_detail,
          
          }, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.$store.state.token
            }
          })
        
          .then((res) => {
         
              this.$router.go(0)
            
          })
    

        }
     }
}
</script>

<style>

</style>