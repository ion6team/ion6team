<template>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <a class="navbar-brand" href="#">당근마켓</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item" v-if="loggin==false">
                        <router-link to="/login">login</router-link> |
                    </li>

                    <li class="nav-item" v-if="loggin==false">
                        <router-link to="/join">signup</router-link> |
                    </li>

                    <li class="nav-item" v-if="loggin==true">
                        <router-link to="/join">{{usernn}}님</router-link> |
                    </li>

                    <li class="nav-item" v-if="loggin==true">
                        <router-link to="/join">logout</router-link> |
                    </li>

                    <!-- <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li> -->
                </ul>
                <!-- <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form> -->
            </div>
        </div>
    </nav>
</div>
</template>

<script>
    export default {
        data() {
            return {
                loginSuccess: false,
                loginError: false,
                user1: '',
                password: '',
                auth: 'ROLE_MEMBER',
                error: false,
                usernn:''
            }
        },

        computed:{
            loggin(){
                return this.$store.state.loginSuccess
            },
        },
        watch:{
            username(){
                this.usernn = this.$store.state.userName
            }
        }
    }
</script>

<style>

</style>