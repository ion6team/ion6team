<template>
<div>
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

<style>

</style>