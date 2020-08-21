<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="my.likeaglow.fakedc.utils.JspViewHelper"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>게시글 보기</title>

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
    숙제 #2
    글 상세 페이지 작성
    글정보 보여주고,
    게시글 정보를 제어할 수 있는 버튼들 추가
    - 버튼 종류
      1. 게시글 수정
      2. 게시글 삭제
      3. 추천
      4. 비추천
      5. 목록으로
   -->  
    
    <div>
      <label>게시판 이름 :</label>
      <span>${ vo.BOARD_ID }</span>
    </div>

    <div>
      <label>글종류 :</label> 
      <span>${ (vo.POST_TYPE == 1 ? "일반"  : "") }</span>
      <span>${ (vo.POST_TYPE == 2 ? "공지"  : "") }</span>
    </div>
    
    <div>
      <label>제목 :</label>
      <span>${ vo.POST_TITLE }</span>
    </div>   
    
    <div>
      <label>글쓴이 :</label>      
      <span>${ vo.CREATE_USER }</span>
    </div>
    
    <div>
      <label>작성시간 :</label>      
      <span>${ JspViewHelper.parseString(vo.CREATE_DATE) }</span>
    </div>
    
    <div>
      <label>수정시간 :</label>      
      <span>${ vo.UPDATE_DATE == vo.CREATE_DATE ? "수정된 이력이 없습니다" : JspViewHelper.parseString(vo.UPDATE_DATE) }</span>
    </div>
      
    <div>
      <label>내용 :</label>
      <%-- <textarea name="POST_CONTENT" readonly>${ vo.POST_CONTENT }</textarea> --%> 
      <p>${ vo.POST_CONTENT }</p> 
      <%-- <span>${ vo.POST_CONTENT }</span> --%>
    </div> 
  
  <a href="..">메인페이지</a>
  <a href="update/${ vo.POST_ID }"><button type="button">게시글 수정</button></a>
  <a href="delete/${ vo.BOARD_ID }/${ vo.POST_ID }"><button type="button">게시글 삭제</button></a>
  <a href="recommendPost"><button type="button">추천</button></a>
  <a href="unrecommendPost"><button type="button">비추천</button></a>
  <a href="../board/${ vo.BOARD_ID }"><button type="button">게시판으로</button></a>
  <%-- <a href="컨트롤러 메서드 매핑명"></a> --%>
  

</body>
</html>