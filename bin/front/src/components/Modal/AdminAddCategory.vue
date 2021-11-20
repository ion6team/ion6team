<template>
    <div>
        <h5>카테고리 추가</h5>
        <b-input-group>
             <b-row>
                <b-col sm="7">
                    <label type="text" for="identity">카테고리 아이콘</label>
                </b-col>
                <b-col sm="5">
                    <form> <input type="file" name="categoryicon" id="categoryicon" multiple/> </form>
                    <!-- <b-form-input v-model="categoryicon" id="categoryicon" type="file"></b-form-input> -->
                </b-col>
            </b-row>
            <b-row>
                <b-col sm="7">
                    <label type="text" for="name">카테고리 명</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input v-model="name" id="name" type="text"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="text" for="identity">카테고리 영어 설명</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input v-model="description" id="identity" type="text"></b-form-input>
                </b-col>
            </b-row>
        </b-input-group>
        <button @click="addCategory()">추가</button>
        <button> 취소</button>


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

</style>