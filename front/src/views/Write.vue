
<template>
  <b-container style="width:600px; margin-top:50px;">

    <div class="mb-3">
      <label for="title">제목</label>
      <div>
        <b-form-input v-model="title" placeholder="제목을 입력하세요."></b-form-input>
      </div>
    </div>
    <!--
            <b-form-file
            v-model="file1"
            :state="Boolean(file1)"
            placeholder="이미지파일을 업로드해주세요"
            drop-placeholder="Drop file here..."
            ></b-form-file>
            <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div>
           -->
    <div class="mb-3">
      <label for="address">장소</label>
      <b-checkbox id="checkbox-1" v-model="hope_address" name="checkbox-1" value="accepted"
        unchecked-value="not_accepted">
        <label for="address">거래 지역 변경</label>
      </b-checkbox>
      <input v-if="hope_address=='not_accepted'"  class="form-control" v-model="defaultaddress" readonly>
      <input v-if="hope_address=='accepted'"  class="form-control" placeholder="거래 희망 지역">
    </div>

    <label for="address">가격</label>
    <div>
      <b-form-input v-model="price" placeholder="가격"></b-form-input>
    </div>
    <p />
    <label for="address">상세 설명</label>
    <div>
      <b-form-input v-model="contents" placeholder="상세설명"></b-form-input>
    </div>
    <div>

  <form> <input type="file" name="photo" id="photo" /> </form>



  </div>
    
    <button class="btn btn-primary btn-lg btn-block" @click="write_board()" >작성 완료</button>



  </b-container>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'write',
    data() {
      return {
        title: '',
        price: 0,
        file1: null,
        hope_address: 'not_accepted',
        contents: '',
        defaultaddress: '',
        category_id: 1,
        image : '',
        FormData : null
      }
    },
    mounted() {
      axios.get('/api/member/address', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        }
      }).then((response) => {

        this.defaultaddress = response.data;
      })
    },
    
    
  
  methods: {

write_board() {
      var frm = new FormData(); 
      var photoFile = document.getElementById("photo"); 
      var data ={
        "title" : this.title,
        "contents" : this.contents,
        "category_id": "1",
        "price": this.price,
        "hopeaddress": this.defaultaddress,
      }
      frm.append('data', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
      frm.append("file", photoFile.files[0]); 
      axios.post('http://localhost:8080/api/board', frm, 
      { 
        headers: 
       { 
         'Content-Type': 'multipart/form-data' , 
          'Authorization': 'Bearer ' + this.$store.state.token
       } 
      }) .then((response) => 
      { 
        alert("게시물 작성 완료")
       this.$router.go(-1);
      })
      .catch((error) => { // 예외 처리 })

    })
}
  
  
  
    }
  }
  
</script>

<style>

</style>