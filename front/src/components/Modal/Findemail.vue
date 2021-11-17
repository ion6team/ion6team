<template>
  <div>
      <h5>아이디찾기</h5>
    <b-input-group>
      <b-row>
      <b-col sm="3">
        <label type="text" for="name">이름</label>
      </b-col>
      <b-col sm="9">
        <b-form-input id="name" type="text"></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col sm="3">
        <label type="text" for="identity">주민등록번호</label>
      </b-col>
      <b-col sm="9">
        <b-form-input id="identity" type="text"></b-form-input>
      </b-col>
    </b-row>

    </b-input-group>
    <button>검색</button>


  </div>
</template>

<script>


export default {
  name:'findeEmail',
  }
  
</script>

<style scoped>

</style>