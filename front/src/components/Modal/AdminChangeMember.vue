<template>
  <div>
        <h5>회원정보 변경</h5>
          <validation-observer ref="observer" v-slot="{ handleSubmit }">

          <b-form @submit.stop.prevent="handleSubmit(onSubmit)">
        <b-input-group>
             
           <validation-provider name="name" :rules="{ required: true,regex: /^[가-힣]{2,4}$/ }" v-slot="validationContext">
          <b-form-group label="이름" label-for="form-name" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon icon="person-fill"></b-icon>
              </b-input-group-prepend>
              <!-- <b-form-input v-model="name" id="form-name" :disabled="busy"></b-form-input> -->
               <b-form-input id="example-input-3" name="example-input-2" v-model="name" placeholder="김당근" :disabled="busy"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  style="border-color:#fec69f;">
                </b-form-input>

            </b-input-group>
          </b-form-group>
              </validation-provider>

            <validation-provider name="email" rules="required|email" v-slot="validationContext">
          <b-form-group label="Email" label-for="form-mail" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">email</i>
              </b-input-group-prepend>
            
              <!-- <b-form-input v-model="email" id="form-email" type="email" :disabled="busy"></b-form-input> -->
                <div class="col-md-9-mb-3">
              <b-form-input id="example-input-1" name="example-input-1" v-model="email" :disabled="busy"
                      placeholder="you@example.com" :state="getValidationStateEmail(validationContext)"
                      aria-describedby="input-1-live-feedback"
                      style="border-color:#fec69f;">
                    </b-form-input>

                     <div class="col-md-3 mb-3">
                    <b-button class="ml-2" @click="checkEmailDuplicate()" style="background-color:#ff8a3d; border-color:#fec69f;">중복확인</b-button>
                  </div>

                    <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                  </b-form-invalid-feedback>
                </div>
           </b-input-group>
          </b-form-group>
</validation-provider>

<validation-provider name="password" :rules="{ required: true  }" v-slot="validationContext">
              <b-form-group id="example-input-group-2" label="비밀번호*" label-for="example-input-1">
                <b-form-input id="example-input-2" name="example-input-2" v-model="password" :disabled="busy"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  type="password" style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>

              </b-form-group>
            </validation-provider>
     


            <validation-provider name="passwordcheck" rules="required|confirmed:password"  v-slot="validationContext">
              <b-form-group id="example-input-group-2" label="비밀번호 확인*" label-for="example-input-1" >
                <b-form-input id="example-input-2" name="example-input-2" v-model="passwordcheck"  :disabled="busy"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  type="password" style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>

              </b-form-group>
 </validation-provider>

            <validation-provider name="phone" :rules="{required: true, min: 11, max: 11 }" v-slot="validationContext">
          <b-form-group label="전화번호" label-for="form-phonNum" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">phone</i>
              </b-input-group-prepend>
              <!-- <b-form-input v-model="phone" id="form-phonNum" :disabled="busy"></b-form-input> -->
               
                <b-form-input id="example-input-2" name="example-input-2" v-model="phone" placeholder="휴대폰번호"  type="number" :disabled="busy"
                  :state="getValidationState(validationContext)" aria-describedby="input-2-live-feedback"
                  style="border-color:#fec69f;">
                </b-form-input>

                <b-form-invalid-feedback id="input-1-live-feedback">{{ validationContext.errors[0] }}
                </b-form-invalid-feedback>
            </b-input-group>
          </b-form-group>
 </validation-provider>

        
          <b-form-group label="주소" label-for="form-address" label-cols-lg="2">
            <b-input-group>
              <b-input-group-prepend is-text>
                <i class="material-icons">place</i>
              </b-input-group-prepend>
              <b-form-input v-model="address" aria-invalid="" id="form-address" :disabled="busy" readonly="true">
              </b-form-input>
              <button type="button" @click="addressApi()">검색</button>
            </b-input-group>
            <b-form-input v-model="address_detail"></b-form-input>
          </b-form-group>

        </b-input-group>
       <b-button variant="outline-danger" class="mr-3" @click="onCancel">
                    취소
                  </b-button>
   <b-button variant="outline-success" @click="onOK">확인</b-button>

   </validation-observer>
    </div> 
</template>

<script>
export default {

}
</script>

<style>

</style>