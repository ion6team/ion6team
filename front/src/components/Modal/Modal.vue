<template>
  <div id="modal">
      <slot />
  </div>
</template>

<script>
export default {
    name:'modal'
}
</script>

<style scoped>
    #modal{
        background-color: blue;
    }
</style>