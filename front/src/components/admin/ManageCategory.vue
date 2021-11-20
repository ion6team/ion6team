<template>
    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>
                        <label class="form-checkbox">
                            <input type="checkbox" v-model="selectAll" @click="select">
                            <i class="form-icon"></i>
                        </label>
                    </th>
                    <th width="10%">id</th>
                    <th width="20%">icon</th>
                    <th width="35%">name</th>
                    <th width="35%">description</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item,i) in items" v-bind:key="i">
                    <td>
                        <label class="form-checkbox">
                            <input type="checkbox" :value="item.id" v-model="tableselected">
                            <i class="form-icon"></i>
                        </label>
                    </td>
                    <td>{{item.id}}</td>
                    <td><img  v-bind:src= "'../../upload/category/'+item.icon" width="40px" height="40px"></td>
                                        <td>{{item.name}}</td>
                    <td>{{item.description}}</td>

                </tr>
            </tbody>
        </table>
        <b-button size="sm" style="float:right; margin-left:20px;" @click="deleteCateogory()">삭제</b-button>
        <b-button size="sm" style="float:right;" v-b-modal.AdminAddCategory>추가</b-button>
        <br><br>
        <b-pagination align="center" v-model="currentPage" :total-rows="rows" :per-page="perPage" >
        </b-pagination>

          <b-modal id="AdminAddCategory"  centered hide-header hide-footer size="xl" style="position:relative;">
              
               <template #default="{ close }">
                  <div style="background-color: #fbf7f2; min-height:200px; width:100%;">
                      <b-button style="background-color:#ff8a3d; border:3px solid white; border-radius:50%; height:40px; width:40px; text0align:center; position:absolute; top:-10px; right:-10px;"
                    @click="close()">
                      X
                    </b-button>

                <admin-add-category/>

            
                  </div>
              </template>
            </b-modal>
    </div>
</template>

<script>
    import axios from 'axios';
    import AdminAddCategory from '../Modal/AdminAddCategory.vue'

    export default {
      components:{
        AdminAddCategory
      },
        data() {
            return {
                kind:'all',
                rows: '',
                perPage: 10, 
                currentPage: null, 
                searchKinds: 'id',
                totalpage: '',
                text: '',
                search: '',
                active: this.tab,
                keyword:'',
                items: [],
                tableselected: [],
                selectAll: false,
            }
        },
        mounted() {
            this.currentPage = 1

        },
        methods: {
            select() {
                this.tableselected = [];
                if (!this.selectAll) {
                    for (let i in this.items) {
                        this.tableselected.push(this.items[i].id);
                    }
                }
            },
            searchtext() {
                this.search = this.text;
            },
            loadApi() {
                const page = this.currentPage - 1
                axios.get('/api/admin/category?page=' + page,{
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.token
                        }
                    })
                    .then(response => {
                        console.log(response.data)
                        this.items = response.data.content
                        this.rows = response.data.totalElements
                    })
            },
            
            deleteCateogory(){
                var data = {
                    selectlist : this.tableselected
                }
                axios.post('/api/admin/rcategory', data, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + this.$store.state.token
                        }
                })

                this.tableselected = []
                this.loadApi()
                this.$router.go(0)
                


            },
        },
        watch: {
            currentPage() {
                this.loadApi()
            },
        }
    }
</script>

<style scoped>

</style>