<template>
  <b-container fluid style="background-color:#e6f3e6">
    <b-container
      fluid="md"
      class="p-4"
      style="height:600px;"
      >
      <b-row align-v="center" style="height:100%">
        <b-col cols='5'>
            <div>
              <button >버튼</button><!--카테고리 db받아서 for문 돌릴것 -->
            </div>
        </b-col>

        <b-col cols='7'>
            <b-row>
                <b-col>
                  <h2>카테고리별</h2>
                </b-col>
            </b-row>
            <b-row>
                <b-col >
                 <h2>검색하기</h2>
                 <p>개인용품부터 사무용품까지 내가 필요한 물품만 모아보아요</p>
                </b-col>
            </b-row>
        </b-col>
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
export default {
    name:'category'
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