<template>
    <div class="container">
        <b-form-select v-model="perPage" :options="options" size="sm" class="mt-3" style="float:right;">
        </b-form-select>
        <a v-if="kind!='all'" @click="kindAll()">전체보기</a>
        <b-form-select v-model="searchKinds" :options="searchOptions" style="float:left;margin-right:10px;">
        </b-form-select>
        <b-form-input size="sm" v-model="search" placeholder="검색하세요" style="float:left; width:200px;">
        </b-form-input>
        <b-button size="sm" style="float:left; margin-left:10px;" @click="searchApi()">Search</b-button>

        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>
                        <label class="form-checkbox">
                            <input type="checkbox" v-model="selectAll" @click="select">
                            <i class="form-icon"></i>
                        </label>
                    </th>
                    <th width="5%">id</th>
                    <th width="8%">name</th>
                    <th width="20%">email</th>
                    <th width="25%">address</th>
                    <th width="25%">address_detail</th>
                    <th width="10%">phone</th>
                    <th width="20%">탈퇴</th>
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
                    <td>{{item.name}}</td>
                    <td>{{item.email}}</td>
                    <td>{{item.address}}</td>
                    <td>{{item.address_detail}}</td>
                    <td>{{item.phone}}</td>
                    <td> <a v-if="item.activated==false" @click="check(item.id)"> 탈퇴
                    </a>
                    </td>
                </tr>
            </tbody>
        </table>

        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center">
        </b-pagination>


        <b-button size="sm" style="float:right;" @click="deleteMember()">삭제</b-button>
        <h6 style="float:right;">
            <pre />{{tableselected.length}}/{{rows}}..</h6>

            {{tableselected}}
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                kind:'all',
                rows: '',
                perPage: 5, 
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
                axios.get('/api/admin/member/' + this.kind  + '?page=' + page + '&size=' + this.perPage + '&keyword=' + this.keyword, {
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
            searchApi(){
                this.kind = this.searchKinds;
                this.keyword = this.search
            },
            kindAll(){
                this.kind='all';
            },
            
            deleteMember(){
                var data = {
                    selectlist : this.tableselected
                }
                axios.post('/api/admin/member', data, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + this.$store.state.token
                        }
                })

                alert("탈퇴되었습니다")
                this.tableselected = []
                this.loadApi()


            },
            check(rejoinid){

                axios.get('/api/admin/member/rejoin?id=' + rejoinid ,{
                    },
                    {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + this.$store.state.token
                        }
                })

                alert(rejoinid + "번 회원 탈퇴 취소")

                
                this.loadApi()

            }


        },
        watch: {
            currentPage() {
                this.loadApi()
            },
            perPage(){
                this.loadApi()
            },
            kind(){
                this.loadApi()
            },
            keyword(){
                this.loadApi()
            }

        }
    }
</script>

<style scoped>

</style>