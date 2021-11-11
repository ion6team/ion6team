<template>
  <b-container>
    

            <b-form-file
            v-model="file1"
            :state="Boolean(file1)"
            placeholder="이미지파일을 업로드해주세요"
            drop-placeholder="Drop file here..."
            ></b-form-file>
            <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div>
           
            <div class="mb-3">
              <label for="address">장소</label>
              <input type="text" class="form-control" v-model="address" placeholder="서울특별시 강남구" readonly>
            </div>

            <div>
                <b-form-input v-model="text" placeholder="가격"></b-form-input>
            </div>

            <div>
                <b-form-input v-model="text" placeholder="상세설명"></b-form-input>
            </div>
            
  </b-container>
</template>

<script>
export default {
    name:'sales',
    data() {
      return {
        file1: null,
      }
    }
}
</script>

<style>
</style>