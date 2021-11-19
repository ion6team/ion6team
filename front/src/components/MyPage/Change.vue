<template>
  <div>
    <div v-if="!check">
       비밀번호 :
      <input v-model="password" type="password">
       <button @click="checkpassword()"> 확인 </button>
       <br>
       {{message}}
    </div>
    <div v-if="check">
      <div style="max-width:600px; margin:auto;">
        <b-form class="position-relative p-3" @submit.prevent="onSubmit">
          <b-form-group label="이름" label-for="form-name" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon icon="person-fill"></b-icon>
              </b-input-group-prepend>
              <b-form-input v-model="name" id="form-name" :disabled="busy"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group label="Email" label-for="form-mail" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">email</i>
              </b-input-group-prepend>
              <b-form-input v-model="email" id="form-email" type="email" :disabled="busy"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group label="전화번호" label-for="form-phonNum" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">phone</i>
              </b-input-group-prepend>
              <b-form-input v-model="phone" id="form-phonNum" :disabled="busy"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group label="주소" label-for="form-address" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">place</i>
              </b-input-group-prepend>
              <b-form-input v-model="address" aria-invalid="" id="form-address" :disabled="busy" readonly="true"></b-form-input>
              <button @click="addressApi()">검색</button>
            </b-input-group>
            <b-form-input v-model="address_detail"></b-form-input>
          </b-form-group>

          <div class="d-flex justify-content-center">
            <b-button ref="submit" type="submit" :disabled="busy" @click="chagneinfo()">변경</b-button>
          </div>

          <b-overlay :show="busy" no-wrap @shown="onShown" @hidden="onHidden">
            <template #overlay>
              <div v-if="processing" class="text-center p-4 bg-primary text-light rounded">
                <b-icon icon="cloud-upload" font-scale="4"></b-icon>
                <div class="mb-3">Processing...</div>
                <b-progress min="1" max="20" :value="counter" variant="success" height="3px" class="mx-n4 rounded-0">
                </b-progress>
              </div>
              <div v-else ref="dialog" tabindex="-1" role="dialog" aria-modal="false"
                aria-labelledby="form-confirm-label" class="text-center p-3">
                <p><strong id="form-confirm-label">변경하시겠습니까?</strong></p>
                <div class="d-flex">
                  <b-button variant="outline-danger" class="mr-3" @click="onCancel">
                    취소
                  </b-button>
                  <b-button variant="outline-success" @click="onOK">확인</b-button>
                </div>
              </div>
            </template>
          </b-overlay>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
  export default {
    name: 'change',
    data() {
      return {
        busy: false,
        processing: false,
        counter: 1,
        interval: null,
        check: false,
        message:'',
        name: this.$store.state.member.name,
        email: this.$store.state.member.email,
        phone: this.$store.state.member.phone,
        address: this.$store.state.member.address,
        address_detail: this.$store.state.member.address_detail,
      }
    },
    beforeDestroy() {
      this.clearInterval()
    },
    methods: {
      checkpassword(){
         axios.post('/api/login', {
          email: this.$store.state.member.email,
          password: this.password,
        }).then((res)=>{
          if(res.status==200)
          this.check=true
        }).catch((err)=>{
           this.message='인증 실패';
      })
      },
      clearInterval() {
        if (this.interval) {
          clearInterval(this.interval)
          this.interval = null
        }
      },
      onShown() {
        // Focus the dialog prompt
        this.$refs.dialog.focus()
      },
      onHidden() {
        // In this case, we return focus to the submit button
        // You may need to alter this based on your application requirements
        this.$refs.submit.focus()
      },
      onSubmit() {
        this.processing = false
        this.busy = true
      },
      onCancel() {
        this.busy = false
      },
      onOK() {
        this.counter = 1
        this.processing = true
        // Simulate an async request
        this.clearInterval()
        this.interval = setInterval(() => {
          if (this.counter < 3) {
            this.counter = this.counter + 1
          } else {
            this.clearInterval()
            this.$nextTick(() => {
              this.busy = this.processing = false
            })
          }
        }, 100)

          axios.put('/api/member',{
              name: this.name,
              email: this.email,
              phone: this.phone,
              address: this.address,
              address_detail: this.address_detail
           }, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.$store.state.token
            }
          })
          .then((res) => {
            alert("수정됨")
          })
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
            } // 우편번호와 주소 정보를 해당 필드에 넣는다. ㄴ
 //5자리 새우편번호 사용 
            this.address = fullRoadAddr;

          }
        }).open(this.$refs.embed)
      },
    }
  }
</script>

<style scoped>
  button {
    margin: 10px;
  }
</style>