<template>
    <div>
        <div style="margin:10px 50px 0;">
             <b-card
                     @click="go(board.id)" v-for="(board,i) in list" :key="i"  title="" class="mb-3">
                <b-row>
                    <b-col cols='2'>
                        <img class="product-thumb" v-bind:src="'../../upload/'+board.filepath1" style="width:100px;" />
                    </b-col>
                    <b-col cols='10'>
                        <h5><b>{{board.title}}</b></h5>
                        채팅방 가기
                        <!-- <table>
                            <tr>
                                <th style="width:20%;">
                                    <b-card-text>가격 : </b-card-text>
                                </th>
                                <td style="width:15%; ">
                                    <b-card-text>{{board.price}}</b-card-text>
                                </td>
                                <th style="width:15%; ">
                                    <b-card-text>주소 : </b-card-text>
                                </th>
                                <td style="width:50%">
                                    <b-card-text>{{board.hopeaddress}}</b-card-text>
                                </td>
                            </tr>
                        </table> -->
                    </b-col>
                </b-row>
            </b-card>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                list: [],
            }
        },
        mounted() {
            axios.get('/api/member/chat', {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.token
                },
            }).then((res) => {
                console.log(res.data);
                this.list = res.data
            })
        },
        methods:{
            go(id){
        var myForm = document.popForm;
        var url = "http://localhost:8081/chat/" + id;
        window.open("http://localhost:8081/chat/" + id, "popForm",
          "toolbar=no, width=420, height=600, directories=no, status=no,    scrollorbars=no, resizable=no");
            }
        }
    }
</script>

<style>

</style>