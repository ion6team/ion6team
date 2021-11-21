<template>
  <div style="text-align:center">
    <h5 style="padding: 20px 0;"><b>새로운 비밀번호를 입력해주세요</b></h5>
    <b-input-group>
      <table>
      <tr>
        <th>
          <label type="text" for="password">새 비밀번호</label>
        </th>
        <td>
          <b-form-input id="password" type="text"></b-form-input>
        </td>
      </tr>

      <tr>
        <th>
          <label type="text" for="password">비밀번호 확인</label>
        </th>
        <td>
          <b-form-input id="password" type="text"></b-form-input>
        </td>
      </tr>

      </table>
    
    </b-input-group>
    <button class="btn btn-primary my-2" style="background-color:#ff8a3d; border-color:#fec69f;">확인</button>


  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>