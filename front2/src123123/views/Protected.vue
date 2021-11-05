<template>
  <div class="protected">
    <h1>
      <b-badge variant="success">Welcome to Protected Page!</b-badge>
    </h1>
    zzz{{user}}
    <h5>당신은 인증된 사용자 입니다.</h5>
  </div>
</template>

<script>
  export default {
    name: 'protected',
    data() {
      return {}
    },
    props() {
      return {
        user: String,
      }
    },
    methods: {}
  }
</script>
