
<template>

  <b-container style="width:1000px; margin-top:50px;">

    <hr style="color:#fec69f">

    <b-row>
      <b-col cols='3'>
        <h5><b>상품사진 등록</b></h5>
        <span style="font-size:15px">(최대 3개)</span>
      </b-col>
      <b-col cols='9' >
        <div class="filebox">
          <label for="photo"></label>
          <input type="file" name="photo" id="photo" accept="image/*" @change="previewMultiImage" class="form-control-file"  multiple />
           <template v-if="preview_list.length">
           <div v-for="item, index in preview_list" :key="index">
                <img :src="item" class="img-fluid" />
                <p class="mb-0">file name: {{ image_list[index].name }}</p>
                <p>size: {{ image_list[index].size/1024 }}KB</p>
             </div>
            </template>
              <button @click="reset">취소</button>

        </div>
      <!--  <div >
          <label for="photo"></label>
          <input type="file" name="photo" id="photo" multiple />
        </div> -->
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
          <label for="address" style="margin:0 10px">거래 지역 변경</label>
        </b-checkbox>
      </b-col>
      <b-col cols='9'>
       <input v-if="hope_address=='not_accepted'"  class="form-control" v-model="defaultaddress" readonly style="border:0; background-color:white;">
       <input v-if="hope_address=='accepted'"  class="form-control"   v-model="defaultaddress" placeholder="거래 희망 지역" style="border-color:#ff8a3d;"  @click="addressApi()">
       
     </b-col>
    </b-row>

    <hr style="color:#fec69f">
    
    <b-row >
      <b-col cols='3'>
       <label for="price"><h5><b>가격</b></h5></label>
      </b-col>
      <b-col cols='9' class="nav justify-content-left">
        <input id="price" v-model="price" placeholder="가격" style="border-color:#ff8a3d; margin:0 10px; border-radius:5px;">원</input>
      </b-col>
    </b-row>

    <hr style="color:#fec69f">

    <b-row>
      <b-col cols='3'>
        <label for="detail"><h5><b>상세설명</b></h5></label>
      </b-col>
      <b-col cols='9'>
        <editor id="detail" v-model="value" @change="onChange" paste-as-text="true" style="height:400px;"></editor>
      </b-col>
    </b-row>

    <hr style="color:#fec69f">
    
    <button class="btn btn-primary btn-block" @click="write_board()" style="background-color:#ff8a3d; border-color:#fec69f">작성 완료</button>

  </b-container>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'write',
    data() {
      return {
        title: '',
        options:[],
        price: 0,
        file1: null,
        value: '',
        hope_address: 'not_accepted',
        contents: '',
        defaultaddress: '',
        selected: 1,
        category_id: 1,
       FormData : null,
      preview: null,
      image: null,
      preview_list: [],
      image_list: []
       
    
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
     previewMultiImage: function(event) {
      var input = event.target;
      var count = input.files.length;
      var index = 0;
      if (input.files) {
        while(count --) {
          var reader = new FileReader();
          reader.onload = (e) => {
            this.preview_list.push(e.target.result);
          }
          this.image_list.push(input.files[index]);
          reader.readAsDataURL(input.files[index]);
          index ++;
        }
      }
    },

    reset: function() {
      this.image = null;
      this.preview = null;
      this.image_list = [];
      this.preview_list = [];
    },
     addressApi() {
        new window.daum.Postcode({
          oncomplete: (
            data
          ) => { // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분. // 도로명 주소의 노출 규칙에 따라 주소를 조합한다. // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let fullRoadAddr = data.roadAddress; // 도로명 주소 변수 
            let extraRoadAddr =
            ''; // 도로명 조합형 주소 변수 // 법정동명이 있을 경우 추가한다. (법정리는 제외) // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다. 

            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
              extraRoadAddr += data.bname;
            } // 건물명이 있고, 공동주택일 경우 추가한다.

            if (data.buildingName !== '' && data.apartment === 'Y') {
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            } // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다. 
            if (extraRoadAddr !== '') {
              extraRoadAddr = ' (' + extraRoadAddr + ')';
            } // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다. 
            if (fullRoadAddr !== '') {
              fullRoadAddr += extraRoadAddr;
            } // 우편번호와 주소 정보를 해당 필드에 넣는다. 

            this.defaultaddress = fullRoadAddr;

          }
        }).open(this.$refs.embed)
      },


 
 
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
        "contents" : this.value,
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
hr{
  margin: 30px;
}
.filebox label {
  display: inline-block;
  padding: .5em .75em;
  color: #999;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: .25em;
  height: 200px;
  width:200px;
  background-image: url(../assets/uploadImg.png);
}

.filebox input[type="file"] {  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
}

</style>