<template>
  <div>
        <h5>회원정보 변경</h5>
        <b-input-group>
             
            <b-row>
                <b-col sm="7">
                    <label type="text" for="name">이름</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="name" type="text"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="email" for="email">이메일</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="email" type="text"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="text" for="address">주소</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input  id="address" type="text"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="text" for="detailAddress">상세주소</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input  id="detailAddress" type="text"></b-form-input>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="7">
                    <label type="tel" for="phone">전화번호</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="phone" type="text"></b-form-input>
                </b-col>
            </b-row>

            
        </b-input-group>
        <button>추가</button>
        <button> 취소</button>


    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>