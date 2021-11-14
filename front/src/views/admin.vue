<template>
    <div class="container">
        <b-card title="Card Title" no-body>
            <b-card-header header-tag="nav">
                <b-nav card-header tabs>
                    <b-nav class="nav-tabs">
                        <b-nav-item :active="tab === 1" @click="tab = 1">회원 관리</b-nav-item>
                        <b-nav-item :active="tab === 2" @click="tab = 2">게시물 관리</b-nav-item>
                        <b-nav-item :active="tab === 3" @click="tab = 3">카테고리 관리</b-nav-item>
                    </b-nav>
                </b-nav>
            </b-card-header>

            <b-card-body class="text-center" v-if="tab==1">
                <b-card-text>
                    <b-form-group>

                        <ManageMember />
                        
                    </b-form-group>
                </b-card-text>
            </b-card-body>

            <b-card-body class="text-center" v-if="tab==2">
                <b-card-text>
                    게시물 관리
                </b-card-text>
                <b-button variant="primary">Go somewhere</b-button>
            </b-card-body>

            <b-card-body class="text-center" v-if="tab==3">
                <b-card-text>
                    카테고리 관리
                </b-card-text>
                <b-button variant="primary">Go somewhere</b-button>
            </b-card-body>
        </b-card>
    </div>
</template>

<script>
import ManageMember from '../components/admin/ManageMember.vue'
    export default {
        data(){
            return{
                tab: 1,
            }
        },
        components:{
            ManageMember,
        }
    }
</script>

<style>
</style>