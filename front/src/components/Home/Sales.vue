<template>
  <b-container <b-container fluid>
    <b-container fluid="md" class="p-4" style="height:600px;">

      <b-row align-v="center" style="height:100%">
        <b-col cols='7'>

          <h2>나도 판매하기</h2>
          <p>더이상 사용하지 않는 내 물품, 다른사람들에게는 필요할 수 있어요</p>
                <b-button href="/write" 
                class="bg-primary m-2 ">
                판매 글쓰기
                </b-button>
        </b-col>

        <b-col cols='5'>
          <img src="../../assets/sales.png">
        </b-col>
      </b-row>



    </b-container>
  </b-container>
</template>

<script>
  export default {
    name: 'sales'
  }
</script>

<style scoped>
h2{
  font-size: 50px;
}
p{
  margin: 20px 0;
}
</style>