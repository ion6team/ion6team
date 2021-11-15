<template>
  <div>
    상세페이지 : {{index}}
          <div class="container" style="width:1000px">
        <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        img-width="1024"
        img-height="480"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
        >
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54"></b-carousel-slide>
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=52"></b-carousel-slide>

        </b-carousel>

<div 
        class="my-3" 
        style="border-bottom:1px solid #fec69f; text-align:left">
        <h6 class="mx-2"><b> {{list.member.name}} </b></h6>
        <p class="mx-2" style="font-size:14px;"> {{list.boardaddress}} </p>
        </div>

  <div 
        class="my-3" 
        style="border-bottom:1px solid #fec69f; text-align:left">
            <h4 class="mx-2"> {{list.title}} </h4><!-- 글제목 -->
            <p class="mx-2"> {{list.category.name}}</p> <!--해당카테고리 -->
            <h5 class="mx-2"> {{list.price}} </h5><!--가격 -->
            <p class="mx-2"> {{list.contents}}</p><!--상세내용 -->
        </div>

        <div class="my-3">
            <!-- 지도API -->
        </div>

        <button>수정</button> <button>삭제</button> 

        <div style="background-color:#fbf7f2">
            <div>
                <table v-for="(reply,i) in replylist" :key="i">
                    <tr>
                        <th style="width:150px; border-right:1px solid #fec69f; text-align:center">{{reply.member.name}}</th>
                        <td>{{reply.content}}</td>
                    </tr>
                </table>
            </div>
        </div>



      <!-- <h3>게시글 정보</h3>
      게시글 번호 : {{list.id}} <br>
      게시글 이름 : {{list.title}} <br>
      게시판 내용 : {{list.contents}} <br>
      가격 : {{list.price}} <br>
      판매여부 : {{list.onslae}} <br>
      희망거래 지역 : {{list.boardaddress}} <br>
      작성일 : {{list.create_date}} <br>

    </div>
    <hr>
    <div>
      <h3>작성자 정보</h3>
      작성자 아이디 : {{list.member.id}} <br>
      작성자 이름 : {{list.member.name}} <br>
      작성자 이메일 : {{list.member.email}} <br>
      작성자 주소 : {{list.member.address}} <br>
      작성자 누적 신고 : {{list.member.report_count}} <br>

    </div>
    <div>
      <h3>카테고리 정보</h3>
      카테고리 아이디 : {{list.category.id}} <br>
      카테고리 명 : {{list.category.name}} <br>
    </div>
    <div>
      <h3>댓글 정보</h3>
      <b-card v-for="(reply,i) in replylist" :key="i" class="list-group">
        댓글 번호 : {{reply.id}}<br>
        댓글 아이디 : {{reply.member.email}}<br>
        댓글 내용 : {{reply.content}} <br>
        댓글 작성일 : {{reply.createDate}} <br>
        <textarea v-model="relpycontent1" ref="relpycontent1"></textarea>

        <button @click="replyput(reply.id)"> 수정 </button>
        <button @click="replydel(reply.id)"> 삭제 </button>
      </b-card>-->
      <h3>댓글 쓰기</h3>
      <textarea v-model="relpycontent" ref="relpycontent"></textarea>
      <button @click="replynew"> 작성 </button> 

    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {

    data() {
      return {
        list: [],
        index: 0,
        replylist: [],
        replyno: 0,
        replycontent: '',
      }
    },
    props: {
      id: {
        type: String
      }
    },

    mounted() {
      this.index = this.$route.params.id;

      axios.get("http://localhost:8080/api/board/" + this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.$store.state.token
          }
        })
        .then((res) => {
          //console.log(res.data.content[0]);
          this.list = res.data;
          this.totalpage = res.data.totalPages;
          console.log(replyno);
        })

      axios.get('/api/reply/' + this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.$store.state.token
          }
        })
        .then((res) => {
          //console.log(res.data.content[0]);
          this.replylist = res.data.content;
        })
    },
    methods: {

      onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      },
      replyput(value) {
        const replyno = value;
        this.index = this.$route.params.id;
        axios.put('/api/reply/' + replyno, {
            content: this.relpycontent1
          }, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.$store.state.token
            }
          })
          .then((res) => {

            console.log(res.data.content);
            this.$router.go(0);
            //this.replylist=res.data.content;
          })


      },
      replynew() {

        this.index = this.$route.params.id;
        axios.post('/api/reply/' + this.index, {
            contents: this.relpycontent
          }, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.$store.state.token
            }
          })
          .then((res) => {

            console.log(res.data.content);
            this.$router.go(0);
            //this.replylist=res.data.content;
          })

      },
      replydel(value) {
        console.log(value);
        const replyno = value;
        this.index = this.$route.params.id;
        axios.delete('/api/reply/' + replyno, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.$store.state.token
            }
          })
          .then((res) => {
            console.log(res.data.content[0]);
            this.$router.go(0);
            //this.replylist=res.data.content;
          })
      },

    }
  }
</script>

<style scoped>

</style>