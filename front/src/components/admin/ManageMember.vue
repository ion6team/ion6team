<template>
    <div class="container">
        
        
         <b-navbar-nav class="ml-auto my-2">
             <b-row>
                <b-col cols="1" class="nav justify-content-start">
                <b-form-select v-model="searchKinds" :options="searchOptions" style="margin-right:10px;">
                </b-form-select>
                </b-col>

                <b-col class="nav justify-content-start">
                    <b-input-group>
                        <b-form-input size="sm" v-model="search" placeholder="검색하세요" style="max-width:200px; border-color:#c2e8c2;">
                        </b-form-input>
                        <b-input-group-append>
                            <b-button size="sm" style="background-color:#56c271; border-color:#c2e8c2;" @click="searchApi()">검색</b-button>
                        </b-input-group-append>
                    </b-input-group>
                </b-col>

                <b-col class="nav justify-content-end">
                    <b-form-select v-model="perPage" :options="options" size="sm">
                    </b-form-select>
                    <a v-if="kind!='all'" @click="kindAll()">전체보기</a>
                </b-col>
             </b-row>
      </b-navbar-nav>

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
                    <td> <a v-if="item.activated==false" @click="check(item.id)"> 복구
                    </a>
                    </td>
                    <b-button size="sm" style="float:right; background-color:#56c271; border-color:#c2e8c2"  @click="put(item.id, item.name, item.email, item.address, item.address_detail ,item.phone)" v-b-modal.AdminChangeMember>수정</b-button>
                      
                </tr>
            </tbody>
        </table>

        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center">
        </b-pagination>


        <b-button size="sm" style="float:right; background-color:#56c271; border-color:#c2e8c2" @click="deleteMember()">삭제</b-button>
        <h6 style="float:right;">
            <pre />{{tableselected.length}}/{{rows}}..</h6>
  <b-modal id="AdminChangeMember"  centered hide-header hide-footer size="xl" style="position:relative;">
              
               <template #default="{ close }">
                  <div style="background-color: #fbf7f2; min-height:200px; width:100%;">
                      <b-button style="background-color:#ff8a3d; border:3px solid white; border-radius:50%; height:40px; width:40px; text0align:center; position:absolute; top:-10px; right:-10px;"
                    @click="close()">
                      X
                    </b-button>
              
                <admin-change-member :id="id" :name="name" :address="address" :address_detail="address_detail" :email="email" :password="password" :phone="phone"/>
                   </div>
              </template>
 </b-modal>

            
    </div>
</template>

<script>
    import axios from 'axios';
    import AdminChangeMember from '../Modal/AdminChangeMember.vue'
    

    export default {
             components:{
        AdminChangeMember
      },
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
                name: '',
                email: '',
                phone: 0,
                address: '',
                address_detail:'',
                id: '',
                password: 0,

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
            put(id, name, email, address, address_detail, phone, password){
                this.id= id;
                this.name= name;
                this.email= email;
                this.address= address;
                this.address_detail= address_detail;
                this.phone= phone;
                this.password= password;

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
.table-striped>tbody>tr:nth-child(odd)>td, 
.table-striped>tbody>tr:nth-child(odd)>th {
   background-color: #e6f3e6;
 }
 table,tr,td,th{
     border-color: #56c271;
 }
</style>