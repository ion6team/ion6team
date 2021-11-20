<template>
    <div class="container">
        <b-card title="Card Title" no-body>
            <b-card-header header-tag="nav">
                <b-nav card-header tabs>
                    <b-nav class="nav-tabs">
                        <b-nav-item :active="this.$store.state.admintab === 1" @click="tab1()">회원 관리</b-nav-item>
                        <b-nav-item :active="this.$store.state.admintab === 2" @click="tab2()">게시물 관리</b-nav-item>
                        <b-nav-item :active="this.$store.state.admintab === 3" @click="tab3()">카테고리 관리</b-nav-item>
                    </b-nav>
                </b-nav>
            </b-card-header>

            <b-card-body class="text-center" v-if="this.$store.state.admintab==1">
                <b-card-text>
                    <b-form-group>

                        <ManageMember />
                        
                    </b-form-group>
                </b-card-text>
            </b-card-body>

            <b-card-body class="text-center" v-if="this.$store.state.admintab==2">
                <b-card-text>
                    <b-form-group>
                        <ManageBoard />
                    </b-form-group>
                </b-card-text>
            </b-card-body>

            <b-card-body class="text-center" v-if="this.$store.state.admintab==3">
                <b-card-text>
                    <b-form-group>
                        <ManageCategory />
                    </b-form-group>
                </b-card-text>
                <b-button variant="primary">Go somewhere</b-button>
            </b-card-body>
        </b-card>
    </div>
</template>

<script>
import ManageMember from '../components/admin/ManageMember.vue'
import ManageBoard from '../components/admin/ManageBoard.vue'
import ManageCategory from '../components/admin/ManageCategory.vue'
    export default {
        data(){
            return{
                tab: 1,
            }
        },
        components:{
            ManageMember, ManageBoard, ManageCategory
        },
          methods: {
           tab1() {
            
            this.$store.commit('changeadmintab', 1)
          },
          tab2() {
              
            this.$store.commit('changeadmintab', 2)

          },
          tab3() {
              
            this.$store.commit('changeadmintab', 3)

          }
    }
    }
</script>

<style scoped>
</style>