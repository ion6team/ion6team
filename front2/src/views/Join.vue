<template>
  <div class="container">
    <div class="input-form-backgroud"> 
      <div class="input-form col-md-12 mx-auto"> 
        <h4 class="mb-3">회원가입</h4> 
        <form class="validation-form" novalidate> 
          <div class="row"> 
            <div class="col-md-6 mb-3"> 
              <label for="email">이메일</label> 
              <input type="email" class="form-control" id="email" placeholder="you@example.com" required> 
            <div class="invalid-feedback"> 이메일을 입력해주세요. </div> 
            </div> 
            <div class="col-md-6 mb-3"> 
                <label for="password">비밀번호</label> 
                <input type="password" class="form-control" id="password" required> 
                <div class="invalid-feedback"> 비밀번호를 입력해주세요. </div> 
            </div> 
          </div> 
          <div class="mb-3"> 
            <label for="name">이름</label> 
              <input type="text" class="form-control" id="name" placeholder="김당근" value="" required> 
              <div class="invalid-feedback"> 이름을 입력해주세요. </div> 
          </div> 
          <div class="mb-3"> 
            <label for="address">주소</label> 
            <input type="text" class="form-control" id="address" placeholder="서울특별시 강남구" required> 
            <div class="invalid-feedback"> 주소를 입력해주세요. </div>
          </div> 
          <div class="mb-3"> 
            <label for="address2">상세주소<span class="text-muted">&nbsp;(필수 아님)</span></label> 
            <input type="text" class="form-control" id="address2" placeholder="상세주소를 입력해주세요."> 
          </div> 
          <div class="mb-3"> 
            <label for="phone">전화번호<span class="text-muted">&nbsp;('-' 없이 입력)</span></label> 
            <input type="tel" class="form-control" id="phone" placeholder="전화번호를 입력해주세요."> 
          </div>
          <div class="row"> 
            <label for="resident">주민등록번호</label>
            <div class="col-md-6 mb-3">
              <input type="text" class="form-control" id="resident1" placeholder="앞자리">
            </div>
            <div class="col-md-6 mb-3">
              <input type="password" class="form-control" id="resident2" placeholder="뒷자리">
            </div>
          </div>
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement"> &nbsp;개인정보 수집 및 이용에 동의합니다.</label> 
          </div>
          <div class="mb-4"></div> 
          <button class="btn btn-primary btn-lg btn-block" type="submit">가입 완료</button> 
        </form> 
      </div> 
    </div>
  </div>
</template>

<script>
export default {
    name:'join'
}
</script>

<style>
.container {
  min-height: 100vh;
} 

.input-form {
  max-width: 680px;
  margin-top: 80px; 
  padding: 32px; 
  background: #fff; 
  border-radius: 10px; 
  box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15) 
  } 
</style>