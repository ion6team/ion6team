<template>
  <div style="text-align:center;">
    <h5 style="padding: 20px 0;"><b>비밀번호찾기</b></h5>
    <b-input-group>
      <table>

      <tr>
        <th>
          <label type="text" for="id">아이디</label>
        </th>
        <td>
          <b-form-input id="id" type="text"></b-form-input>
        </td>
      </tr>

      <tr>
        <th>
          <label type="text" for="name">이름</label>
        </th>
        <td>
          <b-form-input id="name" type="text"></b-form-input>
        </td>
      </tr>

      <tr>
        <th>
          <label type="text" for="identity">주민등록번호</label>
        </th>
        <td>
          <b-form-input  id="identity" type="text"></b-form-input>
          
          </td>

          <td>-</td>

          <td>
          <b-form-input v-model="resident2" id="identity" type="password"></b-form-input>
        </td>
      </tr>
      </table>
    
    </b-input-group>
    <button  class="btn btn-primary my-2" style="background-color:#ff8a3d; border-color:#fec69f;">검색</button>


  </div>
</template>

<script>
export default {};
</script>

<style scoped>

</style>