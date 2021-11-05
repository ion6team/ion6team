<template>
  <div class="container">
    <div class="input-form-backgroud">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">회원가입</h4>
        <form class="validation-form" novalidate>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" v-model="email" placeholder="you@example.com" >
            <div class="invalid-feedback"> 이메일을 입력해주세요. </div>

            <div col-md-6 mb-3>
              <button type="button" @click="checkEmailDuplicate"> 중복체크 </button>
              {{isDuplicateEmail}}
            </div>

          </div>
        </div>
        <div class="col-md-6 mb-3">
          <label for="password">비밀번호</label>
          <input type="password" class="form-control" v-model="password"  required>
          <div class="invalid-feedback"> 비밀번호를 입력해주세요. </div>
        </div>
        <div class="mb-3">
          <label for="name">이름</label>
          <input type="text" class="form-control" v-model="name" placeholder="김당근" value="" required>
          <div class="invalid-feedback"> 이름을 입력해주세요. </div>
        </div>
        <div class="mb-3">
          <label for="address">우편번호</label>
          <input type="text" class="form-control" v-model="address_num" placeholder="우편번호" readonly>
          <button @click="addressApi"> 주소 검색 </button>
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
          <label for="resident">주민등록번호</label>
          <div class="col-md-6 mb-3">
            <input type="text" class="form-control" v-model="resident1" placeholder="앞자리">
          </div>
          <div class="col-md-6 mb-3">
            <input type="password" class="form-control" v-model="resident2" placeholder="뒷자리">
          </div>
        </div>
        <hr class="mb-4">
        <div class="custom-control custom-checkbox">
          <input type="checkbox" class="custom-control-input" v-model="aggrement" required>
          <label class="custom-control-label" for="aggrement"> &nbsp;개인정보 수집 및 이용에 동의합니다.</label>
        </div>
        <div class="mb-4"></div>
        <button class="btn btn-primary btn-lg btn-block" @click="join">가입 완료</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {


    name: 'join',

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
        errors: [],
      }
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
            } // 우편번호와 주소 정보를 해당 필드에 넣는다. 

            this.address_num = data.zonecode; //5자리 새우편번호 사용 
            this.address = fullRoadAddr;

          }
        }).open(this.$refs.embed)
      },

      ValidationCheck() {
        this.errors = [];
        if (!this.name) {
          this.errors.push("이름을 입력해주세요")
        }
      },
      checkEmailDuplicate() {
        if (this.email == '') {
          alert("이메일을 입력해주세요")
        } else {

          axios({
            url: '/api/member/' + this.email,
            method: 'get',
          }).then(response => {
            if (response.data == false) {
              alert("사용가능한 이메일 입니다")

            } else {
              alert("사용중인 이메일입니다. 바꿔주세요")
            }
            this.isDuplicateEmail = response.data
          })

        }
      },
      join() {

        if (this.errors.length == 0) {
          axios.post('/api/member/new', {
              name: this.name,
              email: this.email,
              password: this.password,
              address: this.address,
              address_detail: this.address_detail,
              phone: this.phone,
              resident1: this.resident1,
              resident2: this.resident2,
            })
            .then(response => {
              if (response.status == 200) {
                alert("회원가입 성공 로그인페이지로 이동합니다.")
                this.$router.push('/login')
              } else {
                alert("회원가입 실패")
              }
            })
        } else {
          alert(this.errors)
        }
      }

    }
  }
</script>

<style>
  .container {
    min-height: 100vh;
  }

  .input-form {
    max-width: 680px;
    margin-top: 80px;
    padding: 32px;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
  }
</style>