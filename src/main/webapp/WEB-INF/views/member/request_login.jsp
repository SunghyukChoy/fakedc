<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>로그인 요청</title>

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
    <!--  action 태그의 속성값은 미입력 시 호출 당시의 URL(컨트롤러의 메소드)로 보냄 -->
    <!--  method="post" : post 메소드로 보냄 . get 방식은 브라우저의 URL에 ?속성=값 형태로 보냄-->
    <form class="form-register" method="post">
      <h1>글을 쓰고 싶다면 로그인을 해주세영</h1>
      <hr style="margin: 20px 0px; border: 1px solid #eee;" />      
      
      <span>로그인 해줘잉</span>      
      <div class="form-button-group">
        <a href="/fakedc/member/login"><button type="button" class="confirm">로그인</button></a>        
        <button type="button" onclick="history.back(-1)">뒤로 가기</button>
      </div>
    </form>
  </div>
  
</body>
</html>