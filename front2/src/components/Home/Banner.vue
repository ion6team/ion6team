<template>
  <b-container fluid style="background-color:#fbf7f2">
    <b-container
      fluid="md"
      class="p-4"
      style="height:600px;"
      >
      <b-row align-v="center" style="height:100%">
        <b-col cols='5'>
            <div style="background-color:orange; width:300px; height:300px"></div>
        </b-col>

        <b-col cols='7'>
            <b-row>
                <b-col md="6" offset-md="1" >
                  <h2>당신의 근처에</h2>
                </b-col>
            </b-row>
            <b-row>
                <b-col md="8" offset-md="3" >
                 <img src="../../assets/name.jpg">
                </b-col>
            </b-row>
        </b-col>
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
export default {
    name:'banner'
}
</script>

<style>

</style>