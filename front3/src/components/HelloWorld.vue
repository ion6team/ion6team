<template>
  <div class="hello">
    <button v-on:click='getData'>getAllMembers</button>
    <hr>
    {{ getAllMember }}
        <hr>
    <input v-model="id" placeholder="id"> <br>
    <input v-model="pw" placeholder="pw"> <br>
    <button v-on:click='get'>getParam</button> <br>
    <p> {{ login }} </p>
    <hr>
    <input v-model="id" placeholder="id"> <br>
    <input v-model="pw" placeholder="pw"> <br>
    <button v-on:click='post'>postParam</button> <br>
    <p> {{ login2 }} </p>


    

  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'HelloWorld',
    data() {
      return {
        getAllMember: '',
        id: 'gdgd',
        pw: 'gdgd',
        checkResult: '',
        login: '',
        login2: '',
        member: {
          id: '',
          pw: ''
        }
      }
    },
    methods: {

      post(){
        const url = 'http://localhost:8080/check';

        axios.post(url, {id:this.id, pw:this.pw}).then((response) => {
          console.log(this.id)
          console.log(this.pw)
          console.log(response)
          this.login2 = response.data;
        })
      },   
      getData() {
        const url = 'http://localhost:8080/member/all';

        axios.get(url, {
          headers: {
            'Content-Type': 'application/json'
          }
        }).then((response) => {
          console.log(response.data)
          this.getAllMember = response.data
        })
      },
      get(){
        const url = 'http://localhost:8080/check';

        axios.get(url, {
           params: {
            id: this.id,
            pw: this.pw
          }
        }).then((response) => {
          console.log(this.id)
          console.log(this.pw)
          console.log(response)
          this.login = response.data;
        })
      },
   
    }
    //   methods: {
    //     getData: function(){
    //       axios.get('http://localhost:8080/member/all, {
    //          headers: {
    //             'Content-Type': 'application/json'
    //          }
    //       }).then(function(response){
    //         console.log(response.data);
    //         this.getAllMember = response.data;
    //       })
    //       .catch(function(error){
    //         console.log(error);
    //       })
    //     }
    //   }
    // }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
