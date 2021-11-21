<template>
  <div style="text-align:center">
    <h5 style="padding: 20px 0;"><b>아이디찾기</b></h5>
    <b-input-group>
      <table>
      <tr>
        <th>
          <label type="text" for="name">이름</label>
        </th>
        <td>
          <b-form-input v-model="name" id="name" type="text"></b-form-input>
        </td>
      </tr>

      <tr>
        <th>
          <label type="text" for="identity">주민등록번호</label>
        </th>
        <td>
          <b-form-input v-model="resident1" id="identity" type="text"></b-form-input>
          
          </td>

          <td>-</td>

          <td>
          <b-form-input v-model="resident2" id="identity" type="password"></b-form-input>
        </td>
      </tr>
      </table>
    <span v-if="email!=''">
      <h5 v-if="email=='notfound'"> 이메일을 찾지 못했습니다. </h5>
      <h5 v-else> 당신의 이메일은 {{email}} 입니다. </h5>
    </span>
    </b-input-group>
    <button @click="findId()" class="btn btn-primary my-2" style="background-color:#ff8a3d; border-color:#fec69f;">검색</button>


  </div>
</template>

<script>

  import FindPassword from './FindPassword.vue';
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
table,tr,th,td{
  padding:5px;
}
table{
  margin:auto;
}
</style>