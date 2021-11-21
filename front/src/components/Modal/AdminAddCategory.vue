<template>
    <div class="text-center py-5" >
        <h4><b>카테고리 추가</b></h4>
        <div class="px-3 mb-3">
        <table style="width:100%;">
             <tr style="width:100%">
                <th style="width:30%">
                    <label type="text" for="identity">카테고리 아이콘</label>
                </th>
                <th style="width:30%">
                    <label type="text" for="name">카테고리 명</label>
                </th>
                <th style="width:30%">
                    <label type="text" for="identity">카테고리 영어 설명</label>
                </th>
                
            </tr>
            <tr>
                <td>
                    <form> <input type="file" name="categoryicon" id="categoryicon" multiple/> </form>
                    <!-- <b-form-input v-model="categoryicon" id="categoryicon" type="file"></b-form-input> -->
                </td>
                <td>
                    <b-form-input v-model="name" id="name" type="text"></b-form-input>
                </td>
                <td>
                    <b-form-input v-model="description" id="identity" type="text"></b-form-input>
                </td>
            </tr>

        </table>
        </div>
        <b-button class="mx-1" @click="addCategory()" style="background-color:#ff8a3d; border-color:#fec69f;">추가</b-button>
        <b-button class="mx-1" style="background-color:#ff8a3d; border-color:#fec69f;"> 취소</b-button>


    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'findeEmail',
        data() {
            return {
                categoryicon:'',
                name: '',
                description:'',
            }
        },
        methods: {
            addCategory() {
                 var frm = new FormData(); 
                 var photoFile = document.getElementById("categoryicon"); 
                 var data ={
                        "name" : this.name,
                        "description" : this.description,
                }
                 frm.append('data', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
                 frm.append("file",  photoFile.files[0]); 
                axios.post('/api/admin/category', frm,
                {
                    headers: {
                               'Content-Type': 'application/json',
                              'Authorization': 'Bearer ' + this.$store.state.token
        },
                   
                }).then((res) => {

                    this.email = res.data
                    if (res.data == 'null')
                        this.email = 'null'

                })
            }
        }
    }
</script>

<style scoped>
th,td{
    padding: 10px 5px;
    
}
</style>