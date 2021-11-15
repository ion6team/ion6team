<template>
<div class="background">
  <router-view/>
</div>
</template>

<script>



export default {
  name: 'none'
  

  }
  
</script>

<style scoped>
.background{
  width: 100%;
  height: 100%;
  background-image:url(../../assets/bg.png);
  padding: 50px 0;
}
</style>