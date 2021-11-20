<template>
  <b-container fluid>
    <Banner />
    <Area />
    
    <Category />
    <Sales />
  </b-container>
</template>

<script>
import Banner from '../components/Home/Banner.vue'
import Area from '../components/Home/Area.vue'
import Category from '../components/Home/Category.vue'
import Sales from '../components/Home/Sales.vue'


export default {
  name: 'Home',
  components: {
    Banner:Banner,
    Area:Area,
    Category:Category,
    Sales:Sales,
  }
}
</script>
