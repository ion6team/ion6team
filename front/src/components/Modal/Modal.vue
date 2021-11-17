<template>
  <div id="modal">
      <div id='overlay' @click="close">
        <div id="modal-card">
            <b-nav align-right style="position=relative;">
                    <button @click="close">X</button>
            </b-nav>

            <slot />
            
        </div>
      </div>
  </div>
</template>

<script>
export default {
    name:'modal',
    methods:{
      close(){
        this.$store.commit('closeModal');
      }
    },
}
</script>

<style scoped>
    #modal,#overlay{
        width: 100%;
        height: 100%;
        position: fixed;
        left: 0;
        top: 0;
    }
    #overlay{
        opacity:0.5;
        background-color: black;
    }
    #modal-card{
        position: relative;
        max-width: 50%;
        margin: auto;
        margin-top: 30px;
        background-color: white;
        min-height: 300px;
        z-index: 10;
        opacity:1;
    }
    button{
        background-color: white;
        width: 30px;
        height: 30px;
        border: 0;
        position: absolute;
        right: 0;
        margin: 5px;
    }
</style>