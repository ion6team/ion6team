<template>
  <b-container>
            <div>
              <div class="mt-3">사진선택</div>
              <b-form-file
              v-model="file1"
              :state="Boolean(file1)"
              placeholder="이미지파일을 업로드해주세요"
              drop-placeholder="Drop file here..."
              ></b-form-file>
            </div>

            <div>
                <b-form-input v-model="text" placeholder="가격"></b-form-input>
            </div>

            <div>
                <div class="mt-3">지역선택</div>
                 <b-form-select v-model="selected" :options="sido"></b-form-select>
                 <b-form-select v-model="selected" :options="gugun"></b-form-select>
                <b-button class="bg-primary m-2 ">
                우리동네
                </b-button>
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

<style scoped>

</style>