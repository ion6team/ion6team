<template>
    <div>
        <h5>카테고리 추가</h5>
        <b-input-group>
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
                name: '',
                description:'',
            }
        },
        methods: {
            addCategory() {
                axios.post('/api/admin/category', {
                    name: this.name,
                    description : this.description
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