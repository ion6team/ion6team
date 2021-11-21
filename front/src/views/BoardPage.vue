<template>
  <div>
    <!-- 상세페이지 : {{index}} -->
    <!-- 지금 로그인한 사람 : {{this.$store.state.member.email}} -->
    <div class="container" style="width:1000px">
      <b-carousel id="carousel-1" :interval="3000" v-model="slide" controls style="max-width:700px; margin:auto;"
        @sliding-start="onSlideStart" @sliding-end="onSlideEnd">
        <b-carousel-slide v-bind:img-src="'../upload/'+list.filepath1" style="height:700px"></b-carousel-slide>
        <b-carousel-slide v-if="list.filepath2!==null" v-bind:img-src="'../upload/'+list.filepath2"
          style="height:700px"></b-carousel-slide>
        <b-carousel-slide v-if="list.filepath3!==null" v-bind:img-src="'../upload/'+list.filepath3"
          style="height:700px"></b-carousel-slide>

      </b-carousel>

      <b-nav class="nav justify-content-end">
        <b-nav-item @click="put(list.member.email,list.id)">
          <p style="color:#ff8a3d">글 수정</p>
        </b-nav-item>
        <b-nav-item @click="del(list.member.email)">
          <p style="color:#ff8a3d">삭제</p>
        </b-nav-item>
      </b-nav>

      <div class="my-3" style="text-align:left">
        <h3 class="mx-2"><b> {{list.member.name}} </b></h3>
        <p>{{list.hopeaddress}}</p>

        <hr style="background-color:#ff8a3d;" class="my-4">
        <!-- 제품설명창  -->
        <p class="mx-2" style="font-size:14px;"> {{list.boardaddress}} </p>
      </div>

      <div class="my-3" style="text-align:left">
        <!-- 글제목 -->
        <h2 class="mx-2"> {{list.title}} </h2>
        <!--해당카테고리 -->
        <p class="mx-2" style="color:#999;"> {{list.category.name}}</p>
        <!--가격 -->
        <h5 class="mx-2"><b> {{list.price}} 원</b></h5>
      </div>
      <hr style="background-color:#ff8a3d;" class="my-4">

      <div>
        <!--상세내용 -->
        <p class="mx-2" style="text-align:left"> <span v-html="list.contents"></span></p>

        <b-nav>
          <b-nav-item disabled> 조회수 : {{list.hit}}</b-nav-item>

          <b-nav-item>
            <a href="#" v-if="check" class="material-icons" style="color:#ff8a3d;" @click="addzzim()">favorite</a>


            <a href="#" v-if="!check" class="material-icons" style="color:#ff8a3d;"
              @click="addzzim()">favorite_border</a>
          </b-nav-item>
        </b-nav>
        {{this.$store.state.member.id}}
        <button @click="$router.push({
          name:'ChatRomm',
          params:{
            chatid: list.member.id
            }
          })"> 채팅방 </button>
      </div>

      <hr style="background-color:#ff8a3d;" class="my-4">

      <b-row align-h="center">
        <b-col cols="6">
          <KaKaoMap v-bind:address="list.hopeaddress" />
        </b-col>
      </b-row>

      <hr style="background-color:#ff8a3d;" class="my-4">

      <!-- 댓글 -->




      <div>
        <b-row v-for="(reply,i) in replylist" :key="i" style="border-bottom:1px solid #ff8a3d; padding:10px;">
          <b-col cols='11'>

            <p style=" text-align:left; font-size:18px; margin-left:20px;"><b>{{reply.member.name}}</b></p>

            <p style="text-align:left; font-size:15px;">{{reply.content}}</p>

          </b-col>

          <b-col cols="1">
            <b-nav class="nav justify-content-end" style="height:20px; font-size:12px;">
              <!-- <b-nav-item @click="replyput(reply.id,list.member.email)" >
                  <p style="color:#ff8a3d">수정</p></b-nav-item> -->
              <b-nav-item @click="replydel(reply.id,list.member.email)">
                <p style="color:#ff8a3d">삭제</p>
              </b-nav-item>
            </b-nav>
          </b-col>
        </b-row>


      </div>

      <b-row no-gutters style="height:80px; margin:20px 0">
        <b-col cols="11" style="padding:0;">
          <textarea v-model="relpycontent" ref="relpycontent"
            style="height:100%; width:100%; border-color:#ff8a3d; border-radius: 10px 0 0 10px ;"></textarea>
        </b-col>
        <b-col cols='1' style="padding:0;">
          <button @click="replynew()"
            style="height:100%; width:100%; background-color:#ff8a3d; border:0; color:#fbf7f2; border-radius:0 10px 10px 0;">
            댓글쓰기 </button>
        </b-col>
      </b-row>




    </div>

  </div>
</template>

<script>
  import axios from 'axios'
  import KaKaoMap from '../components/KaKaoMap.vue'
  export default {

    data() {
      return {
        list: [],
        index: 0,
        replylist: [],
        replyno: 0,
        replycontent: '',
        zzimlist: '',
        zzim: '',
      }
    },
    props: {
      id: {
        type: String
      },
      myaddress: String
    },
    components: {
      KaKaoMap
    },

    mounted() {
      // const zimlist = this.$store.state.member.zzim;
      // if(.includes(this.index)){
      //   check=true;
      // }
      this.index = this.$route.params.id;
      const zzim = this.$store.state.member.zzim;
      console.log(zzim)

      axios.get("http://localhost:8080/api/board/" + this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.$store.state.token
          }
        })
        .then((res) => {
          this.list = res.data;
          this.totalpage = res.data.totalPages;
        })

      axios.get('/api/reply/' + this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.$store.state.token
          }
        })
        .then((res) => {
          this.replylist = res.data.content;
        })

      axios.get('/api/zzim', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.state.token
        }
      }).then((res) => {
        if (res.data.includes('-' + this.index)) {
          this.check = true
        } else {
          this.check = false
        }
      })

    },
    methods: {
      put(membervel, id) {
        if (membervel == this.$store.state.member.email) {
          this.$router.push({
            name: 'ReWrite',
            params: {
              id: id
            },
          })
        } else {
          alert("작성자가 아닙니다.")
        }
      },
      del(membervel) {
        if (membervel == this.$store.state.member.email) {
          this.index = this.$route.params.id;
          axios.delete('/api/board/' + this.index, {
              headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.$store.state.token
              }
            })
            .then((res) => {
              this.$router.go(-1);
            })

        } else {
          alert("작성자가 아닙니다.")
        }


      },

      onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      },
      replyput(value) {
        const replyno = value;
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
      replydel(value, membervel) {
        if (membervel == this.$store.state.member.email) {
          console.log(value);
          const replyno = value;
          axios.delete('/api/reply/' + replyno, {
              headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.$store.state.token
              }
            })
            .then((res) => {
              console.log(res.data.content[0]);
              alert("삭제되었습니다.")
              this.$router.go(0);
              //this.replylist=res.data.content;
            })
        } else {
          alert("작성자가 아닙니다.")
        }
      },

      addzzim() {
        axios.get('/api/zzim/' + this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.$store.state.token
          }
        }).then((res) => {
          console.log(res.data)
          if (res.data) {
            alert("찜 추가")
            this.check = false
            this.$router.go(0);
          } else {
            alert("찜 취소")
            this.check = true
            this.$router.go(0);
          }
        })
      },




    }
  }
</script>

<style scoped>
  a {
    text-decoration: none;
  }

  tr,
  th,
  td {
    min-height: 30px;
  }
</style>