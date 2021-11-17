<template>
  <div>
    <h5>카테고리 추가</h5>
    <b-input-group>
      <b-row>
        <b-col sm="7">
          <label type="text" for="name">카테고리 명</label>
        </b-col>
        <b-col sm="5">
          <b-form-input v-model="name" id="name" type="text"></b-form-input>
        </b-col>
      </b-row>

      <b-row>
        <b-col sm="7">
          <label type="text" for="identity">카테고리 한글 설명</label>
        </b-col>
        <b-col sm="5">
          <b-form-input v-model="resident1" id="identity" type="text"></b-form-input>
        </b-col>
      </b-row>
    <span v-if="email!=''">
      <h5 v-if="email=='notfound'"> 이메일을 찾지 못했습니다. </h5>
      <h5 v-else> 당신의 이메일은 {{email}} 입니다. </h5>
    </span>
    </b-input-group>
    <button @click="findId()">검색</button>


  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'findeEmail',
    data() {
      return {
        name: '',
        resident1: '',
        resident2: '',
        email: '',
      }
    },
    methods: {
      findId() {
        axios.post('/api/member/email', {
          name: this.name,
          resident1: this.resident1,
          resident2: this.resident2,
        }).then((res)=>{

            this.email= res.data
            if(res.data=='null')
              this.email='null'

        })
      }
    }
  }
</script>

<style scoped>

</style>