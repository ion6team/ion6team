<!-- 내지역 보기 -->
<template>
  <div>
    <div>
      <h3 style="postion:flex;" align="left">{{myaddress}} 내 검색결과 </h3>
      <b-button v-b-modal.searchAddr @click="addressApi()">다른지역검색</b-button>
      <b-modal id="searchAddr" centered hide-footer>
    <searchAddr />
  </b-modal>

<b-navbar type="light" variant="light">
  <b-nav>
    <b-nav-item>
      <select class="form-control" v-model="selected">
          <option :key="i" :value="d.id" v-for="(d, i) in options">{{ d.name }}</option>
        </select>
    </b-nav-item>

    <b-nav-item>
      <b-input-group>
            <b-form-input placeholder="검색" v-model="keyword"></b-form-input>
            <b-input-group-append>
              <b-button type='submit' @click="searchApi()">
                <b-icon icon='search'></b-icon>
              </b-button>
            </b-input-group-append>
          </b-input-group>
    </b-nav-item>
  </b-nav>
</b-navbar>

    
      
    </div>
 
    {{selected}}
    {{keyword}}
    <hr>
    <!-- <b-card-group class="cardlayout"> -->
    <div>
      <b-card @click="$router.push({
          name:'BoardPage',
          params:{
            id:board.id
            }
          })" v-for="(board,i) in list" :key="i" img-src="" img-alt="Card image"
        img-left title="" class="mb-3" style="border:1px solid #fec69f">
        <!-- <img src="../assets/1373b807-50c9-43a7-837f-fb3b2c5e34ce_dog5.jpg" />
        <img src="`../assets/${board.filename}`" />  -->
        <img  class="product-thumb" v-bind:src= "'../../upload/'+board.filepath1" />
        <h4><b>{{board.title}}</b></h4>
        <template #footer>
          <b-link href='#'>
            <b-icon icon='chat-left-fill' class="m-1"></b-icon>
          </b-link>
          <b-icon icon='heart' class="m-1"></b-icon>
        </template>


        <table class="mx-5 my-3">
          <tr>
            <th style="width:20%">
              <b-card-text>가격 : </b-card-text>
            </th>
            <td style="width:30%">
              <b-card-text>{{board.price}}</b-card-text>
            </td>
            <th style="width:20%">
              <b-card-text>주소 : </b-card-text>
            </th>
            <td style="width:30%">
              <b-card-text>{{board.hopeaddress}}</b-card-text>
            </td>

          </tr>
          <tr>
            <th>
              <b-card-text>상세내용</b-card-text>

            </th>
            <td colspan="3"></td>
          </tr>
          <tr>

            <td colspan="4">
              <p style="height:50px; 
          overflow: hidden;
          text-overflow: ellipsis;">
                 <span v-html="board.contents"></span>
              </p>
            </td>
          </tr>
        </table>
      </b-card>
    </div>
    <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center">
    </b-pagination>

    <b-button
                class="bg-primary m-2"
                v-b-modal.needLogin>
                버튼
                </b-button>
                <b-modal id="needLogin" centered hide-footer>
           <needLogin />
        </b-modal>
  </div>
</template>

<script>
  import axios from 'axios';
  import SearchAddr from './Modal/SearchAddr.vue'
  import NeedLogin from './Modal/NeedLogin.vue'

  export default {
    data() {
      return {
        myarea: '',
        keyword: '',
        rows: '',
        perPage: 5,
        currentPage: null,
        list: [],
        pagecount: 1,
        totalpage: 0,
        selected: 1,
        options: [],
        myaddress: '',
      }
    },
    components:{
      SearchAddr,
      NeedLogin,
    },
    mounted() {
      this.currentPage = 1

      this.myaddress = this.$store.state.member.address

      axios.get('/api/category', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        },
      }).then((res) => {
        console.log(res.data);
        this.options = res.data
      })
    },
    methods: {
      loadApi() {
        const page = this.currentPage - 1
        axios.get('/api/board?page=' + page +
            '&size=' + this.perPage +
            '&hopeaddress=' + this.myaddress +
            '&category_id=' + this.selected +
            '&keyword=' + this.keyword, {
              headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.$store.state.token
              },
            })
          .then(response => {
            this.list = response.data.content
            this.rows = response.data.totalElements
          })
      },
      searchApi() {
        this.loadApi();
      },
      addressApi() {
        new window.daum.Postcode({
          oncomplete: (
            data
          ) => { // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분. // 도로명 주소의 노출 규칙에 따라 주소를 조합한다. // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let fullRoadAddr = data.roadAddress; // 도로명 주소 변수 
            let extraRoadAddr = ''; // 도로명 조합형 주소 변수 // 법정동명이 있을 경우 추가한다. (법정리는 제외) // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다. 

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

            this.myaddress = fullRoadAddr;

          }
        }).open(this.$refs.embed)
      },
    },
    watch: {
      currentPage() {
        this.loadApi()
      },
      selected() {
        this.keyword = ''
        this.loadApi()
      },
      myaddress(){
        this.loadApi()
      }
    }

  }
</script>

<style>
  tr,
  th,
  td {
    text-align: left;
    padding: 10px;
  }
</style>