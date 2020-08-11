<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
  <h1>Hello world!</h1>

<!-- 숙제 #1번 힌트 -->
  <c:choose>
    <c:when test="${ member != null }">
      <h1>${ member.MEM_NAME }님 환영합니다.</h1>
      <a href="member/myInfo">회원정보 보기</a>
      <a href="post/write">글쓰기</a>
      <a href="member/logout">로그아웃</a>
    </c:when>
    <c:otherwise>
      <h1>환영 메시지를 보고 싶으면 로그인을 하라우</h1>
      <a href="member/login">로그인</a>
      <a href="post/write">글쓰기</a>
      <a href="member/register">회원가입</a>
    </c:otherwise>
  </c:choose>

  <P>The time on the server is ${serverTime}.</P>
</body>
</html>
