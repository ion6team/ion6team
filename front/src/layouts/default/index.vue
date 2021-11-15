<template>
<b-container
  fluid>
    <Header />
  <router-view/>
    <Footer />
</b-container>
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