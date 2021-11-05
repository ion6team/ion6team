<template>
    <div>
        <b-container>
            <h4>회원가입</h4>
            <b-form>
                <b-form-group label="Email" label-for="input-1">
                    <b-form-input v-model="email" type="text" placeholder="email 입력" required></b-form-input>
                    <b-button @click="EmailDuplicateCheck" variant="primary">중복확인</b-button>
                    {{ result }}
                </b-form-group>

                <b-form-group id="input-group-2" label="name" label-for="input-2">
                    <b-form-input type="text" v-model="name" placeholder="이름 입력" required></b-form-input>


                </b-form-group>

                <b-form-group id="input-group-3" label="PW" label-for="input-3">
                    <b-form-input v-model="password" type="password" placeholder="비밀번호 입력" required>
                    </b-form-input>
                </b-form-group>

                <b-form-group id="input-group-4" label="address" label-for="input-4">
                    <b-form-input v-model="address" type="text" placeholder="주소 입력" required>
                    </b-form-input>
                </b-form-group>

                <b-form-group id="input-group-5" label="address_detail" label-for="input-5">
                    <b-form-input v-model="address_detail" type="text" placeholder="상세주소 입력" required>
                    </b-form-input>
                </b-form-group>
                <b-button @click="join" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>

                <input type="text" class="addr1" id="address" placeholder="서울특별시 서초구 -" v-model="address" disabled>

            </b-form>

        </b-container>

    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                name: '',
                email: '',
                password: '',
                address: '',
                address_detail: '',
                auth: 'ROLE_MEMBER',
                result: null,
            }
        },
        methods: {
            join() {
                axios.post('/api/join', {
                        name: this.name,
                        email: this.email,
                        password: this.password,
                        address: this.address,
                        address_detail: this.address_detail,
                        auth: this.auth,
                    })
                    .then(response => {
                        console.log(response);
                    })
            },
            EmailDuplicateCheck() {
                axios({
                    url: '/api/join/' + this.email,
                    method: 'get',
                }).then(response => {
                    console.log(response.data)
                    if (!response.data) this.result = '사용가능한 이메일'
                    else this.result = '중복이메일'
                })
            },
          
        },
        watch: {
            result: function (result) {
                this.result = result
            }
        }
    }
</script>

<style scoped>

</style>