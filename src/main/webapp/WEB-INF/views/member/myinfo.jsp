<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="my.likeaglow.fakedc.utils.JspViewHelper"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>회원 정보 보기</title>

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


  <div class="container">

    <h1>${ vo.MEM_ID }의 회원정보.</h1>
    <span>회원 아이디 : </span><span>${ vo.MEM_ID }</span><br/>
    <span>회원 이름 : </span><span>${ vo.MEM_NAME }</span><br/>
    <span>회원 이메일 : </span><span>${ vo.EMAIL }</span><br/>
    <span>회원 연락처 : </span><span>${ vo.PHONE_NUM }</span><br/>
    <span>회원 생일 : </span><span>${ vo.BIRTHDAY }</span><br/>
    <span>최근 방문일 : </span><span>${ JspViewHelper.parseString(vo.RECENT_VISIT) }</span><br/>    
    <span>정보 제공 동의 여부 : </span><span>${ (vo.INFO_OFFER == "Y" ? "동의" : "동의하지 않음") }</span><br/>
    <span>가입 회원 : </span><span>${ vo.CREATE_USER }</span><br/>
    <span>회원 가입일 : </span><span>${ JspViewHelper.parseString(vo.CREATE_TIME) }</span><br/>
    <span>정보 수정 회원 : </span><span>${ vo.UPDATE_USER }</span><br/>        
    <span>회원 정보 수정일 : </span><span>${ JspViewHelper.parseString(vo.UPDATE_TIME) }</span><br/>
    
    
  </div>
  
  <a href="..">메인페이지</a>
  <a href="updateInfo">회원정보 수정하기</a>
  <!-- 같은 레벨의 페이지이므로 "member/"는 기재하지 않음 -->
  <a href="leave">환골탈퇴</a>

</body>
</html>