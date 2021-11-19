<template>
  <div>
    상세페이지 : {{index}}
    <div class="container" style="width:1000px">
      <b-carousel id="carousel-1" v-model="slide" controls style="max-width:700px; margin:auto;"
        @sliding-start="onSlideStart" @sliding-end="onSlideEnd">
        <b-carousel-slide v-bind:img-src="'../upload/'+list.filepath1" style="height:700px"></b-carousel-slide>
        <b-carousel-slide v-if="list.filepath2!==null" v-bind:img-src="'../upload/'+list.filepath2"
          style="height:700px"></b-carousel-slide>
        <b-carousel-slide v-if="list.filepath3!==null" v-bind:img-src="'../upload/'+list.filepath3"
          style="height:700px"></b-carousel-slide>

      </b-carousel>

      <div class="my-3" style="border-bottom:1px solid #fec69f; text-align:left">
        <h3 class="mx-2"><b> {{list.member.name}} </b></h3>
        <p>{{list.hopeaddress}}</p>

        <!-- 제품설명창  -->
        <p class="mx-2" style="font-size:14px;"> {{list.boardaddress}} </p>
      </div>

      <div class="my-3" style="border-bottom:1px solid #fec69f; text-align:left">
        <!-- 글제목 -->
        <h2 class="mx-2"> 제목{{list.title}} </h2>
        <!--해당카테고리 -->
        <p class="mx-2" style="color:#999;"> {{list.category.name}}</p>
        <!--가격 -->
        <h5 class="mx-2"><b> {{list.price}} 원</b></h5>
        <!--상세내용 -->
        <p class="mx-2"> <span v-html="list.contents"></span></p>

        <b-nav>
          <b-nav-item disabled>조회수 45</b-nav-item>

          <b-nav-item>
            <a href="#" v-if="check" class="material-icons" style="color:#ff8a3d;" @click="addzzim()">favorite</a>


            <a href="#" v-if="!check" class="material-icons" style="color:#ff8a3d;" @click="addzzim()">favorite_border</a>
          </b-nav-item>
        </b-nav>

      </div>

      <div class="my-3">
        <KaKaoMap v-bind:address="list.hopeaddress" />
      </div>


      <!-- 댓글 -->
      <button @click="$router.push({
          name:'ReWrite',
          params:{
            id:list.id
            },
          })">수정</button>
      <button @click="del()"> 삭제 </button>


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
      <h3>댓글 쓰기</h3>
      <textarea v-model="relpycontent" ref="relpycontent"></textarea>
      <button @click="replynew"> 작성 </button>


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
        if(res.data.includes('-'+this.index)){
          this.check = true
        }else{
          this.check= false
        }
      })

    },
    methods: {
      del() {
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

      },

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

      addzzim() {
        axios.get('/api/zzim/' + this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.$store.state.token
          }
        }).then((res) => {
          console.log(res.data)
          if(res.data){
            alert("찜 추가")
            this.check=false
            this.$router.go(0);
          }else{
            alert("찜 취소")
            this.check=true
            this.$router.go(0);
          }
        })
      },




    }
  }
</script>

<style scoped>

</style>