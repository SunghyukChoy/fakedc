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
    
    <form class="form-login" method="post">
      <h1>DCINSIDE 로그인</h1>
      <hr style="margin: 20px 0px; border: 1px solid #eee;" />
      <div class="form-control">
        <label>아이디</label>
        <input type="text" name="MEM_ID">
      </div>
      <div class="form-control">
        <label>비밀번호</label>
        <input type="password" name="MEM_PASSWORD">
      </div>      
    </form>
    
  </div>
  
</body>
</html>