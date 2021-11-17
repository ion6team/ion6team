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
                    <td><img src="@/assets/Daangeun_Icon.png" width="40px" height="40px"></td>
                    <td>{{item.description}}</td>
                    <td>{{item.name}}</td>
                </tr>
            </tbody>
        </table>
        <b-button size="sm" style="float:right; margin-left:20px;" @click="deleteCategory()">삭제</b-button>
        <b-button size="sm" style="float:right;" @click="addCategory()">추가</b-button>
        <br><br>
        <b-pagination align="center" v-model="currentPage" :total-rows="rows" :per-page="perPage" >
        </b-pagination>
        
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
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
                options: [{
                        value: 5,
                        text: '5개씩'
                    },
                    {
                        value: 10,
                        text: '10개씩'
                    },
                    {
                        value: 20,
                        text: '20개씩'
                    }
                ],
                searchOptions: [{
                    value: 'id',
                    text: 'id'
                    },
                    {
                        value: 'email',
                        text:'이메일'
                    },
                    {
                        value:'address',
                        text:'주소',
                    },
                    {
                        value:'name',
                        text:'이름',
                    }

                ]
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
            
            // deleteMember(){
            //     var data = {
            //         selectlist : this.tableselected
            //     }
            //     axios.post('/api/admin/member', data, {
            //         headers: {
            //             'Content-Type': 'application/json',
            //             'Authorization': 'Bearer ' + this.$store.state.token
            //             }
            //     })

            //     alert("탈퇴되었습니다")
            //     this.tableselected = []
            //     this.loadApi()


            // },
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