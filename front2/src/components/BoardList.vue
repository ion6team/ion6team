<template>

<b-card-group>
    
  <b-card
      :title='제목'
      
      >
      {{$store.state.boards.url}}
      <b-link href='#'><b-icon icon='chat-left-fill'></b-icon></b-link>
      <b-icon icon='heart'></b-icon>
     <!-- 클릭시 icon='heart-fill 로 변경하게 해야함-->
     
      <b-card-text>가격</b-card-text>
      <b-card-text>주소</b-card-text>
      <b-card-text>상세내용</b-card-text>
  </b-card>
  <p>{{$store.state.boards[0]}}</p>
</b-card-group>
</template>

<script>
export default {
    name:'boardList',
    data:function() {
return {

    };
},
    method:{

    }
}
</script>

<style>

</style>