<template>
    <div>
      상세페이지 : {{index}}
      <div>
        <h3>게시글 정보</h3>
        게시글 번호 : {{list.id}} <br>
        게시글 이름 : {{list.title}} <br>
        가격 : {{list.price}} <br>
        희망거래 지역 : {{list.hope_address}} <br>
        작성일 : {{list.create_date}} <br>
        수정일 : {{list.update_date}} <br>
      </div>
      <hr>
      <div>
          <h3>작성자 정보</h3>
          작성자 아이디 : {{list.member.id}} <br>
          작성자 이름 : {{list.member.name}} <br>
          작성자 이메일 : {{list.member.email}} <br>
          작성자 주소 : {{list.member.address}} <br>
          작성자 상세주소 : {{list.member.address_detail}} <br>
          작성자 누적 신고 : {{list.member.report_count}} <br>
          작성자 등급 : {{list.member.role}} <b></b>
      </div>
      <div>
          <h3>카테고리 정보</h3>
          카테고리 아이디 : {{list.category.id}} <br>
          카테고리 명 : {{list.category.name}} <br>
          카테고리 구분코드 : {{list.category.description}} <br>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data(){
        return{
            list:[],
            index:0,
        }
    },
    props:{
        id:{
            type:Number
        }
    },

    mounted(){
        this.index=this.$route.params.id;

		axios.get("http://localhost:8080/api/board/"+this.index, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ this.$store.state.token
          }
        })
		.then((res)=>{
        //console.log(res.data.content[0]);
            this.list=res.data;
            console.log(this.list);
        })
    },
    
}
</script>

<style>

</style>