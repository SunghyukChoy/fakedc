<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>회원가입</title>

<style>
  html {
    font-size: 20px;
  }

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
  h1 {
    margin-top: 15px;
    margin-bottom: 15px;
    font-size: 2rem;
  }
  
  form input {
    font-size: 1rem;
  }
  
  input[type="text"], 
  input[type="password"],
  input[type="email"] {
    padding: 0.2rem;
  }

  .container {
    width: 850px;
  }

  form.form-register {
    margin: auto;
    width: 700px;
  }
  
  .form-control {
    clear: both;
    height: 2rem;
  }
  
  .form-control > label {
    width: 30%;
    float: left;
    display: block;
  }
  
  .form-control > label + * {
    width: 70%;
    display: block;
    float: right;
  }
  
  .form-button-group {
    display: flex;
    justify-content: center;
  }
  
  .form-button-group > button {
    margin: 10px 1rem;
    padding: 0.5rem;
    font-size: 1.1rem;
    color: #fff;
    background-color: #7f5b5b;
    border: 0px;
    border-radius: 5px;
  }
  
  button.danger {
    background-color: #f66464 !important;
  }
  
  button.confirm {
    background-color: #c341b9;
  }
</style>
</head>
<body>
   
  <div class="container">
    <!--  데이터를 읽어오는 작업은 get, 그 외의 나머지는 post -->
    <!--  action 태그의 속성값은 미입력 시 호출 당시의 url로 보냄 -->
    <form class="form-register" method="post">
      <h1>DCINSIDE 회원가입</h1>
      <hr style="margin: 20px 0px; border: 1px solid #eee;" />
      <div class="form-control">
        <label>회원 아이디</label>
        <input type="text" name="MEM_ID" value="${ vo.MEM_ID }" />
      </div>
      <div class="form-control">
        <label>이름</label>
        <input type="text" name="MEM_NAME" value="${ vo.MEM_NAME }" />
      </div>
      <div class="form-control">
        <label>비밀번호</label>
        <input type="password" name="MEM_PASSWORD" value="${ vo.MEM_PASSWORD }" />
      </div>
      <div class="form-control">
        <label>비밀번호 확인</label>
        <input type="password" id="password-confirm" value="${ vo.MEM_PASSWORD }" />
      </div>
      <div class="form-control">
        <label>이메일</label>
        <input type="email" name="EMAIL" value="${ vo.EMAIL }" />
      </div>
      <div class="form-control">
        <label>휴대폰 번호</label>
        <input type="text" name="PHONE_NUM" value="${ vo.PHONE_NUM }" />
      </div>
      <div class="form-control">
        <label>생일</label>
        <input type="date" name="BIRTHDAY" value="${ vo.BIRTHDAY }" />
      </div>
      <div class="form-control">
        <label>정보제공동의</label>
        <div class="form-radio-group">
          <label for="rdo-offer-agree">동의</label>
          <input type="radio" id="rdo-offer-agree" name="INFO_OFFER" value="agree" ${ (vo.INFO_OFFER == "agree" ? "checked" : "") } />
          <label for="rdo-offer-disagree">동의하지않음</label>
          <input type="radio" id="rdo-offer-disagree" name="INFO_OFFER" value="disagree" ${ (vo.INFO_OFFER == "disagree" ? "checked" : "") } />
          <label>
                          테스트 <input type="radio" name="INFO_OFFER" value="test" ${ (vo.INFO_OFFER == "test" ? "checked" : "") }/>
          </label>
        </div>
      </div>
      <div class="form-button-group">
        <button type="submit" class="confirm">가입하기</button>
        <button type="reset" class="danger">정보 다시쓰기</button>
      </div>
    </form>
  </div>
  
</body>
</html>