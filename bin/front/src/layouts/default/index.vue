<template>
<div style="margin:0; width:100%; height:100%">
    <Header />

  
  <router-view/>
  

    <Footer />
</div>
</template>

<script>
import Header from './Header'
import Footer from './Footer'

export default {
  name: 'defaultLayout',
  components: {
    Header:Header,
    Footer:Footer

  }
  

}
</script>

<style scoped>


</style>