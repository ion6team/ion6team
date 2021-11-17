<template>
  <div>
      <h5>비밀번호찾기</h5>
    <b-input-group>
        <b-row>
      <b-col sm="3">
        <label type="text" for="name">아이디</label>
      </b-col>
      <b-col sm="9">
        <b-form-input id="name" type="text"></b-form-input>
      </b-col>
    </b-row>

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
        <label type="number" for="identity">주민등록번호</label>
      </b-col>
      <b-col sm="4">
        <b-form-input id="identity" type="number"></b-form-input>
      </b-col>
      -
            <b-col sm="4">
        <b-form-input id="identity" type="number"></b-form-input>
      </b-col>
    </b-row>

    </b-input-group>
    <button>검색</button>

  </div>
</template>

<script>
export default {};
</script>

<style scoped>

</style>