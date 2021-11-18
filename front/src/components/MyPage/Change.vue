<template>
  <div>
    <div v-if="!check">
       비밀번호 :
      <input v-model="password" type="password">
       <button @click="checkpassword()"> 확인 </button>
    </div>
    <div v-if="check">
      <div style="max-width:600px; margin:auto;">
        <b-form class="position-relative p-3" @submit.prevent="onSubmit">
          <b-form-group label="이름" label-for="form-name" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon icon="person-fill"></b-icon>
              </b-input-group-prepend>
              <b-form-input id="form-name" :disabled="busy"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group label="Email" label-for="form-mail" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">email</i>
              </b-input-group-prepend>
              <b-form-input id="form-email" type="email" :disabled="busy"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group label="전화번호" label-for="form-phonNum" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">phone</i>
              </b-input-group-prepend>
              <b-form-input id="form-phonNum" :disabled="busy"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group label="주소" label-for="form-address" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">place</i>
              </b-input-group-prepend>
              <b-form-input id="form-address" :disabled="busy"></b-form-input>
            </b-input-group>
            <b-form-input></b-form-input>
          </b-form-group>

          <div class="d-flex justify-content-center">
            <b-button ref="submit" type="submit" :disabled="busy">변경</b-button>
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
        check: false
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
           alert("error")
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
          if (this.counter < 20) {
            this.counter = this.counter + 1
          } else {
            this.clearInterval()
            this.$nextTick(() => {
              this.busy = this.processing = false
            })
          }
        }, 350)
      }
    }
  }
</script>

<style scoped>
  button {
    margin: 10px;
  }
</style>