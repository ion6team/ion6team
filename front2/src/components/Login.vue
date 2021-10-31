<template>
    <div class="unprotected" v-if="this.$store.state.loginSuccess==false">
        <h1>
            <b-badge variant="info">로그인해주세요</b-badge>
        </h1>
        <h5>로그인 하지 않았습니다. 로그인을 해주세요</h5>

        <label>
            <input type="text" placeholder="email" v-model="email">
        </label>
        <label>
            <input type="password" placeholder="password" v-model="password">
        </label>
        <b-btn variant="success" @click="login">Login</b-btn>
        <p v-if="error" class="error">Bad login information</p><br>
        <h2>{{result}} </h2>

        <p>로그인 성공 >> {{this.$store.state.loginSuccess}}</p>
        <p>로그인 에러 >>{{this.$store.state.loginError}}</p>
        <p>로그인 실패 >>{{this.$store.state.loginfailed}}</p>
    </div>
</template>

<script>
    // import axios from 'axios';

    export default {
        name: 'login',
        data() {
            return {
                loginSuccess: false,
                loginError: false,
                email: '',
                password: '',
                error: false,
                result: '',
            }
        },
        methods: {
            async login() {
                // axios.post('/api/login',{
                //     username: this.email,
                //     password: this.password,
                // }).then(response=>{
                //     console.log(response.data)
                //     if(response.data.email==this.email){
                //        this.result='로그인성공';
                //     }
                //     else this.result='실패';
                // })
                try {
                    await this.$store.dispatch('login', {
                        email: this.email,
                        password: this.password,
                    })
                    // await this.$router.push({ name: 'Protected' })
                } catch (err) {
                    throw new Error(err)
                }
                if (this.$store.state.loginSuccess == true) {
                    console.log("홈으로가자")
                    this.$router.push('/')
                }

                if (this.$store.state.loginSuccess == false){
                    console.log("현재페이지 새로고침하자")
                    this.$router.go()
                }

            }
        }
    }
</script>
