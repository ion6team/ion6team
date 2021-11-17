<template>
<div class="background">
  <transition name="fade" mode="out-in">
  <router-view/>
  </transition>
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
.fade-enter {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}

.fade-leave-to {
  opacity: 0;
}
</style>