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

                        <b-form-select v-model="selected" :options="options" size="sm" class="mt-3"
                            style="float:right;"></b-form-select>

                        <b-form-input size="sm" v-model="text" placeholder="검색하세요" style="float:left; width:200px;">
                        </b-form-input>
                        <b-button size="sm" style="float:left;" @click="searchtext()">Search</b-button>


                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>
                                        <label class="form-checkbox">
                                            <input type="checkbox" v-model="selectAll" @click="select">
                                            <i class="form-icon"></i>
                                        </label>
                                    </th>
                                    <th>id</th>
                                    <th>name</th>
                                    <th>email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="i in items" v-bind:key="i">
                                    <td>
                                        <label class="form-checkbox">
                                            <input type="checkbox" :value="i.id" v-model="tableselected">
                                            <i class="form-icon"></i>
                                        </label>
                                    </td>
                                    <td>{{i.id}}</td>
                                    <td>{{i.name}}</td>
                                    <td>{{i.email}}</td>
                                </tr>
                            </tbody>
                        </table>
                        <b-button size="sm" style="float:right;" @click="deleteMember()">삭제</b-button>
                        <p />
                        <div class="text-uppercase text-bold">id selected: {{tableselected}}</div>
                        <div class="text-uppercase text-bold">paging: {{selected}}</div>
                        <div class="text-uppercase text-bold">search Keyword: {{search}}</div>
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
    export default {
        data() {
            return {
                text: '',
                search: '',
                tab: 1,
                active: this.tab,
                items: [{
                        id: "1",
                        name: "son",
                        email: "a@a"
                    },
                    {
                        id: "2",
                        name: "dong",
                        email: "b@b"
                    }
                ],
                tableselected: [],
                selectAll: false,
                selected: 10,
                options: [{
                        value: 10,
                        text: '10개씩'
                    },
                    {
                        value: 30,
                        text: '30개씩'
                    },
                ]
            }
        },
        mounted:{
          
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
            }
        }
    }
</script>

<style>
</style>