<template>
<div>
    <Header />
  <router-view/>
    <Footer />
</div>
</template>

<script>
import Header from './Header'


export default {
  name: 'defaultLayout',
  components: {
    Header:Header,

  }
  

}
</script>

<style>

</style>