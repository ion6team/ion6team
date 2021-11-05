<template>
  
  <b-container fluid>
    <b-container
      fluid="md"
      class="p-4"
      style="height:600px;"
      >
      
        <b-row align-v="center" style="height:100%">
          <b-col cols='7'>
            <b-row>
              <h2>우리동네</h2>
              <h2>중고직거래마켓</h2>
            </b-row>
            <b-row>
              <b-col>
                
                <b-button href="/board" 
                class="bg-primary m-2 ">
                내지역보기
                </b-button>

                <b-button href="/board" 
                class="bg-primary m-2">
                지역검색
                </b-button>

              </b-col>
            </b-row>
          </b-col>

          <b-col cols='5'>
              <div style="background-color:orange; width:300px; height:300px"></div>
          </b-col>
       </b-row>
      
      
  
    </b-container>
  </b-container>
</template>

<script>
export default {
    name:'area'
}
</script>

<style>

</style>