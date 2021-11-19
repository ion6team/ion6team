
<template>
  <b-container style="width:600px; margin-top:50px;">

    <div class="mb-3">
      <label for="title">제목</label>
      <div>
        <b-form-input v-model="title" placeholder="제목을 입력하세요."></b-form-input>
      </div>
    </div>
    <select class="form-control" v-model="selected" style="border-color:#c2e8c2;">
          <option :key="i" :value="d.id" v-for="(d, i) in options">{{ d.name }}</option>
        </select>
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
      <editor v-model="value" @change="onChange" paste-as-text="true"></editor>
    </div>
    <div>

  <form> <input type="file" name="photo" id="photo" multiple/> </form>



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
        value: '',
        hope_address: 'not_accepted',
        contents: '',
        defaultaddress: '',
        category_id: 1,
        image : '',
        FormData : null
      }
    },
    mounted() {
      axios.get('/api/category', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        },
      }).then((res) => {
        console.log(res.data);
        this.options = res.data
      })
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
    val() {
                this.value = "This's new value";
            },

            onChange(html, text) {
                console.log(html.length, text.length);
            },

write_board() {
      var frm = new FormData(); 
      var photoFile = document.getElementById("photo"); 
      var data ={
        "title" : this.title,
        "contents" : this.contents,
        "category_id": this.selected,
        "price": this.price,
        "hopeaddress": this.defaultaddress,
         "onsale" :false
      }
     frm.append('data', new Blob([ JSON.stringify(data) ], {type : "application/json"}));

      if(photoFile.files[0] != null){
frm.append("file",  photoFile.files[0]); //대표이미지
      frm.append("file",  photoFile.files[1]);
      frm.append("file",  photoFile.files[2]); 
      // frm.append("file", photoFile.files[2]); 

      axios.post('http://localhost:8080/api/board', frm, 
      { 
        headers: 
       { 
         'Content-Type': 'multipart/form-data' , 
          'Authorization': 'Bearer ' + this.$store.state.token
       } 
      }) .then((response) => 
      { 
        console.log(photoFile.files[0])
        console.log(photoFile.files[1]) 
        console.log(photoFile.files[2])
        alert("게시물 작성 완료")
       this.$router.go(-1);
      })
      .catch((error) => { // 예외 처리 })

    })



      }

      else{
          alert("사진이없는 처리를 할게요")
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
  }
  
</script>

<style>

</style>