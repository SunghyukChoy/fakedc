<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>로그인</title>

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

input[type="text"], input[type="password"], input[type="email"] {
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

.form-control>label {
  width: 30%;
  float: left;
  display: block;
}

.form-control>label+* {
  width: 70%;
  display: block;
  float: right;
}

.form-button-group {
  display: flex;
  justify-content: center;
}

.form-button-group>button {
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

  <!--
        // TODO 2번 
        1. form 태그 생성, 요청은 MemberController.loginProcess 메서드를 실행할 수 있도록 action, method 지정
        2. 내부에 input 2개, submit 타입 버튼 만들고 다음 조건을 만족해야 함
            - 로그인 ID : input 태그, text 타입
            - 비밀번호 : input 태그, password 타입
            - 로그인 버튼 : submit 타입
        3. 전송했을 때 loginProcess에서 처리할 수 있도록 올바른 값을 넘겨야 함
        4. 디자인은 크게 상관없음
     -->

  <div class="container">

    <form class="form-login" method="post">
    
      <h1>DCINSIDE 로그인</h1>
      <hr style="margin: 20px 0px; border: 1px solid #eee;" />
      <div class="form-control">
        <label>아이디</label> <input type="text" name="MEM_ID"
          value="${ vo.MEM_ID }" />
      </div>
      <div class="form-control">
        <label>비밀번호</label> <input type="password" name="MEM_PASSWORD"
          value="${ vo.MEM_PASSWORD }" />
      </div>
      <div class="form-button-group">
        <button type="submit" class="confirm">로그인</button>
        <button type="reset" class="danger">정보 다시쓰기</button>
      </div>
    </form>

  </div>
</body>
</html>
