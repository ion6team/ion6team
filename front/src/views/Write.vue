
<template>

  <b-container style="width:1000px; margin-top:50px;">

    <hr style="color:#fec69f">

    <b-row>
      <b-col cols='3'>
        <label for="photo"><h5><b>상품사진 등록</b></h5>
        <span style="font-size:15px">(최대 3개)</span></label>
      </b-col>
      <b-col cols='9'>
        <input type="file" name="photo" id="photo" multiple />
      </b-col>
    </b-row>
  <hr style="color:#fec69f">

    <b-row>
      <b-col cols='3'>
       <label for="category"><h5><b>카테고리 선택</b></h5></label>
      </b-col>
      <b-col cols='9'>
        <select id="category" class="form-control" v-model="selected" style="border-color:#ff8a3d;">
          <option :key="i" :value="d.id" v-for="(d, i) in options">{{ d.name }}</option>
        </select>
      </b-col>
    </b-row>

  <hr style="color:#fec69f">

    <b-row>
      <b-col cols='3'>
        <label for="title"><h5><b>제목</b></h5></label>  
      </b-col>
      <b-col cols='9'>
        <b-form-input v-model="title" placeholder="제목을 입력하세요." style="border-color:#ff8a3d;"></b-form-input>
      </b-col>
    </b-row>
    <hr style="color:#fec69f">

    <!--
            <b-form-file
            v-model="file1"
            :state="Boolean(file1)"
            placeholder="이미지파일을 업로드해주세요"
            drop-placeholder="Drop file here..."
            ></b-form-file>
            <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div>
           -->
    <b-row>
      <b-col cols='3'>
        <label for="address"><h5><b>거래장소</b></h5></label>
        <b-checkbox id="checkbox-1" v-model="hope_address" name="checkbox-1" value="accepted"
          unchecked-value="not_accepted">
          <label for="address">거래 지역 변경</label>
        </b-checkbox>
      </b-col>
      <b-col cols='9'>
       <input v-if="hope_address=='not_accepted'"  class="form-control" v-model="defaultaddress" readonly style="border:0; background-color:white;">
       <input v-if="hope_address=='accepted'"  class="form-control" placeholder="거래 희망 지역" style="border-color:#ff8a3d;">
     </b-col>
    </b-row>

    <hr style="color:#fec69f">
    
    <b-row>
      <b-col cols='3'>
       <label for="price"><h5><b>가격</b></h5></label>
      </b-col>
      <b-col cols='9'>
        <input id="price" v-model="price" placeholder="가격" style="border-color:#ff8a3d;">원</input>
      </b-col>
    </b-row>

    <hr style="color:#fec69f">

    <b-row>
      <b-col cols='3'>
        <label for="detail"><h5><b>상세설명</b></h5></label>
      </b-col>
      <b-col cols='9'>
        <editor id="detail" v-model="value" @change="onChange" paste-as-text="true" ></editor>
      </b-col>
    </b-row>

    <hr style="color:#fec69f">
    
    <button class="btn btn-primary btn-lg btn-block" @click="write_board()" style="background-color:#ff8a3d; border-color:#fec69f">작성 완료</button>

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

<style scoped>

</style>