<template>
  <div class="container">
    <div class="input-form-backgroud">

      <div class="input-form col-md-12 mx-auto">

        <div style="height:250px;" align-center>
          <div>
            <b-link href='/'>
              <img src='../assets/Daangeun_Logo_vertical_Color_RGB.png'>
            </b-link>
          </div>
        </div>

        <hr style="background-color:#ff8a3d;">

        <h4 class="my-5" style="color:#ff8a3d;"><b>회원가입</b></h4>

        <validation-observer ref="observer" v-slot="{ handleSubmit }">

          <b-form @submit.stop.prevent="handleSubmit(onSubmit)">


            <validation-provider name="email" rules="required|email" v-slot="validationContext">
              <b-form-group id="example-input-group-1" label="이메일*" label-for="example-input-1">
                <div class="row">
                  <div class="col-md-9 mb-3">
                    <b-form-input id="example-input-1" name="example-input-1" v-model="email"
                      placeholder="you@example.com" :state="getValidationStateEmail(validationContext)"
                      aria-describedby="input-1-live-feedback"
                      style="border-color:#fec69f;">
                    </b-form-input>
                  </div>
                  <div class="col-md-3 mb-3">
                    <b-button class="ml-2" @click="checkEmailDuplicate()" style="background-color:#ff8a3d; border-color:#fec69f;">중복확인</b-button>
                  </div>

                  <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                  </b-form-invalid-feedback>
                </div>
              </b-form-group>
            </validation-provider>

<br>
<br>

            <validation-provider name="password" :rules="{ required: true  }" v-slot="validationContext">
              <b-form-group id="example-input-group-2" label="비밀번호*" label-for="example-input-1">
                <b-form-input id="example-input-2" name="example-input-2" v-model="password"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  type="password" style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>

              </b-form-group>
            </validation-provider>
     


            <validation-provider name="passwordcheck" rules="required|confirmed:password"  v-slot="validationContext">
              <b-form-group id="example-input-group-2" label="비밀번호 확인*" label-for="example-input-1" >
                <b-form-input id="example-input-2" name="example-input-2" v-model="passwordcheck" 
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  type="password" style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>

              </b-form-group>
            </validation-provider>

<br>
<br>


            <validation-provider name="name" :rules="{ required: true, min: 2, max: 10 }" v-slot="validationContext">
              <b-form-group id="example-input-group-3" label="이름*" label-for="example-input-1">

                <b-form-input id="example-input-3" name="example-input-2" v-model="name" placeholder="김당근"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>
              </b-form-group>
            </validation-provider>

<br>
<br>

            <div class="row">

              <label for="address">우편번호*</label>
              <div class="col-md-10 mb-3">
                <input type="text" class="form-control" v-model="address_num" placeholder="우편번호" readonly style="background-color:#fbf7f2; border-color:#fec69f;">
              </div>
              <div class="col-md-2 mb-3">
                <b-button class="ml-2" @click="addressApi()" style="background-color:#ff8a3d; border-color:#fec69f;">검색</b-button>
              </div>

            </div>

            <div class="mb-3">
              <label for="address">주소*</label>
              <input type="text" class="form-control" v-model="address" placeholder="서울특별시 강남구" readonly style="background-color:#fbf7f2; border-color:#fec69f;">

            </div>
            <div class="mb-3">
              <label for="address2">상세주소<span class="text-muted">&nbsp;(필수 아님)</span></label>
              <input type="text" class="form-control" v-model="address_detail" placeholder="상세주소를 입력해주세요." style="border-color:#fec69f;">
            </div>

<br>
<br>

            <validation-provider name="phone" :rules="{ required: true}" v-slot="validationContext">
              <b-form-group id="example-input-group-2" label="휴대폰*" label-for="example-input-1">

                <b-row>
                  <b-col cols="2">
                    <b-form-input id="example-input-2" name="example-input-2" v-model="phone" type="number"
                      :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                      style="border-color:#fec69f;">
                    </b-form-input>
                  </b-col>
                  <b-col cols="5">
                    <b-form-input id="example-input-2" name="example-input-2" v-model="phone"  type="number"
                      :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                      style="border-color:#fec69f;">
                    </b-form-input>
                  </b-col>
                  <b-col cols="5">
                    <b-form-input id="example-input-2" name="example-input-2" v-model="phone"  type="number"
                      :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                      style="border-color:#fec69f;">
                    </b-form-input>
                  </b-col>
                </b-row>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>
              </b-form-group>
            </validation-provider>


<br>
<br>

            <div class="row">
              <label for="resident">주민등록번호*</label>
              <div class="col-md-6 mb-3">
              
            <validation-provider name="resident1" :rules="{ required: true,regex:/([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/}" v-slot="validationContext">
              <b-form-group id="example-input-group-2"  label-for="example-input-1">

                <b-form-input id="example-input-2" name="example-input-2" v-model="resident1" placeholder="앞자리" type="number"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>
              </b-form-group>
            </validation-provider>

                
               
               
              </div>
              <div class="col-md-6 mb-3">
                         <validation-provider name="resident2" :rules="{ required: true,regex:/([1-4]{1}\d{6})/}" v-slot="validationContext">
              <b-form-group id="example-input-group-2"  label-for="example-input-1">

                <b-form-input id="example-input-2" name="example-input-2" v-model="resident2" placeholder="뒷자리" type="number"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>
              </b-form-group>
            </validation-provider>
              </div>
            </div>



            <hr class="mb-4" style="border-color:#fbf7f2;" >


            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" v-model="aggrement" required>
              <label class="custom-control-label" for="aggrement"> &nbsp;개인정보 수집 및 이용에 동의합니다.</label>
            </div>
            <div class="mb-4"></div>
            <b-button class="btn btn-primary btn-lg btn-block" type="submit"
            style="background-color:#ff8a3d; border-color:#fec69f;" 
            >회원 가입
            </b-button>

          </b-form>
        </validation-observer>
      </div>
    </div>
    <b-modal id="joinComplete" ref="join-modal" centered hide-footer>
      <join-complete />
    </b-modal>
  </div>
</template>

<script>
  import axios from 'axios';
  import JoinComplete from '../components/Modal/JoinComplete.vue'
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

      }
    },
    components: {
      JoinComplete
    },
    methods: {
      
      getValidationStateEmail({
        dirty,
        validated,
        valid = null,
      }) {
        return dirty || validated ? valid : null;
      },

      getValidationState({
        dirty,
        validated,
        valid = null,
      }) {
        return dirty || validated ? valid : null;
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
      onSubmit() {
        if (this.isDuplicateEmail == true) {
          alert("이메일 중복 체크를 해주세요")
        }  
   else 
   {
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
                this.$refs['join-modal'].show()
              } else {
                alert("회원가입 실패")
              }
            })

        }
      }
    }
  }
</script>

<style>
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