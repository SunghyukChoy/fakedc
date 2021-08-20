<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="my.likeaglow.fakedc.utils.JspViewHelper"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>게시글 목록</title>

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
    <div class="board">
      <table class="table">
        <thead class="thead-light">
          <tr>
            <th>번호</th>
            <th>말머리</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
            <th>추천</th>
            <th>비추천</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="post" items="${ postList }">

            <tr>
              <th>${ post.POST_ID }</th>
              <td>${ (post.POST_TYPE == 1 ? "일반" : "공지")}</td>
              <td><a href="/../post/${ post.POST_ID }">${ post.POST_TITLE }</a></td>
              <td>${ post.CREATE_USER }</td>
              <td>${ JspViewHelper.getTimeDifference(post.CREATE_DATE) }</td>
              <td>${ post.VIEW_COUNT }</td>
              <td>${ post.POST_RECOMMEND_COUNT }</td>
              <td>${ post.POST_UNRECOMMEND_COUNT }</td>
            </tr>

          </c:forEach>
        </tbody>
      </table>
    </div>

    <a href="/..">메인 페이지</a> <a href="/../post/write/${ boardId }">글쓰기</a>
  </div>

</body>
</html>