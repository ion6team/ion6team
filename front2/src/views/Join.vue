<template>
    <div>
        <b-container>
            <h4>회원가입</h4>
            <b-form>
                <b-form-group label="Email" label-for="input-1">
                    <b-form-input v-model="form.email" type="text" placeholder="email 입력" required></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="name" label-for="input-2">
                    <b-form-input type="text" v-model="form.name" placeholder="이메일 입력" required></b-form-input>
                    <!-- <b-button @click="EmailDuplicateCheck" variant="primary">중복확인</b-button> -->

                </b-form-group>

                <b-form-group id="input-group-3" label="PW" label-for="input-3">
                    <b-form-input v-model="form.password" type="password" placeholder="비밀번호 입력" required>
                    </b-form-input>
                </b-form-group>

                <b-form-group id="input-group-4" label="address" label-for="input-4">
                    <b-form-input v-model="form.address" type="text" placeholder="주소 입력" required>
                    </b-form-input>
                </b-form-group>

                <b-form-group id="input-group-5" label="address_detail" label-for="input-5">
                    <b-form-input v-model="form.address_detail" type="text" placeholder="상세주소 입력" required>
                    </b-form-input>
                </b-form-group>
                <b-button @click="onSubmit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
        </b-container>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                form: {
                    name: '',
                    email: '',
                    password: '',
                    address: '',
                    address_detail: '',
                }
            }
        },
        methods: {
            async onSubmit() {
                try {
                    await axios.post('http://localhost:8080/api/join', this.form);
                    await this.$router.push({
                        name: 'Login'
                    })
                } catch (err) {
                    throw new Error(err);
                }
            }
        }
    }
</script>

<style scoped>

</style>